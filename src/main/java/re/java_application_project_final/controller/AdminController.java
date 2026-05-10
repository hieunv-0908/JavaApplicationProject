package re.java_application_project_final.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import re.java_application_project_final.model.dto.CreateDoctorDto;
import re.java_application_project_final.model.entity.Medicine;
import re.java_application_project_final.model.entity.Role;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.repository.SpecialtyRepository;
import re.java_application_project_final.service.AdminService;
import re.java_application_project_final.service.DoctorService;
import re.java_application_project_final.service.MedicineService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final MedicineService medicineService;

    private final SpecialtyRepository specialtyRepository;

    private final DoctorService doctorService;

    private final AdminService adminService;

    private boolean isAdmin(HttpSession session) {

        User user =
                (User) session.getAttribute("loggedInUser");

        return user != null
                && user.getRole() == Role.ADMIN;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model, HttpSession session) {

        if (!isAdmin(session)) {
            return "redirect:/access-denied";
        }
        // Dashboard statistics
        long totalMedicines = medicineService.getAllMedicines(PageRequest.of(0, 1)).getTotalElements();
        List<Medicine> lowStockMedicines = medicineService.getLowStockMedicines(10);
        
        model.addAttribute("totalMedicines", totalMedicines);
        model.addAttribute("lowStockMedicines", lowStockMedicines);
        model.addAttribute("lowStockCount", lowStockMedicines.size());
        
        return "admin/dashboard";
    }
    
    @GetMapping("/medicines")
    public String manageMedicines(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            Model model, HttpSession session) {

        if (!isAdmin(session)) {
            return "redirect:/access-denied";
        }
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Medicine> medicines;
        
        if (search != null && !search.trim().isEmpty()) {
            List<Medicine> searchResults = medicineService.searchMedicines(search);
            int start = Math.min(page * size, searchResults.size());
            int end = Math.min((page + 1) * size, searchResults.size());
            medicines = new org.springframework.data.domain.PageImpl<>(
                searchResults.subList(start, end), pageable, searchResults.size());
            model.addAttribute("search", search);
        } else {
            medicines = medicineService.getActiveMedicines(pageable);
        }
        
        model.addAttribute("medicines", medicines);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", medicines.getTotalPages());
        model.addAttribute("totalItems", medicines.getTotalElements());
        
        return "admin/medicines";
    }
    
    @GetMapping("/medicines/add")
    public String showAddMedicineForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "admin/medicine-form";
    }
    
    @PostMapping("/medicines/add")
    public String addMedicine(@ModelAttribute Medicine medicine, 
                             RedirectAttributes redirectAttributes) {
        try {
            medicineService.createMedicine(medicine);
            redirectAttributes.addFlashAttribute("success", "Medicine added successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/medicines/add";
        }
        return "redirect:/admin/medicines";
    }
    
    @GetMapping("/medicines/edit/{id}")
    public String showEditMedicineForm(@PathVariable Long id, Model model,
                                      RedirectAttributes redirectAttributes) {
        try {
            Optional<Medicine> medicine = medicineService.getMedicineById(id);
            if (medicine.isPresent()) {
                model.addAttribute("medicine", medicine.get());
                return "admin/medicine-form";
            } else {
                redirectAttributes.addFlashAttribute("error", "Medicine not found");
                return "redirect:/admin/medicines";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error loading medicine: " + e.getMessage());
            return "redirect:/admin/medicines";
        }
    }
    
    @PostMapping("/medicines/edit/{id}")
    public String updateMedicine(@PathVariable Long id, @ModelAttribute Medicine medicine,
                                RedirectAttributes redirectAttributes) {
        try {
            medicineService.updateMedicine(id, medicine);
            redirectAttributes.addFlashAttribute("success", "Medicine updated successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/medicines/edit/" + id;
        }
        return "redirect:/admin/medicines";
    }
    
    @PostMapping("/medicines/delete/{id}")
    public String deleteMedicine(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            medicineService.deleteMedicine(id);
            redirectAttributes.addFlashAttribute("success", "Medicine deleted successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/medicines";
    }
    
    @PostMapping("/medicines/restore/{id}")
    public String restoreMedicine(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            medicineService.restoreMedicine(id);
            redirectAttributes.addFlashAttribute("success", "Medicine restored successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/medicines";
    }
    
    @GetMapping("/medicines/low-stock")
    public String lowStockMedicines(Model model) {
        List<Medicine> lowStockMedicines = medicineService.getLowStockMedicines(10);
        model.addAttribute("medicines", lowStockMedicines);
        model.addAttribute("title", "Low Stock Medicines");
        return "admin/medicine-list";
    }



    @PostMapping("/create-doctor")
    public String createDoctor(

            @ModelAttribute
            CreateDoctorDto dto
    ) {

        adminService.createDoctor(dto);

        return "redirect:/admin/users";
    }
}
