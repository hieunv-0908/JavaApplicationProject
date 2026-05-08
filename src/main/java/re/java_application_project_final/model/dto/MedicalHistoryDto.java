package re.java_application_project_final.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalHistoryDto {

    private Long appointmentId;

    private LocalDate appointmentDate;

    private String doctorName;

    private String specialtyName;

    private String symptoms;

    private String diagnosis;

    private String conclusion;

    private List<MedicineHistoryDto> medicines;

    private LocalDateTime createdAt;
}
