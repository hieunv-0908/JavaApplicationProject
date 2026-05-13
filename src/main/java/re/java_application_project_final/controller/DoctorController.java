package re.java_application_project_final.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import re.java_application_project_final.model.dto.MedicalExaminationDto;
import re.java_application_project_final.model.entity.Appointment;
import re.java_application_project_final.model.entity.Doctor;
import re.java_application_project_final.model.entity.Role;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.service.AppointmentService;
import re.java_application_project_final.service.DoctorService;
import re.java_application_project_final.service.MedicalExaminationService;
import re.java_application_project_final.service.MedicineService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    private final MedicalExaminationService medicalExaminationService;

    private final AppointmentService appointmentService;

    private final MedicineService medicineService;

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

    @PostMapping("/examination/complete")
    public String completeExamination(
            HttpSession session,
            @ModelAttribute MedicalExaminationDto dto,
            RedirectAttributes redirectAttributes
    ) {

        User user =
                (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        try {

            Doctor doctor =
                    doctorService.getDoctorByUser(user);

            medicalExaminationService
                    .completeExamination(doctor, dto);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Khám bệnh thành công"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );
        }

        return "redirect:/doctor/dashboard";
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

        Doctor doctor =
                doctorService.getDoctorByUser(user);

        List<Appointment> appointments =
                appointmentService
                        .getAppointmentsByDoctor(doctor);

        model.addAttribute(
                "appointments",
                appointments
        );

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

    @GetMapping("/examination")
    public String examinationPage(
            Long appointmentId,
            HttpSession session,
            Model model
    ) {

        if (!isDoctor(session)) {
            return "redirect:/access-denied";
        }

        Appointment appointment =
                appointmentService
                        .getAppointmentById(
                                appointmentId
                        );

        model.addAttribute(
                "appointment",
                appointment
        );

        model.addAttribute(
                "medicines",
                medicineService.getAllMedicines()
        );

        return "doctor/examination";
    }
}