package re.java_application_project_final.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.service.ProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public String viewProfile(
            HttpSession session,
            Model model
    ) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        // Chưa login
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        User user =
                profileService.getUserByUsername(
                        loggedInUser.getUsername()
                );

        model.addAttribute("user", user);

        model.addAttribute(
                "profile",
                profileService.getProfileByUser(user)
        );

        return "profile/view";
    }

    @GetMapping("/edit")
    public String editProfile(
            HttpSession session,
            Model model
    ) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        // Chưa login
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        User user =
                profileService.getUserByUsername(
                        loggedInUser.getUsername()
                );

        model.addAttribute("user", user);

        model.addAttribute(
                "profile",
                profileService.getProfileByUser(user)
        );

        return "profile/edit";
    }

    @PostMapping("/update")
    public String updateProfile(
            HttpSession session,

            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String degree,
            @RequestParam(required = false) Integer experienceYears,
            @RequestParam(required = false) String biography,

            RedirectAttributes redirectAttributes
    ) {

        User loggedInUser =
                (User) session.getAttribute("loggedInUser");

        // Chưa login
        if (loggedInUser == null) {
            return "redirect:/login";
        }

        try {

            profileService.updateProfile(
                    loggedInUser.getUsername(),
                    email,
                    phone,
                    address,
                    degree,
                    experienceYears,
                    biography
            );

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Profile updated successfully!"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Failed to update profile: " + e.getMessage()
            );
        }

        return "redirect:/profile";
    }
}