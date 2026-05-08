package re.java_application_project_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.java_application_project_final.model.entity.Prescription;

@Repository
public interface PrescriptionRepository
        extends JpaRepository<Prescription, Long> {
}
