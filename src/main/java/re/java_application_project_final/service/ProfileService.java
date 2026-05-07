package re.java_application_project_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.java_application_project_final.model.entity.*;
import re.java_application_project_final.repository.*;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PatientRegisterRepository patientRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    public Object getProfileByUser(User user) {
        switch (user.getRole()) {
            case PATIENT:
                return patientRepository.findByUser(user)
                        .orElseThrow(() -> new RuntimeException("Patient profile not found"));
            case DOCTOR:
                return doctorRepository.findByUser(user)
                        .orElseThrow(() -> new RuntimeException("Doctor profile not found"));
            case ADMIN:
                return user; // Admin only has basic user info
            default:
                throw new RuntimeException("Unknown role: " + user.getRole());
        }
    }

    @Transactional
    public void updateProfile(String username, String email, String phone, String address, 
                            String degree, Integer experienceYears, String biography) {
        User user = getUserByUsername(username);
        
        // Update user email if provided
        if (email != null && !email.trim().isEmpty()) {
            user.setEmail(email);
            userRepository.save(user);
        }

        // Update role-specific profile
        switch (user.getRole()) {
            case PATIENT:
                updatePatientProfile(user, phone, address);
                break;
            case DOCTOR:
                updateDoctorProfile(user, phone, address, degree, experienceYears, biography);
                break;
            case ADMIN:
                // Admin only updates email, handled above
                break;
        }
    }

    private void updatePatientProfile(User user, String phone, String address) {
        Patient patient = patientRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Patient profile not found"));
        
        if (phone != null) patient.setPhone(phone);
        if (address != null) patient.setAddress(address);
        
        patientRepository.save(patient);
    }

    private void updateDoctorProfile(User user, String phone, String address, 
                                   String degree, Integer experienceYears, String biography) {
        Doctor doctor = doctorRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Doctor profile not found"));
        
        if (phone != null) doctor.setPhone(phone);
        if (address != null) doctor.setAddress(address);
        if (degree != null) doctor.setDegree(degree);
        if (experienceYears != null) doctor.setExperienceYears(experienceYears);
        if (biography != null) doctor.setBiography(biography);
        
        doctorRepository.save(doctor);
    }
}
