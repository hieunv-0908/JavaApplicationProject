package re.java_application_project_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import re.java_application_project_final.model.entity.TestType;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestTypeRepository extends JpaRepository<TestType, Long> {
    Optional<TestType> findByCode(String code);
    boolean existsByCode(String code);
    boolean existsByName(String name);
    
    @Query("SELECT t FROM TestType t WHERE t.active = true")
    List<TestType> findAllActive();
    
    @Query("SELECT t FROM TestType t WHERE t.active = true AND t.category = :category")
    List<TestType> findByCategory(String category);
}
