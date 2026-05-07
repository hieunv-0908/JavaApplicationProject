package re.java_application_project_final.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.service.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @GetMapping
    public String showLoginPage() {
        return "login-page";
    }

    @PostMapping
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {

        User user = userService.login(username, password);

        if (user == null) {
            model.addAttribute(
                    "error",
                    "Sai tài khoản hoặc mật khẩu"
            );

            return "login-page";
        }

        session.setAttribute("loggedInUser", user);

        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}