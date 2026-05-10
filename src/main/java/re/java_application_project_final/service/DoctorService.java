package re.java_application_project_final.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.java_application_project_final.model.dto.CreateDoctorDto;
import re.java_application_project_final.model.entity.Doctor;
import re.java_application_project_final.model.entity.Role;
import re.java_application_project_final.model.entity.Specialty;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.repository.DoctorRepository;
import re.java_application_project_final.repository.SpecialtyRepository;
import re.java_application_project_final.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final SpecialtyRepository specialtyRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    // Lấy doctor theo user
    public Doctor getDoctorByUser(User user) {

        return doctorRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Doctor not found"
                        )
                );
    };

    @Transactional
    public void createDoctor(
            CreateDoctorDto dto
    ) {

        User user =
                User.builder()
                        .username(dto.getUsername())

                        .password(
                                passwordEncoder.encode(
                                        dto.getPassword()
                                )
                        )

                        .email(dto.getEmail())

                        .role(Role.DOCTOR)

                        .enabled(true)

                        .build();

        userRepository.save(user);

        Specialty specialty =
                specialtyRepository
                        .findById(
                                dto.getSpecialtyId()
                        )
                        .orElseThrow();

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

    // Lấy doctor theo id
    public Doctor getDoctorById(Long doctorId) {

        return doctorRepository
                .findById(doctorId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Doctor not found"
                        )
                );
    }

    // Lấy danh sách doctor theo chuyên khoa
    public List<Doctor> getDoctorsBySpecialtyId(
            Long specialtyId
    ) {

        Specialty specialty =
                specialtyRepository
                        .findById(specialtyId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Specialty not found"
                                )
                        );

        return doctorRepository.findBySpecialty(specialty);
    }
}