package re.java_application_project_final.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineHistoryDto {

    private String medicineName;

    private Integer quantity;

    private String dosage;

    private String usageInstructions;
}