package re.java_application_project_final.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import re.java_application_project_final.model.dto.CreateDoctorDto;
import re.java_application_project_final.model.entity.Role;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.repository.SpecialtyRepository;
import re.java_application_project_final.repository.UserRepository;
import re.java_application_project_final.service.AdminService;
import re.java_application_project_final.service.UserService;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final SpecialtyRepository specialtyRepository;
    private final AdminService adminService;

    @GetMapping
    public String showUsers(
            Model model
    ) {

        model.addAttribute(
                "users",
                userRepository.findAll()
        );

        return "admin/manage-users";
    }

    @GetMapping("/edit/{id}")
    public String showEditPage(

            @PathVariable Long id,

            Model model
    ) {

        User user =
                userRepository
                        .findById(id)
                        .orElseThrow();

        model.addAttribute(
                "user",
                user
        );

        return "admin/edit-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(

            @PathVariable Long id,

            @RequestParam String email,

            @RequestParam Role role,

            @RequestParam boolean enabled
    ) {

        User user =
                userRepository
                        .findById(id)
                        .orElseThrow();
        user.setEmail(email);
        user.setRole(role);
        user.setEnabled(enabled);
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/create-doctor")
    public String showCreateDoctorPage(
            Model model
    ) {

        model.addAttribute(
                "doc" +
                        "torDto",
                new CreateDoctorDto()
        );

        model.addAttribute(
                "specialties",
                specialtyRepository.findAll()
        );

        return "admin/create-doctor";
    }

    @PostMapping("/create-doctor")
    public String createDoctor(

            @ModelAttribute
            CreateDoctorDto dto
    ) {

        adminService.createDoctor(dto);

        return "redirect:/admin/users";
    }
}
