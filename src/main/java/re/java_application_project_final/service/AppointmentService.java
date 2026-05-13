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
import java.time.LocalDateTime;
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

        System.out.println("=== BOOK APPOINTMENT SERVICE ===");
        System.out.println("Patient: " + patient.getFullName());
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Note: " + note);

        Doctor doctor =
                doctorRepository
                        .findById(doctorId)
                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Không tìm thấy bác sĩ"
                                )
                        );

        System.out.println(
                "Doctor found: "
                        + doctor.getFullName()
        );

        // THỜI GIAN LỊCH KHÁM

        LocalDateTime appointmentDateTime =
                LocalDateTime.of(
                        date,
                        time
                );

        // PHẢI ĐẶT TRƯỚC ÍT NHẤT 24 GIỜ

        if (
                appointmentDateTime.isBefore(
                        LocalDateTime.now()
                                .plusHours(24)
                )
        ) {

            throw new RuntimeException(
                    "Chỉ được đặt lịch trước ít nhất 24 giờ"
            );
        }

        // CHECK TRÙNG LỊCH

        boolean exists =
                appointmentRepository
                        .existsByDoctorAndAppointmentDateAndAppointmentTimeAndStatusNot(
                                doctor,
                                date,
                                time,
                                AppointmentStatus.CANCELLED
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
                        .consultationFee(
                                java.math.BigDecimal.valueOf(200000)
                        )
                        .status(
                                AppointmentStatus.PENDING
                        )
                        .note(note)
                        .build();

        appointmentRepository.save(
                appointment
        );
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
                        .findByDoctorIdAndAppointmentDateAndStatusNot(
                                doctorId,
                                date,
                                AppointmentStatus.CANCELLED
                        );

        List<LocalTime> bookedSlots =
                appointments.stream()
                        .map(
                                Appointment::getAppointmentTime
                        )
                        .toList();

        LocalDateTime now =
                LocalDateTime.now();

        return TIME_SLOTS.stream()

                .filter(slot -> {

                    LocalDateTime slotDateTime =
                            LocalDateTime.of(
                                    date,
                                    slot
                            );

                    // phải trước ít nhất 24h
                    return slotDateTime.isAfter(
                            now.plusHours(24)
                    );
                })

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

    @Transactional
    public void cancelAppointment(
            Long appointmentId,
            Patient patient
    ) {

        Appointment appointment =
                appointmentRepository
                        .findByIdAndPatientId(
                                appointmentId,
                                patient.getId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Không tìm thấy lịch khám"
                                )
                        );

        // Chỉ cho hủy lịch đang chờ xác nhận
        if (
                appointment.getStatus()
                        != AppointmentStatus.PENDING
        ) {

            throw new RuntimeException(
                    "Không thể hủy lịch này"
            );
        }

        // Thời gian lịch khám
        LocalDateTime appointmentDateTime =
                LocalDateTime.of(
                        appointment.getAppointmentDate(),
                        appointment.getAppointmentTime()
                );

        // Không cho hủy lịch đã diễn ra
        if (
                appointmentDateTime.isBefore(
                        LocalDateTime.now()
                )
        ) {

            throw new RuntimeException(
                    "Lịch khám đã diễn ra"
            );
        }

        // Phải hủy trước ít nhất 24 giờ
        if (
                LocalDateTime.now()
                        .plusHours(24)
                        .isAfter(
                                appointmentDateTime
                        )
        ) {

            throw new RuntimeException(
                    "Chỉ được hủy trước 24 giờ"
            );
        }

        appointment.setStatus(
                AppointmentStatus.CANCELLED
        );
    }
}