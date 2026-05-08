package re.java_application_project_final;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import re.java_application_project_final.model.entity.*;
import re.java_application_project_final.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

    private final UserRepository userRepository;

    private final SpecialtyRepository specialtyRepository;

    private final DoctorRepository doctorRepository;

    private final PatientRepository patientRepository;

    private final AppointmentRepository appointmentRepository;

    private final MedicineRepository medicineRepository;

    private final MedicalRecordRepository medicalRecordRepository;

    private final PrescriptionRepository prescriptionRepository;

    private final PrescriptionDetailRepository
            prescriptionDetailRepository;

    private final TestTypeRepository testTypeRepository;

    private final PasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    @Override
    @Transactional
    public void run(String... args) {

        if (userRepository.count() > 0) {
            return;
        }

        /*
            SPECIALTIES
         */

        Specialty cardio =
                specialtyRepository.save(

                        Specialty.builder()
                                .code("CARD")
                                .name("Tim mạch")
                                .description("Khám tim mạch")
                                .active(true)
                                .build()

                );

        Specialty neuro =
                specialtyRepository.save(

                        Specialty.builder()
                                .code("NEUR")
                                .name("Thần kinh")
                                .description("Khám thần kinh")
                                .active(true)
                                .build()

                );

        Specialty ent =
                specialtyRepository.save(

                        Specialty.builder()
                                .code("ENT")
                                .name("Tai mũi họng")
                                .description("Khám tai mũi họng")
                                .active(true)
                                .build()

                );

        Specialty derm =
                specialtyRepository.save(

                        Specialty.builder()
                                .code("DERM")
                                .name("Da liễu")
                                .description("Khám da liễu")
                                .active(true)
                                .build()

                );

        Specialty general =
                specialtyRepository.save(

                        Specialty.builder()
                                .code("GEN")
                                .name("Đa khoa")
                                .description("Khám tổng quát")
                                .active(true)
                                .build()

                );

        /*
            USERS
         */

        User admin =
                userRepository.save(

                        User.builder()
                                .username("admin")
                                .password(
                                        passwordEncoder.encode(
                                                "12345678"
                                        )
                                )
                                .email("admin@gmail.com")
                                .role(Role.ADMIN)
                                .enabled(true)
                                .build()

                );

        User doctor1User =
                userRepository.save(
                        User.builder()
                                .username("doctor1")
                                .password(
                                        passwordEncoder.encode(
                                                "12345678"
                                        )
                                )
                                .email("doctor1@gmail.com")
                                .role(Role.DOCTOR)
                                .enabled(true)
                                .build()
                );

        User doctor2User =
                userRepository.save(
                        User.builder()
                                .username("doctor2")
                                .password(
                                        passwordEncoder.encode(
                                                "12345678"
                                        )
                                )
                                .email("doctor2@gmail.com")
                                .role(Role.DOCTOR)
                                .enabled(true)
                                .build()
                );

        User doctor3User =
                userRepository.save(
                        User.builder()
                                .username("doctor3")
                                .password(
                                        passwordEncoder.encode(
                                                "12345678"
                                        )
                                )
                                .email("doctor3@gmail.com")
                                .role(Role.DOCTOR)
                                .enabled(true)
                                .build()
                );

        User doctor4User =
                userRepository.save(
                        User.builder()
                                .username("doctor4")
                                .password(
                                        passwordEncoder.encode(
                                                "12345678"
                                        )
                                )
                                .email("doctor4@gmail.com")
                                .role(Role.DOCTOR)
                                .enabled(true)
                                .build()
                );

        User doctor5User =
                userRepository.save(
                        User.builder()
                                .username("doctor5")
                                .password(
                                        passwordEncoder.encode(
                                                "12345678"
                                        )
                                )
                                .email("doctor5@gmail.com")
                                .role(Role.DOCTOR)
                                .enabled(true)
                                .build()
                );

        /*
            PATIENT USERS
         */

        User patient1User =
                userRepository.save(
                        User.builder()
                                .username("patient1")
                                .password(
                                        passwordEncoder.encode(
                                                "12345678"
                                        )
                                )
                                .email("patient1@gmail.com")
                                .role(Role.PATIENT)
                                .enabled(true)
                                .build()
                );

        User patient2User =
                userRepository.save(
                        User.builder()
                                .username("patient2")
                                .password(
                                        passwordEncoder.encode(
                                                "12345678"
                                        )
                                )
                                .email("patient2@gmail.com")
                                .role(Role.PATIENT)
                                .enabled(true)
                                .build()
                );

        User patient3User =
                userRepository.save(
                        User.builder()
                                .username("patient3")
                                .password(
                                        passwordEncoder.encode(
                                                "12345678"
                                        )
                                )
                                .email("patient3@gmail.com")
                                .role(Role.PATIENT)
                                .enabled(true)
                                .build()
                );

        User patient4User =
                userRepository.save(
                        User.builder()
                                .username("patient4")
                                .password(
                                        passwordEncoder.encode(
                                                "12345678"
                                        )
                                )
                                .email("patient4@gmail.com")
                                .role(Role.PATIENT)
                                .enabled(true)
                                .build()
                );

        User patient5User =
                userRepository.save(
                        User.builder()
                                .username("patient5")
                                .password(
                                        passwordEncoder.encode(
                                                "12345678"
                                        )
                                )
                                .email("patient5@gmail.com")
                                .role(Role.PATIENT)
                                .enabled(true)
                                .build()
                );

        /*
            DOCTORS
         */

        Doctor doctor1 =
                doctorRepository.save(

                        Doctor.builder()
                                .user(doctor1User)
                                .fullName("Nguyễn Văn Minh")
                                .phone("0900000001")
                                .address("Hà Nội")
                                .dateOfBirth(
                                        LocalDate.of(
                                                1980,
                                                5,
                                                10
                                        )
                                )
                                .gender(Gender.MALE)
                                .degree("Tiến sĩ")
                                .experienceYears(15)
                                .biography("Bác sĩ tim mạch")
                                .specialty(cardio)
                                .build()

                );

        Doctor doctor2 =
                doctorRepository.save(

                        Doctor.builder()
                                .user(doctor2User)
                                .fullName("Trần Thị Lan")
                                .phone("0900000002")
                                .address("Hà Nội")
                                .dateOfBirth(
                                        LocalDate.of(
                                                1985,
                                                3,
                                                12
                                        )
                                )
                                .gender(Gender.FEMALE)
                                .degree("Bác sĩ CKII")
                                .experienceYears(10)
                                .biography("Bác sĩ thần kinh")
                                .specialty(neuro)
                                .build()

                );

        Doctor doctor3 =
                doctorRepository.save(

                        Doctor.builder()
                                .user(doctor3User)
                                .fullName("Phạm Quốc Bảo")
                                .phone("0900000003")
                                .address("HCM")
                                .dateOfBirth(
                                        LocalDate.of(
                                                1978,
                                                8,
                                                20
                                        )
                                )
                                .gender(Gender.MALE)
                                .degree("Thạc sĩ")
                                .experienceYears(12)
                                .biography("Bác sĩ tai mũi họng")
                                .specialty(ent)
                                .build()

                );

        Doctor doctor4 =
                doctorRepository.save(

                        Doctor.builder()
                                .user(doctor4User)
                                .fullName("Lê Thanh Hà")
                                .phone("0900000004")
                                .address("Đà Nẵng")
                                .dateOfBirth(
                                        LocalDate.of(
                                                1987,
                                                1,
                                                15
                                        )
                                )
                                .gender(Gender.FEMALE)
                                .degree("Bác sĩ")
                                .experienceYears(8)
                                .biography("Bác sĩ da liễu")
                                .specialty(derm)
                                .build()

                );

        Doctor doctor5 =
                doctorRepository.save(

                        Doctor.builder()
                                .user(doctor5User)
                                .fullName("Hoàng Anh Tuấn")
                                .phone("0900000005")
                                .address("Hải Phòng")
                                .dateOfBirth(
                                        LocalDate.of(
                                                1982,
                                                7,
                                                25
                                        )
                                )
                                .gender(Gender.MALE)
                                .degree("Tiến sĩ")
                                .experienceYears(18)
                                .biography("Bác sĩ đa khoa")
                                .specialty(general)
                                .build()

                );

        /*
            PATIENTS
         */

        Patient patient1 =
                patientRepository.save(

                        Patient.builder()
                                .user(patient1User)
                                .fullName("Nguyễn Văn A")
                                .phone("0911111111")
                                .address("Hà Nội")
                                .dateOfBirth(
                                        LocalDate.of(
                                                2000,
                                                1,
                                                1
                                        )
                                )
                                .gender(Gender.MALE)
                                .build()

                );

        Patient patient2 =
                patientRepository.save(

                        Patient.builder()
                                .user(patient2User)
                                .fullName("Trần Thị B")
                                .phone("0922222222")
                                .address("HCM")
                                .dateOfBirth(
                                        LocalDate.of(
                                                1999,
                                                2,
                                                2
                                        )
                                )
                                .gender(Gender.FEMALE)
                                .build()

                );

        Patient patient3 =
                patientRepository.save(

                        Patient.builder()
                                .user(patient3User)
                                .fullName("Lê Văn C")
                                .phone("0933333333")
                                .address("Đà Nẵng")
                                .dateOfBirth(
                                        LocalDate.of(
                                                2001,
                                                3,
                                                3
                                        )
                                )
                                .gender(Gender.MALE)
                                .build()

                );

        Patient patient4 =
                patientRepository.save(

                        Patient.builder()
                                .user(patient4User)
                                .fullName("Phạm Thị D")
                                .phone("0944444444")
                                .address("Huế")
                                .dateOfBirth(
                                        LocalDate.of(
                                                1998,
                                                4,
                                                4
                                        )
                                )
                                .gender(Gender.FEMALE)
                                .build()

                );

        Patient patient5 =
                patientRepository.save(

                        Patient.builder()
                                .user(patient5User)
                                .fullName("Hoàng Văn E")
                                .phone("0955555555")
                                .address("Cần Thơ")
                                .dateOfBirth(
                                        LocalDate.of(
                                                2002,
                                                5,
                                                5
                                        )
                                )
                                .gender(Gender.MALE)
                                .build()

                );

        /*
            MEDICINES
         */

        Medicine med1 =
                medicineRepository.save(
                        Medicine.builder()
                                .code("MED001")
                                .name("Paracetamol")
                                .price(
                                        BigDecimal.valueOf(5000)
                                )
                                .quantity(100)
                                .dosage("2 viên/ngày")
                                .usageInstructions(
                                        "Uống sau ăn"
                                )
                                .sideEffects("Buồn ngủ")
                                .contraindications("Gan yếu")
                                .build()
                );

        Medicine med2 =
                medicineRepository.save(
                        Medicine.builder()
                                .code("MED002")
                                .name("Amoxicillin")
                                .price(
                                        BigDecimal.valueOf(12000)
                                )
                                .quantity(80)
                                .dosage("3 viên/ngày")
                                .usageInstructions(
                                        "Uống sáng chiều tối"
                                )
                                .sideEffects("Tiêu chảy")
                                .contraindications("Dị ứng penicillin")
                                .build()
                );

        Medicine med3 =
                medicineRepository.save(
                        Medicine.builder()
                                .code("MED003")
                                .name("Vitamin C")
                                .price(
                                        BigDecimal.valueOf(3000)
                                )
                                .quantity(150)
                                .dosage("1 viên/ngày")
                                .usageInstructions(
                                        "Uống sau ăn sáng"
                                )
                                .sideEffects("Nóng")
                                .contraindications("Không")
                                .build()
                );

        /*
            APPOINTMENTS
         */

        Appointment appointment1 =
                appointmentRepository.save(

                        Appointment.builder()
                                .patient(patient1)
                                .doctor(doctor1)
                                .appointmentDate(
                                        LocalDate.now()
                                                .minusDays(2)
                                )
                                .appointmentTime(
                                        LocalTime.of(8,0)
                                )
                                .status(
                                        AppointmentStatus
                                                .COMPLETED
                                )
                                .note("Đau đầu")
                                .build()

                );

        Appointment appointment2 =
                appointmentRepository.save(

                        Appointment.builder()
                                .patient(patient2)
                                .doctor(doctor2)
                                .appointmentDate(
                                        LocalDate.now()
                                                .plusDays(1)
                                )
                                .appointmentTime(
                                        LocalTime.of(9,0)
                                )
                                .status(
                                        AppointmentStatus
                                                .CONFIRMED
                                )
                                .note("Khó thở")
                                .build()

                );

        /*
            MEDICAL RECORDS
         */

        MedicalRecord medicalRecord1 =
                medicalRecordRepository.save(

                        MedicalRecord.builder()
                                .appointment(appointment1)
                                .patient(patient1)
                                .doctor(doctor1)
                                .symptoms("Sốt")
                                .diagnosis("Cảm cúm")
                                .conclusion("Nghỉ ngơi")
                                .build()

                );

        /*
            PRESCRIPTIONS
         */

        Prescription prescription1 =
                prescriptionRepository.save(

                        Prescription.builder()
                                .medicalRecord(
                                        medicalRecord1
                                )
                                .doctor(doctor1)
                                .patient(patient1)
                                .status(
                                        PrescriptionStatus
                                                .PENDING_DISPENSE
                                )
                                .createdAt(
                                        LocalDateTime.now()
                                )
                                .build()

                );

        /*
            PRESCRIPTION DETAILS
         */

        prescriptionDetailRepository.save(

                PrescriptionDetail.builder()
                        .prescription(
                                prescription1
                        )
                        .medicine(med1)
                        .quantity(2)
                        .usageInstructions(
                                "Ngày uống 2 lần"
                        )
                        .build()

        );

        prescriptionDetailRepository.save(

                PrescriptionDetail.builder()
                        .prescription(
                                prescription1
                        )
                        .medicine(med2)
                        .quantity(1)
                        .usageInstructions(
                                "Sau ăn"
                        )
                        .build()

        );

        /*
            TEST TYPES
         */

        testTypeRepository.save(

                TestType.builder()
                        .code("BT001")
                        .name("Xét nghiệm máu")
                        .description("Kiểm tra máu")
                        .category("Blood")
                        .price(
                                BigDecimal.valueOf(150000)
                        )
                        .sampleType("Máu")
                        .preparationInstructions(
                                "Nhịn ăn 8 tiếng"
                        )
                        .resultInterpretation(
                                "Đánh giá máu"
                        )
                        .processingTimeHours(2)
                        .active(true)
                        .build()

        );

        testTypeRepository.save(

                TestType.builder()
                        .code("XR001")
                        .name("X-Quang")
                        .description("Chụp X-Quang")
                        .category("Imaging")
                        .price(
                                BigDecimal.valueOf(300000)
                        )
                        .sampleType("Không")
                        .preparationInstructions(
                                "Không cần chuẩn bị"
                        )
                        .resultInterpretation(
                                "Đánh giá hình ảnh"
                        )
                        .processingTimeHours(1)
                        .active(true)
                        .build()

        );

        System.out.println(
                "========= SEEDED SUCCESS ========="
        );
    }
}