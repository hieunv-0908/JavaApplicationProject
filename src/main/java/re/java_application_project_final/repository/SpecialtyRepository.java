package re.java_application_project_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import re.java_application_project_final.model.entity.Specialty;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Optional<Specialty> findByCode(String code);
    boolean existsByCode(String code);
    boolean existsByName(String name);
    
    @Query("SELECT s FROM Specialty s WHERE s.active = true")
    List<Specialty> findAllActive();
}
