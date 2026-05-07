package re.java_application_project_final.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDtoRegister {
    @NotBlank(message = "Username không được trống")
    @Size(min = 3, max = 50, message = "Username phải có độ dài từ 3 đến 50 ký tự")
    private String username;
    @NotBlank(message = "Password không được trống")
    @Size(min = 8, message = "Password phải có độ dài ít nhất 8 ký tự")
    private String password;
    private String confirmPassword;
    @NotBlank(message = "Email không được trống")
    @Email(message = "Email không hợp lệ")
    private String email;
}
