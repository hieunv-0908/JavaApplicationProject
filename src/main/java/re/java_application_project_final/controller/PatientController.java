package re.java_application_project_final.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import re.java_application_project_final.model.entity.Role;
import re.java_application_project_final.model.entity.User;

@Controller
@RequestMapping("/patient")
public class PatientController {

    // Kiểm tra patient
    private boolean isPatient(HttpSession session) {

        User user =
                (User) session.getAttribute("loggedInUser");

        return user != null
                && user.getRole() == Role.PATIENT;
    }

    @GetMapping("/dashboard")
    public String patientDashboard(
            HttpSession session,
            Model model
    ) {

        if (!isPatient(session)) {
            return "redirect:/access-denied";
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        model.addAttribute("username", user.getUsername());
        model.addAttribute("role", "Patient");

        return "patient/dashboard";
    }

    @GetMapping("/appointments")
    public String myAppointments(
            HttpSession session,
            Model model
    ) {

        if (!isPatient(session)) {
            return "redirect:/access-denied";
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        model.addAttribute("username", user.getUsername());

        return "patient/appointments";
    }

    @GetMapping("/medical-records")
    public String myMedicalRecords(
            HttpSession session,
            Model model
    ) {

        if (!isPatient(session)) {
            return "redirect:/access-denied";
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        model.addAttribute("username", user.getUsername());

        return "patient/medical-records";
    }
}