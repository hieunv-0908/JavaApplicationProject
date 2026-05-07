package re.java_application_project_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.java_application_project_final.model.entity.Patient;
import re.java_application_project_final.model.entity.User;

import java.util.Optional;

@Repository
public interface PatientRegisterRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUser(User user);
    boolean existsByUser(User user);
}
