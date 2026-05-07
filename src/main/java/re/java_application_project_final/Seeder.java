package re.java_application_project_final;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import re.java_application_project_final.model.entity.Role;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.repository.UserRepository;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        try {
            // Check if admin user exists, if not create it
            if (!userRepository.existsByUsername("admin")) {
                User admin = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123456"))
                        .email("admin@medical.com")
                        .role(Role.ADMIN)
                        .enabled(true)
                        .build();
                userRepository.save(admin);
                System.out.println("✓ Admin user created: admin / admin123456");
            }

            // Check if test doctor exists, if not create it
            if (!userRepository.existsByUsername("doctor")) {
                User doctor = User.builder()
                        .username("doctor")
                        .password(passwordEncoder.encode("doctor123456"))
                        .email("doctor@medical.com")
                        .role(Role.DOCTOR)
                        .enabled(true)
                        .build();
                userRepository.save(doctor);
                System.out.println("✓ Doctor user created: doctor / doctor123456");
            }

            if (!userRepository.existsByUsername("patient")) {
                User patient = User.builder()
                        .username("patient")
                        .password(passwordEncoder.encode("patient12345"))
                        .email("patient@medical.com")
                        .role(Role.PATIENT)
                        .enabled(true)
                        .build();
                userRepository.save(patient);
                System.out.println("✓ Patient user created: patient / patient12345");
            }

            System.out.println("✓ Database seeding completed!");
        } catch (Exception e) {
            System.err.println(" Error during database seeding: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
