package re.java_application_project_final.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.java_application_project_final.model.dto.MedicalExaminationDto;
import re.java_application_project_final.model.dto.PrescriptionItemDto;
import re.java_application_project_final.model.entity.*;
import re.java_application_project_final.repository.*;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MedicalExaminationService {

    private final AppointmentRepository appointmentRepository;

    private final MedicalRecordRepository medicalRecordRepository;

    private final PrescriptionRepository prescriptionRepository;

    private final PrescriptionDetailRepository
            prescriptionDetailRepository;

    private final MedicineRepository medicineRepository;

    @Transactional
    public void completeExamination(
            Doctor doctor,
            MedicalExaminationDto dto
    ) { Appointment appointment =
            appointmentRepository
                    .findById(dto.getAppointmentId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Appointment not found"
                            )
                    );

        // Chỉ khám lịch pending
        if (appointment.getStatus()
                != AppointmentStatus.PENDING) {

            throw new RuntimeException(
                    "Appointment is not pending"
            );
        }

        // 1. Tạo bệnh án

        MedicalRecord medicalRecord =
                MedicalRecord.builder()
                        .appointment(appointment)
                        .patient(appointment.getPatient())
                        .doctor(doctor)
                        .symptoms(dto.getSymptoms())
                        .diagnosis(dto.getDiagnosis())
                        .conclusion(dto.getConclusion())
                        .build();

        medicalRecordRepository.save(medicalRecord);
        // 2. Tạo đơn thuốc

        Prescription prescription =
                Prescription.builder()
                        .medicalRecord(medicalRecord)
                        .doctor(doctor)
                        .status(
                                PrescriptionStatus
                                        .PENDING_DISPENSE
                        )
                        .patient(appointment.getPatient())
                        .createdAt(LocalDateTime.now())
                        .build();

        prescriptionRepository.save(prescription);

        // 3. Tạo chi tiết đơn thuốc

        for (PrescriptionItemDto item
                : dto.getMedicines()) {

            // Bỏ qua dòng thuốc trống
            if (item.getMedicineId() == null) {
                continue;
            }

            Medicine medicine =
                    medicineRepository
                            .findById(item.getMedicineId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Medicine not found"
                                    )
                            );

            PrescriptionDetail detail =
                    PrescriptionDetail.builder()
                            .prescription(prescription)
                            .medicine(medicine)
                            .quantity(item.getQuantity())
                            .usageInstructions(
                                    item.getUsageInstructions()
                            )
                            .build();

            prescriptionDetailRepository.save(detail);
        }

        // 4. Update appointment status

        appointment.setStatus(
                AppointmentStatus.COMPLETED
        );

        appointmentRepository.save(appointment);
    }
}
