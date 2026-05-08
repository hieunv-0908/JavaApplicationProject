package re.java_application_project_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.java_application_project_final.model.entity.MedicalRecord;
import re.java_application_project_final.model.entity.Prescription;
import re.java_application_project_final.model.entity.PrescriptionStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Optional<Prescription>
    findByMedicalRecord(
            MedicalRecord medicalRecord
    );
    List<Prescription>
    findByStatus(
            PrescriptionStatus status
    );
}
