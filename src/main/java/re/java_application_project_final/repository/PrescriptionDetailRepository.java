package re.java_application_project_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.java_application_project_final.model.entity.PrescriptionDetail;

@Repository
public interface PrescriptionDetailRepository
        extends JpaRepository<PrescriptionDetail, Long> {
}
