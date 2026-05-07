package re.java_application_project_final.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDtoLogin {
    @NotBlank(message = "Username không được trống")
    @Size(min = 3, max = 50, message = "Username phải có độ dài từ 3 đến 50 ký tự")
    private String username;
    @NotBlank(message = "Password không được trống")
    @Size(min = 8, message = "Password phải có độ dài ít nhất 8 ký tự")
    private String password;
    private boolean rememberMe;
}
