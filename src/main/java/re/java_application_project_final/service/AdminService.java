package re.java_application_project_final.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import re.java_application_project_final.model.dto.CreateDoctorDto;
import re.java_application_project_final.model.entity.*;

import re.java_application_project_final.repository.*;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    private final DoctorRepository doctorRepository;

    private final SpecialtyRepository specialtyRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createDoctor(
            CreateDoctorDto dto
    ) {

        boolean usernameExists =
                userRepository.existsByUsername(
                        dto.getUsername()
                );

        if (usernameExists) {

            throw new RuntimeException(
                    "Username đã tồn tại"
            );
        }

        User user =
                User.builder()

                        .username(
                                dto.getUsername()
                        )

                        .password(

                                passwordEncoder.encode(
                                        dto.getPassword()
                                )

                        )

                        .email(
                                dto.getEmail()
                        )

                        .role(Role.DOCTOR)

                        .enabled(true)

                        .build();

        userRepository.save(user);

        Specialty specialty =
                specialtyRepository
                        .findById(
                                dto.getSpecialtyId()
                        )
                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Không tìm thấy chuyên khoa"
                                )

                        );

        Doctor doctor =
                Doctor.builder()

                        .user(user)

                        .fullName(
                                dto.getFullName()
                        )

                        .phone(
                                dto.getPhone()
                        )

                        .degree(
                                dto.getDegree()
                        )

                        .experienceYears(
                                dto.getExperienceYears()
                        )

                        .specialty(
                                specialty
                        )

                        .build();

        doctorRepository.save(doctor);
    }
}