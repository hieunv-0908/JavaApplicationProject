package re.java_application_project_final.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class RevenueStatisticDto {

    private Integer month;

    private BigDecimal revenue;

}
