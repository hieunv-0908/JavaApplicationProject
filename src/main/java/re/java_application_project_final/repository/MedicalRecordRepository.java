package re.java_application_project_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.java_application_project_final.model.entity.MedicalRecord;
import re.java_application_project_final.model.entity.Patient;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord>
    findByPatientOrderByCreatedAtDesc(
            Patient patient
    );
}
