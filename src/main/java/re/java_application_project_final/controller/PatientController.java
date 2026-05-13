package re.java_application_project_final.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import re.java_application_project_final.model.entity.Appointment;
import re.java_application_project_final.model.entity.Patient;
import re.java_application_project_final.model.entity.Role;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.service.AppointmentService;
import re.java_application_project_final.service.PatientService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/patient")
public class PatientController {
    private final AppointmentService appointmentService;
    private final PatientService patientService;

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

        User user =
                (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Patient patient =
                patientService.getPatientByUser(user);

        List<Appointment> appointments =
                appointmentService
                        .getAppointmentsByPatient(patient);

        model.addAttribute(
                "appointments",
                appointments
        );

        model.addAttribute(
                "username",
                user.getUsername()
        );

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

        return "patient/medical-history";
    }
}