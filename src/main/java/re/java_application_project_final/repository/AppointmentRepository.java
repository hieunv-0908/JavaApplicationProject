package re.java_application_project_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.java_application_project_final.model.entity.Appointment;
import re.java_application_project_final.model.entity.Doctor;
import re.java_application_project_final.model.entity.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
}
