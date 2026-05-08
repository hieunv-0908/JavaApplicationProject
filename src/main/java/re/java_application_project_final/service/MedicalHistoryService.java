package re.java_application_project_final.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.java_application_project_final.model.dto.MedicalHistoryDto;
import re.java_application_project_final.model.dto.MedicineHistoryDto;
import re.java_application_project_final.model.entity.MedicalRecord;
import re.java_application_project_final.model.entity.Medicine;
import re.java_application_project_final.model.entity.Patient;
import re.java_application_project_final.model.entity.PrescriptionDetail;
import re.java_application_project_final.repository.MedicalRecordRepository;
import re.java_application_project_final.repository.PrescriptionDetailRepository;
import re.java_application_project_final.repository.PrescriptionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalHistoryService {

    private final MedicalRecordRepository
            medicalRecordRepository;

    private final PrescriptionRepository
            prescriptionRepository;

    private final PrescriptionDetailRepository
            prescriptionDetailRepository;

    public List<MedicalHistoryDto>
    getMedicalHistory(Patient patient) {

        List<MedicalRecord> records =
                medicalRecordRepository
                        .findByPatientOrderByCreatedAtDesc(
                                patient
                        );

        List<MedicalHistoryDto> result =
                new ArrayList<>();

        for (MedicalRecord record : records) {

            List<MedicineHistoryDto>
                    medicineDtos = new ArrayList<>();

            prescriptionRepository
                    .findByMedicalRecord(record)
                    .ifPresent(prescription -> {

                        List<PrescriptionDetail>
                                details =
                                prescriptionDetailRepository
                                        .findByPrescription(
                                                prescription
                                        );

                        for (PrescriptionDetail detail
                                : details) {

                            Medicine medicine =
                                    detail.getMedicine();

                            medicineDtos.add(
                                    MedicineHistoryDto
                                            .builder()
                                            .medicineName(
                                                    medicine.getName()
                                            )
                                            .quantity(
                                                    detail.getQuantity()
                                            )
                                            .dosage(
                                                    medicine.getDosage()
                                            )
                                            .usageInstructions(
                                                    detail.getUsageInstructions()
                                            )
                                            .build()
                            );
                        }
                    });

            MedicalHistoryDto dto =
                    MedicalHistoryDto.builder()
                            .appointmentId(
                                    record
                                            .getAppointment()
                                            .getId()
                            )
                            .appointmentDate(
                                    record
                                            .getAppointment()
                                            .getAppointmentDate()
                            )
                            .doctorName(
                                    record
                                            .getDoctor()
                                            .getFullName()
                            )
                            .specialtyName(
                                    record
                                            .getDoctor()
                                            .getSpecialty()
                                            .getName()
                            )
                            .symptoms(
                                    record.getSymptoms()
                            )
                            .diagnosis(
                                    record.getDiagnosis()
                            )
                            .conclusion(
                                    record.getConclusion()
                            )
                            .medicines(medicineDtos)
                            .createdAt(
                                    record.getCreatedAt()
                            )
                            .build();

            result.add(dto);
        }

        return result;
    }
}
