package re.java_application_project_final.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalExaminationDto {

    private Long appointmentId;

    private String symptoms;

    private String diagnosis;

    private String conclusion;

    private List<PrescriptionItemDto> medicines;
}