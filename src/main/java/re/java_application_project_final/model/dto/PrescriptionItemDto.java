package re.java_application_project_final.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionItemDto {

    private Long medicineId;

    private Integer quantity;

    private String usageInstructions;
}