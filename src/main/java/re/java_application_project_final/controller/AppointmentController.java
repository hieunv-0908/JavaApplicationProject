package re.java_application_project_final.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import re.java_application_project_final.model.entity.Patient;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.repository.DoctorRepository;
import re.java_application_project_final.repository.SpecialtyRepository;
import re.java_application_project_final.service.AppointmentService;
import re.java_application_project_final.service.PatientService;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/patient/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final PatientService patientService;

    private final SpecialtyRepository specialtyRepository;

    private final DoctorRepository doctorRepository;

    @GetMapping("/book")
    public String showBookingPage(Model model) {
        model.addAttribute(
                "specialties",
                specialtyRepository.findAllActive()
        );

        model.addAttribute(
                "doctors",
                doctorRepository.findAll()
        );
        return "patient/book-appointment";
    }

    @PostMapping("/book")
    public String bookAppointment(
            HttpSession session,

            @RequestParam Long doctorId,
            @RequestParam LocalDate appointmentDate,
            @RequestParam LocalTime appointmentTime,
            @RequestParam(required = false) String note,

            RedirectAttributes redirectAttributes
    ) {

        User user =
                (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        try {

            Patient patient =
                    patientService.getPatientByUser(user);

            appointmentService.bookAppointment(
                    patient,
                    doctorId,
                    appointmentDate,
                    appointmentTime,
                    note
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Đặt lịch thành công"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );
        }

        return "redirect:/patient/appointments/book";
    }
}