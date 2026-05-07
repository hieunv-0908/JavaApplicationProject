package re.java_application_project_final.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import re.java_application_project_final.model.dto.UserDtoRegister;
import re.java_application_project_final.service.UserRegisterService;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {
    @Autowired
    private UserRegisterService userRegisterService;

    @GetMapping
    public String showRegister(Model model) {
        model.addAttribute("userDtoRegister", new UserDtoRegister());
        return "register-page";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute UserDtoRegister userDtoRegister,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (!userDtoRegister.getPassword().equals(userDtoRegister.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.confirmPassword", "Mật khẩu xác nhận không khớp");
        }
        
        if (result.hasErrors()) {
            return "register-page";
        }
        
        try {
            userRegisterService.registerUser(userDtoRegister);
            redirectAttributes.addFlashAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "redirect:/login";
        } catch (RuntimeException e) {
            System.err.println("Registration error: " + e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        } catch (Exception e) {
            System.err.println("Unexpected error during registration: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Lỗi không xác định: " + e.getMessage());
            return "redirect:/register";
        }
    }
}
