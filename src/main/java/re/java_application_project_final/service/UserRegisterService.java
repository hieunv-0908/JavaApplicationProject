package re.java_application_project_final.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import re.java_application_project_final.model.dto.UserDtoRegister;
import re.java_application_project_final.model.entity.Patient;
import re.java_application_project_final.model.entity.Role;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.repository.PatientRegisterRepository;
import re.java_application_project_final.repository.UserRepository;

@Service
public class UserRegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRegisterRepository patientRegisterRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(UserDtoRegister userDtoRegister) {
        // Check if username already exists
        if (userRepository.existsByUsername(userDtoRegister.getUsername())) {
            throw new RuntimeException("Username đã tồn tại: " + userDtoRegister.getUsername());
        }

        // Check if email already exists
        if (userRepository.existsByEmail(userDtoRegister.getEmail())) {
            throw new RuntimeException("Email đã tồn tại: " + userDtoRegister.getEmail());
        }

        User user = new User();
        user.setUsername(userDtoRegister.getUsername());
        user.setPassword(passwordEncoder.encode(userDtoRegister.getPassword()));
        user.setEmail(userDtoRegister.getEmail());
        user.setRole(Role.PATIENT);
        user.setEnabled(true);
        userRepository.save(user);

        Patient patient = new Patient();
        patient.setFullName(userDtoRegister.getUsername());
        patient.setUser(user);
        patientRegisterRepository.save(patient);
    }
}
