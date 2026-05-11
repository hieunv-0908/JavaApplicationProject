package re.java_application_project_final.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import re.java_application_project_final.model.entity.Appointment;
import re.java_application_project_final.model.entity.AppointmentStatus;
import re.java_application_project_final.model.entity.Doctor;
import re.java_application_project_final.model.entity.Patient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    boolean
    existsByDoctorAndAppointmentDateAndAppointmentTime(
            Doctor doctor,
            LocalDate appointmentDate,
            LocalTime appointmentTime
    );

    List<Appointment>
    findByPatientOrderByAppointmentDateAscAppointmentTimeAsc(
            Patient patient
    );

    List<Appointment>
    findByDoctorOrderByAppointmentDateAscAppointmentTimeAsc(
            Doctor doctor
    );

    List<Appointment>
    findByDoctorAndAppointmentDate(
            Doctor doctor,
            LocalDate date
    );
    Optional<Appointment>
    findByIdAndPatientId(
            Long appointmentId,
            Long patientId
    );

    List<Appointment> findByDoctorIdAndAppointmentDateAndStatusNot(
            Long doctorId,
            LocalDate appointmentDate,
            AppointmentStatus status
    );

    boolean
    existsByDoctorAndAppointmentDateAndAppointmentTimeAndStatusNot(
            Doctor doctor,
            LocalDate appointmentDate,
            LocalTime appointmentTime,
            AppointmentStatus status
    );

    @Query("""
SELECT 
    MONTH(a.appointmentDate),
    SUM(a.consultationFee)
FROM Appointment a
WHERE a.status = :status
GROUP BY MONTH(a.appointmentDate)
ORDER BY MONTH(a.appointmentDate)
""")
    List<Object[]> getMonthlyRevenue(AppointmentStatus status);

    @Query("""
SELECT 
    d.fullName,
    s.name,
    COUNT(a.id)
FROM Appointment a
JOIN a.doctor d
JOIN d.specialty s
WHERE a.status = :status
GROUP BY d.id, d.fullName, s.name
ORDER BY COUNT(a.id) DESC
""")
    List<Object[]> getTopDoctors(AppointmentStatus status, Pageable pageable);

    @Query("""
SELECT COUNT(a.id)
FROM Appointment a
""")
    Long countAppointments();

    @Query("""
SELECT SUM(a.consultationFee)
FROM Appointment a
WHERE a.status = :status
""")
    BigDecimal getTotalRevenue(AppointmentStatus status);
}
