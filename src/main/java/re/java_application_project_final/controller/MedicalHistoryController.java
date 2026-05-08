package re.java_application_project_final.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import re.java_application_project_final.model.dto.MedicalHistoryDto;
import re.java_application_project_final.model.entity.Patient;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.service.MedicalHistoryService;
import re.java_application_project_final.service.PatientService;

import java.util.List;

@Controller
@RequestMapping("/patient")
@RequiredArgsConstructor
public class MedicalHistoryController {

    private final PatientService patientService;

    private final MedicalHistoryService medicalHistoryService;

    @GetMapping("/medical-history")
    public String medicalHistory(
            HttpSession session,
            Model model
    ) {

        User user =
                (User) session.getAttribute(
                        "loggedInUser"
                );

        if (user == null) {
            return "redirect:/login";
        }

        Patient patient =
                patientService.getPatientByUser(user);

        List<MedicalHistoryDto>
                medicalHistories =
                medicalHistoryService
                        .getMedicalHistory(patient);

        model.addAttribute(
                "medicalHistories",
                medicalHistories
        );

        return "patient/medical-history";
    }
}
