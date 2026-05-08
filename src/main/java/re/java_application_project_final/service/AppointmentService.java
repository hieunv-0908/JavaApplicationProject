package re.java_application_project_final.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.java_application_project_final.model.entity.Appointment;
import re.java_application_project_final.model.entity.AppointmentStatus;
import re.java_application_project_final.model.entity.Doctor;
import re.java_application_project_final.model.entity.Patient;
import re.java_application_project_final.repository.AppointmentRepository;
import re.java_application_project_final.repository.DoctorRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final DoctorRepository doctorRepository;

    private final List<LocalTime> TIME_SLOTS =
            List.of(

                    LocalTime.of(8,0),
                    LocalTime.of(8,30),
                    LocalTime.of(9,0),
                    LocalTime.of(9,30),
                    LocalTime.of(10,0),

                    LocalTime.of(13,0),
                    LocalTime.of(13,30),
                    LocalTime.of(14,0),
                    LocalTime.of(14,30),
                    LocalTime.of(15,0)
            );

    @Transactional
    public void bookAppointment(
            Patient patient,
            Long doctorId,
            LocalDate date,
            LocalTime time,
            String note
    ) {

        Doctor doctor = doctorRepository
                .findById(doctorId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Doctor not found"
                        )
                );

        // CHECK NGÀY GIỜ QUÁ KHỨ

        LocalDate today = LocalDate.now();

        LocalTime now = LocalTime.now();

        // Ngày quá khứ
        if (date.isBefore(today)) {

            throw new RuntimeException(
                    "Không thể đặt lịch trong quá khứ"
            );
        }

        // Cùng ngày nhưng giờ quá khứ
        if (date.isEqual(today)
                && time.isBefore(now)) {

            throw new RuntimeException(
                    "Không thể đặt giờ trong quá khứ"
            );
        }

        // CHECK TRÙNG LỊCH

        boolean exists =
                appointmentRepository
                        .existsByDoctorAndAppointmentDateAndAppointmentTime(
                                doctor,
                                date,
                                time
                        );

        if (exists) {

            throw new RuntimeException(
                    "Khung giờ này đã có người đặt"
            );
        }

        Appointment appointment =
                Appointment.builder()
                        .patient(patient)
                        .doctor(doctor)
                        .appointmentDate(date)
                        .appointmentTime(time)
                        .status(AppointmentStatus.PENDING)
                        .note(note)
                        .build();

        appointmentRepository.save(appointment);
    }

    public List<LocalTime>
    getAvailableSlots(
            Long doctorId,
            LocalDate date
    ) {

        Doctor doctor =
                doctorRepository
                        .findById(doctorId)
                        .orElseThrow();

        List<Appointment> appointments =
                appointmentRepository
                        .findByDoctorAndAppointmentDate(
                                doctor,
                                date
                        );

        List<LocalTime> bookedSlots =
                appointments.stream()
                        .map(
                                Appointment::getAppointmentTime
                        )
                        .toList();

        return TIME_SLOTS.stream()
                .filter(
                        slot ->
                                !bookedSlots.contains(slot)
                )
                .toList();
    }

    public List<Appointment> getAppointmentsByPatient(
            Patient patient
    ) {

        return appointmentRepository
                .findByPatientOrderByAppointmentDateAscAppointmentTimeAsc(
                        patient
                );
    }

    public List<Appointment> getAppointmentsByDoctor(
            Doctor doctor
    ) {

        return appointmentRepository
                .findByDoctorOrderByAppointmentDateAscAppointmentTimeAsc(
                        doctor
                );
    }

    public Appointment getAppointmentById(
            Long appointmentId
    ) {
        return appointmentRepository
                .findById(appointmentId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Appointment not found"
                        )
                );
    }
}