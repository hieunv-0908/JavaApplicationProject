package re.java_application_project_final.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public User login(String username, String password) {

        User user = userRepository
                .findByUsername(username)
                .orElse(null);

        if (user == null) {
            return null;
        }

        boolean isMatch =
                passwordEncoder.matches(password, user.getPassword());

        if (!isMatch) {
            return null;
        }

        return user;
    }
}