package re.java_application_project_final.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDoctorDto {

    private String username;

    private String password;

    private String email;

    private String fullName;

    private String phone;

    private Long specialtyId;

    private String degree;

    private Integer experienceYears;
}
