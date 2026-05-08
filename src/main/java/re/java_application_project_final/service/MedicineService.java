package re.java_application_project_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.java_application_project_final.model.entity.Medicine;
import re.java_application_project_final.repository.MedicineRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    public Page<Medicine> getAllMedicines(Pageable pageable) {
        return medicineRepository.findAll(pageable);
    }
    public List<Medicine> getAllMedicines() {

        return medicineRepository.findAll();
    }

    public Page<Medicine> getActiveMedicines(Pageable pageable) {
        List<Medicine> medicines = medicineRepository.findAllActive();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), medicines.size());
        return new PageImpl<>(medicines.subList(start, end), pageable, medicines.size());
    }

    public Optional<Medicine> getMedicineById(Long id) {
        return medicineRepository.findById(id);
    }

    public Optional<Medicine> getMedicineByCode(String code) {
        return medicineRepository.findByCode(code);
    }

    public List<Medicine> searchMedicines(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return medicineRepository.findAllActive();
        }
        return medicineRepository.searchActiveMedicines(keyword.trim());
    }

    public List<Medicine> getLowStockMedicines(Integer threshold) {
        return medicineRepository.findLowStockMedicines(threshold != null ? threshold : 10);
    }

    @Transactional
    public Medicine createMedicine(Medicine medicine) {
        if (medicineRepository.existsByCode(medicine.getCode())) {
            throw new RuntimeException("Medicine with code " + medicine.getCode() + " already exists");
        }
        if (medicineRepository.existsByName(medicine.getName())) {
            throw new RuntimeException("Medicine with name " + medicine.getName() + " already exists");
        }
        return medicineRepository.save(medicine);
    }

    @Transactional
    public Medicine updateMedicine(Long id, Medicine medicineDetails) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));

        // Check if name is being changed and if new name already exists
        if (!medicine.getName().equals(medicineDetails.getName()) && 
            medicineRepository.existsByName(medicineDetails.getName())) {
            throw new RuntimeException("Medicine with name " + medicineDetails.getName() + " already exists");
        }

        medicine.setName(medicineDetails.getName());
        medicine.setDescription(medicineDetails.getDescription());
        medicine.setManufacturer(medicineDetails.getManufacturer());
        medicine.setPrice(medicineDetails.getPrice());
        medicine.setQuantity(medicineDetails.getQuantity());
        medicine.setUnit(medicineDetails.getUnit());
        medicine.setDosage(medicineDetails.getDosage());
        medicine.setUsageInstructions(medicineDetails.getUsageInstructions());
        medicine.setSideEffects(medicineDetails.getSideEffects());
        medicine.setContraindications(medicineDetails.getContraindications());
        medicine.setActive(medicineDetails.isActive());

        return medicineRepository.save(medicine);
    }

    @Transactional
    public void deleteMedicine(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));
        
        // Soft delete - just deactivate
        medicine.setActive(false);
        medicineRepository.save(medicine);
    }

    @Transactional
    public void restoreMedicine(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));
        
        medicine.setActive(true);
        medicineRepository.save(medicine);
    }

    @Transactional
    public Medicine updateStock(Long medicineId, Integer quantityChange) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + medicineId));
        
        int newQuantity = medicine.getQuantity() + quantityChange;
        if (newQuantity < 0) {
            throw new RuntimeException("Insufficient stock. Current: " + medicine.getQuantity() + ", Requested: " + Math.abs(quantityChange));
        }
        
        medicine.setQuantity(newQuantity);
        return medicineRepository.save(medicine);
    }
}
