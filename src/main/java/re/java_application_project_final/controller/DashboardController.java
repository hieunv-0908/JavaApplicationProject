package re.java_application_project_final.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import re.java_application_project_final.model.entity.Role;
import re.java_application_project_final.model.entity.User;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(
            HttpSession session,
            Model model
    ) {

        User user =
                (User) session.getAttribute("loggedInUser");

        // Chưa login
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", user.getUsername());
        model.addAttribute("role", user.getRole());

        // Redirect theo role
        if (user.getRole() == Role.ADMIN) {
            return "redirect:/admin/dashboard";
        }

        if (user.getRole() == Role.DOCTOR) {
            return "redirect:/doctor/dashboard";
        }

        if (user.getRole() == Role.PATIENT) {
            return "redirect:/patient/dashboard";
        }

        return "redirect:/access-denied";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}