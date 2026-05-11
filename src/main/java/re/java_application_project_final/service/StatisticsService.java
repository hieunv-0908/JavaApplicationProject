package re.java_application_project_final.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import re.java_application_project_final.model.dto.RevenueStatisticDto;
import re.java_application_project_final.model.dto.TopDoctorDto;
import re.java_application_project_final.model.entity.AppointmentStatus;
import re.java_application_project_final.repository.AppointmentRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final AppointmentRepository appointmentRepository;

    public List<RevenueStatisticDto> getMonthlyRevenue() {

        return appointmentRepository
                .getMonthlyRevenue(AppointmentStatus.COMPLETED)
                .stream()
                .map(obj -> new RevenueStatisticDto(
                        (Integer) obj[0],
                        (BigDecimal) obj[1]
                ))
                .toList();
    }

    public List<TopDoctorDto> getTopDoctors() {

        return appointmentRepository
                .getTopDoctors(AppointmentStatus.COMPLETED,PageRequest.of(0,5))
                .stream()
                .map(obj -> new TopDoctorDto(
                        (String) obj[0],
                        (String) obj[1],
                        (Long) obj[2]
                ))
                .toList();
    }
}
