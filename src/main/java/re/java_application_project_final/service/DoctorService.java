package re.java_application_project_final.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.java_application_project_final.model.entity.Doctor;
import re.java_application_project_final.model.entity.Specialty;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.repository.DoctorRepository;
import re.java_application_project_final.repository.SpecialtyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final SpecialtyRepository specialtyRepository;

    // Lấy doctor theo user
    public Doctor getDoctorByUser(User user) {

        return doctorRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Doctor not found"
                        )
                );
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