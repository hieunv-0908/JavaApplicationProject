package re.java_application_project_final.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.java_application_project_final.model.entity.Patient;
import re.java_application_project_final.model.entity.User;
import re.java_application_project_final.repository.PatientRepository;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient getPatientByUser(User user) {

        return patientRepository
                .findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Patient not found"
                        )
                );
    }
}
