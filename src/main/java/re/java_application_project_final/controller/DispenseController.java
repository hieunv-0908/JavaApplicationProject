package re.java_application_project_final.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import re.java_application_project_final.exception
        .InsufficientStockException;
import re.java_application_project_final.model.entity.Prescription;
import re.java_application_project_final.model.entity.PrescriptionStatus;
import re.java_application_project_final.repository
        .PrescriptionRepository;
import re.java_application_project_final.service
        .DispenseService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class DispenseController {

    private final PrescriptionRepository
            prescriptionRepository;

    private final DispenseService
            dispenseService;

    @GetMapping("/dispense")
    public String dispensePage(
            Model model
    ) {

        model.addAttribute(
                "prescriptions",
                prescriptionRepository.findByStatus(
                        PrescriptionStatus
                                .PENDING_DISPENSE
                )
        );

        return "admin/dispense";
    }

    @PostMapping("/dispense/{id}")
    public String dispenseMedicine(
            @PathVariable Long id,
            Model model
    ) {

        try {

            dispenseService
                    .dispensePrescription(id);

            return "redirect:/admin/dispense";

        } catch (
                InsufficientStockException e
        ) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "prescriptions",
                    prescriptionRepository
                            .findByStatus(
                                    PrescriptionStatus
                                            .PENDING_DISPENSE
                            )
            );

            return "admin/dispense";
        }
    }
}