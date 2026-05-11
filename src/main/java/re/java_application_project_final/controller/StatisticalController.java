package re.java_application_project_final.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import re.java_application_project_final.model.entity.AppointmentStatus;
import re.java_application_project_final.repository.AppointmentRepository;
import re.java_application_project_final.repository.DoctorRepository;
import re.java_application_project_final.repository.MedicineRepository;
import re.java_application_project_final.service.StatisticsService;

@Controller
@RequestMapping("/admin/statistical")
@RequiredArgsConstructor
public class StatisticalController {

    private final StatisticsService statisticsService;

    private final AppointmentRepository appointmentRepository;

    private final DoctorRepository doctorRepository;

    private final MedicineRepository medicineRepository;

    @GetMapping
    public String statisticalPage(Model model) {

        model.addAttribute(
                "revenues",
                statisticsService.getMonthlyRevenue()
        );

        model.addAttribute(
                "topDoctors",
                statisticsService.getTopDoctors()
        );

        model.addAttribute(
                "totalRevenue",
                appointmentRepository.getTotalRevenue(AppointmentStatus.COMPLETED)
        );

        model.addAttribute(
                "totalAppointments",
                appointmentRepository.countAppointments()
        );

        model.addAttribute(
                "totalDoctors",
                doctorRepository.countDoctors()
        );

        model.addAttribute(
                "lowStockCount",
                medicineRepository.countLowStockMedicines()
        );

        return "admin/statistical-page";
    }
}
