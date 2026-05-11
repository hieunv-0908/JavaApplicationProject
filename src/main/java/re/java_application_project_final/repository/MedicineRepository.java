package re.java_application_project_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import re.java_application_project_final.model.entity.Medicine;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByCode(String code);
    boolean existsByCode(String code);
    boolean existsByName(String name);
    
    @Query("SELECT m FROM Medicine m WHERE m.active = true")
    List<Medicine> findAllActive();
    
    @Query("SELECT m FROM Medicine m WHERE m.active = true AND (LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(m.code) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Medicine> searchActiveMedicines(@Param("keyword") String keyword);
    
    @Query("SELECT m FROM Medicine m WHERE m.quantity <= :threshold AND m.active = true")
    List<Medicine> findLowStockMedicines(@Param("threshold") Integer threshold);

    @Query(""" 
SELECT COUNT(m.id) FROM Medicine m WHERE m.quantity < 10 
""")
    Long countLowStockMedicines();
}
