package re.java_application_project_final.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import re.java_application_project_final.model.entity.Role;
import re.java_application_project_final.model.entity.User;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    // Hàm kiểm tra doctor
    private boolean isDoctor(HttpSession session) {

        User user =
                (User) session.getAttribute("loggedInUser");

        return user != null
                && user.getRole() == Role.DOCTOR;
    }

    @GetMapping("/dashboard")
    public String doctorDashboard(
            HttpSession session,
            Model model
    ) {

        if (!isDoctor(session)) {
            return "redirect:/access-denied";
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        model.addAttribute("username", user.getUsername());
        model.addAttribute("role", "Doctor");

        return "doctor/dashboard";
    }

    @GetMapping("/appointments")
    public String manageAppointments(
            HttpSession session,
            Model model
    ) {

        if (!isDoctor(session)) {
            return "redirect:/access-denied";
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        model.addAttribute("username", user.getUsername());

        return "doctor/appointments";
    }

    @GetMapping("/patients")
    public String viewPatients(
            HttpSession session,
            Model model
    ) {

        if (!isDoctor(session)) {
            return "redirect:/access-denied";
        }

        User user =
                (User) session.getAttribute("loggedInUser");

        model.addAttribute("username", user.getUsername());

        return "doctor/patients";
    }
}