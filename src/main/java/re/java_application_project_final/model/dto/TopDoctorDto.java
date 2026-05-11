package re.java_application_project_final.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TopDoctorDto {

    private String fullName;

    private String specialtyName;

    private Long totalAppointments;

}