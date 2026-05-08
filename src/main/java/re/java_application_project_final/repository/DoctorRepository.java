package re.java_application_project_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.java_application_project_final.model.entity.Doctor;
import re.java_application_project_final.model.entity.Specialty;
import re.java_application_project_final.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByUser(User user);
    List<Doctor> findBySpecialty(Specialty specialty);
    boolean existsByUser(User user);
}
