package re.java_application_project_final;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.List;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private TestTypeRepository testTypeRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PrescriptionDetailRepository prescriptionDetailRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public void run(String... args) {
        try {
            System.out.println("\n========== DATABASE SEEDING STARTED ==========\n");

            // 1. Seed Specialties
            seedSpecialties();

            // 2. Seed Users (Admin, Doctors, Patients)
            seedUsers();

            // 3. Seed Medicines
            seedMedicines();

            // 4. Seed Test Types
            seedTestTypes();

            // 5. Seed Appointments
            seedAppointments();

            // 6. Seed Medical Records
            seedMedicalRecords();

            // 7. Seed Prescriptions and Prescription Details
            seedPrescriptions();

            System.out.println("\n========== DATABASE SEEDING COMPLETED SUCCESSFULLY ==========\n");
        } catch (Exception e) {
            System.err.println("\n❌ Error during database seeding: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    private void seedSpecialties() {
        if (specialtyRepository.count() > 0) {
            System.out.println("⏭️  Specialties already seeded. Skipping...");
            return;
        }

        List<Specialty> specialties = new ArrayList<>();
        specialties.add(Specialty.builder()
                .code("NEURO")
                .name("Thần Kinh")
                .description("Chuyên khoa về các bệnh liên quan đến hệ thần kinh")
                .active(true)
                .build());

        specialties.add(Specialty.builder()
                .code("CARDIO")
                .name("Tim Mạch")
                .description("Chuyên khoa về các bệnh tim mạch và huyết áp")
                .active(true)
                .build());

        specialties.add(Specialty.builder()
                .code("ORTHO")
                .name("Chỉnh Hình")
                .description("Chuyên khoa về xương khớp")
                .active(true)
                .build());

        specialties.add(Specialty.builder()
                .code("DERM")
                .name("Da Liễu")
                .description("Chuyên khoa về da liễu")
                .active(true)
                .build());

        specialties.add(Specialty.builder()
                .code("PEDIA")
                .name("Nhi Khoa")
                .description("Chuyên khoa về các bệnh ở trẻ em")
                .active(true)
                .build());

        specialties.add(Specialty.builder()
                .code("OB_GYN")
                .name("Sản Phụ Khoa")
                .description("Chuyên khoa về bà bầu và phụ nữ")
                .active(true)
                .build());

        specialtyRepository.saveAll(specialties);
        System.out.println("✓ Created " + specialties.size() + " specialties");
    }

    private void seedUsers() {
        // Create Admin User
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123456"))
                    .email("admin@medical.com")
                    .role(Role.ADMIN)
                    .enabled(true)
                    .build();
            userRepository.save(admin);
            System.out.println("✓ Created Admin user: admin / admin123456");
        }

        // Create Doctor Users
        if (!userRepository.existsByUsername("doctor1")) {
            User doctor1 = User.builder()
                    .username("doctor1")
                    .password(passwordEncoder.encode("doctor123456"))
                    .email("doctor1@medical.com")
                    .role(Role.DOCTOR)
                    .enabled(true)
                    .build();
            userRepository.save(doctor1);
            System.out.println("✓ Created Doctor user: doctor1 / doctor123456");
        }

        if (!userRepository.existsByUsername("doctor2")) {
            User doctor2 = User.builder()
                    .username("doctor2")
                    .password(passwordEncoder.encode("doctor123456"))
                    .email("doctor2@medical.com")
                    .role(Role.DOCTOR)
                    .enabled(true)
                    .build();
            userRepository.save(doctor2);
            System.out.println("✓ Created Doctor user: doctor2 / doctor123456");
        }

        if (!userRepository.existsByUsername("doctor3")) {
            User doctor3 = User.builder()
                    .username("doctor3")
                    .password(passwordEncoder.encode("doctor123456"))
                    .email("doctor3@medical.com")
                    .role(Role.DOCTOR)
                    .enabled(true)
                    .build();
            userRepository.save(doctor3);
            System.out.println("✓ Created Doctor user: doctor3 / doctor123456");
        }

        // Create Patient Users
        if (!userRepository.existsByUsername("patient1")) {
            User patient1 = User.builder()
                    .username("patient1")
                    .password(passwordEncoder.encode("patient12345"))
                    .email("patient1@medical.com")
                    .role(Role.PATIENT)
                    .enabled(true)
                    .build();
            userRepository.save(patient1);
            System.out.println("✓ Created Patient user: patient1 / patient12345");
        }

        if (!userRepository.existsByUsername("patient2")) {
            User patient2 = User.builder()
                    .username("patient2")
                    .password(passwordEncoder.encode("patient12345"))
                    .email("patient2@medical.com")
                    .role(Role.PATIENT)
                    .enabled(true)
                    .build();
            userRepository.save(patient2);
            System.out.println("✓ Created Patient user: patient2 / patient12345");
        }

        if (!userRepository.existsByUsername("patient3")) {
            User patient3 = User.builder()
                    .username("patient3")
                    .password(passwordEncoder.encode("patient12345"))
                    .email("patient3@medical.com")
                    .role(Role.PATIENT)
                    .enabled(true)
                    .build();
            userRepository.save(patient3);
            System.out.println("✓ Created Patient user: patient3 / patient12345");
        }

        // Seed Doctors with their profiles
        if (doctorRepository.count() == 0) {
            List<Doctor> doctors = new ArrayList<>();

            // Get specialties (safe retrieval)
            Specialty neuroSpec = specialtyRepository.findByCode("NEURO")
                    .orElseGet(() -> {
                        Specialty s = Specialty.builder()
                                .code("NEURO")
                                .name("Thần Kinh")
                                .description("Chuyên khoa về các bệnh liên quan đến hệ thần kinh")
                                .active(true)
                                .build();
                        return specialtyRepository.save(s);
                    });

            Specialty cardioSpec = specialtyRepository.findByCode("CARDIO")
                    .orElseGet(() -> {
                        Specialty s = Specialty.builder()
                                .code("CARDIO")
                                .name("Tim Mạch")
                                .description("Chuyên khoa về các bệnh tim mạch và huyết áp")
                                .active(true)
                                .build();
                        return specialtyRepository.save(s);
                    });

            Specialty orthoSpec = specialtyRepository.findByCode("ORTHO")
                    .orElseGet(() -> {
                        Specialty s = Specialty.builder()
                                .code("ORTHO")
                                .name("Chỉnh Hình")
                                .description("Chuyên khoa về xương khớp")
                                .active(true)
                                .build();
                        return specialtyRepository.save(s);
                    });

            // Doctor 1 - Neurologist
            Doctor doctor1 = Doctor.builder()
                    .user(userRepository.findByUsername("doctor1").orElseThrow())
                    .fullName("Bác Sĩ Nguyễn Văn An")
                    .phone("0901234567")
                    .address("123 Đường Lê Lợi, Quận 1, TP.HCM")
                    .gender(Gender.MALE)
                    .degree("MD (Thần Kinh)")
                    .experienceYears(15)
                    .biography("Bác sĩ chuyên khoa Thần Kinh với 15 năm kinh nghiệm. Chuyên về chẩn đoán và điều trị các bệnh thần kinh. Tốt nghiệp từ Trường Đại học Y Hà Nội.")
                    .specialty(neuroSpec)
                    .dateOfBirth(LocalDate.of(1975, 3, 15))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            doctors.add(doctor1);

            // Doctor 2 - Cardiologist
            Doctor doctor2 = Doctor.builder()
                    .user(userRepository.findByUsername("doctor2").orElseThrow())
                    .fullName("Tiến Sĩ Trần Thị Bình")
                    .phone("0912345678")
                    .address("456 Đường Pasteur, Quận 3, TP.HCM")
                    .gender(Gender.FEMALE)
                    .degree("MD, PhD (Tim Mạch)")
                    .experienceYears(18)
                    .biography("Tiến sĩ chuyên khoa Tim Mạch với 18 năm kinh nghiệm. Chuyên điều trị các bệnh tim mạch phức tạp. Từng công tác tại các bệnh viện hàng đầu.")
                    .specialty(cardioSpec)
                    .dateOfBirth(LocalDate.of(1978, 7, 22))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            doctors.add(doctor2);

            // Doctor 3 - Orthopedic Surgeon
            Doctor doctor3 = Doctor.builder()
                    .user(userRepository.findByUsername("doctor3").orElseThrow())
                    .fullName("Bác Sĩ Lê Quốc Hùng")
                    .phone("0923456789")
                    .address("789 Đường Trần Hưng Đạo, Quận 5, TP.HCM")
                    .gender(Gender.MALE)
                    .degree("MD (Chỉnh Hình)")
                    .experienceYears(12)
                    .biography("Bác sĩ phẫu thuật chỉnh hình với 12 năm kinh nghiệm. Chuyên về phẫu thuật cơ xương khớp. Thành thạo các kỹ thuật đóng khóp tối thiểu xâm lấn.")
                    .specialty(orthoSpec)
                    .dateOfBirth(LocalDate.of(1982, 11, 8))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            doctors.add(doctor3);

            doctorRepository.saveAll(doctors);
            System.out.println("✓ Created " + doctors.size() + " doctors");
        }

        // Seed Patients with their profiles
        if (patientRepository.count() == 0) {
            List<Patient> patients = new ArrayList<>();

            Patient patient1 = Patient.builder()
                    .user(userRepository.findByUsername("patient1").orElseThrow())
                    .fullName("Phạm Minh Đức")
                    .phone("0934567890")
                    .address("321 Nguyễn Huệ, Quận 1, TP.HCM")
                    .gender(Gender.MALE)
                    .dateOfBirth(LocalDate.of(1990, 5, 12))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            patients.add(patient1);

            Patient patient2 = Patient.builder()
                    .user(userRepository.findByUsername("patient2").orElseThrow())
                    .fullName("Võ Thị Hương")
                    .phone("0945678901")
                    .address("654 Đường Cộng Hòa, Quận Tân Bình, TP.HCM")
                    .gender(Gender.FEMALE)
                    .dateOfBirth(LocalDate.of(1988, 9, 25))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            patients.add(patient2);

            Patient patient3 = Patient.builder()
                    .user(userRepository.findByUsername("patient3").orElseThrow())
                    .fullName("Đỗ Văn Tài")
                    .phone("0956789012")
                    .address("987 Đường Bạch Đằng, Quận 5, TP.HCM")
                    .gender(Gender.OTHER)
                    .dateOfBirth(LocalDate.of(1995, 2, 18))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            patients.add(patient3);

            patientRepository.saveAll(patients);
            System.out.println("✓ Created " + patients.size() + " patients");
        }
    }

    private void seedMedicines() {
        if (medicineRepository.count() > 0) {
            System.out.println("⏭️  Medicines already seeded. Skipping...");
            return;
        }

        List<Medicine> medicines = new ArrayList<>();

        medicines.add(Medicine.builder()
                .code("MED001")
                .name("Paracetamol 500mg")
                .description("Thuốc hạ sốt, giảm đau")
                .manufacturer("Công ty Cổ phần Dược phẩm Trung Ương")
                .price(new BigDecimal("15000"))
                .quantity(500)
                .unit("Viên")
                .dosage("500mg")
                .usageInstructions("Dùng 1-2 viên 3-4 lần/ngày. Uống cùng nước"
)
                .sideEffects("Rất hiếm, có thể gây dị ứng ở người nhạy cảm")
                .contraindications("Không dùng cho người dị ứng với paracetamol")
                .active(true)
                .build());

        medicines.add(Medicine.builder()
                .code("MED002")
                .name("Ibuprofen 400mg")
                .description("Chống viêm, giảm đau, hạ sốt")
                .manufacturer("Công ty Fimexco")
                .price(new BigDecimal("20000"))
                .quantity(300)
                .unit("Viên")
                .dosage("400mg")
                .usageInstructions("Dùng 1 viên 3-4 lần/ngày. Dùng sau ăn")
                .sideEffects("Có thể gây kích ứng dạ dày, chóng mặt")
                .contraindications("Không dùng khi đau dạ dày, loét")
                .active(true)
                .build());

        medicines.add(Medicine.builder()
                .code("MED003")
                .name("Amoxicillin 500mg")
                .description("Kháng sinh diệt vi khuẩn")
                .manufacturer("Công ty Cổ phần Dược Hậu Giang")
                .price(new BigDecimal("25000"))
                .quantity(200)
                .unit("Viên")
                .dosage("500mg")
                .usageInstructions("Dùng 1 viên 3 lần/ngày trong 7-10 ngày")
                .sideEffects("Có thể gây tiêu chảy, buồn nôn")
                .contraindications("Không dùng cho người dị ứng với Penicillin")
                .active(true)
                .build());

        medicines.add(Medicine.builder()
                .code("MED004")
                .name("Vitamin C 1000mg")
                .description("Bổ sung Vitamin C, tăng miễn dịch")
                .manufacturer("Công ty TNHH Pharm Service")
                .price(new BigDecimal("12000"))
                .quantity(400)
                .unit("Viên")
                .dosage("1000mg")
                .usageInstructions("Dùng 1-2 viên/ngày")
                .sideEffects("Hiếm gặp, có thể gây acid trong nước tiểu")
                .contraindications("Tương đối an toàn cho tất cả người")
                .active(true)
                .build());

        medicines.add(Medicine.builder()
                .code("MED005")
                .name("Aspirin 100mg")
                .description("Chống đông máu, bảo vệ tim mạch")
                .manufacturer("Công ty Dược phẩm Bắc Việt")
                .price(new BigDecimal("18000"))
                .quantity(250)
                .unit("Viên")
                .dosage("100mg")
                .usageInstructions("Dùng 1 viên/ngày, dùng sau ăn")
                .sideEffects("Có thể gây kích ứng dạ dày")
                .contraindications("Không dùng khi chảy máu dạ dày")
                .active(true)
                .build());

        medicines.add(Medicine.builder()
                .code("MED006")
                .name("Metformin 500mg")
                .description("Hạ đường huyết cho bệnh nhân tiểu đường")
                .manufacturer("Công ty Cổ phần Dược Hậu Giang")
                .price(new BigDecimal("22000"))
                .quantity(180)
                .unit("Viên")
                .dosage("500mg")
                .usageInstructions("Dùng 1-2 viên 2-3 lần/ngày, dùng cùng ăn")
                .sideEffects("Có thể gây tiêu chảy, buồn nôn")
                .contraindications("Không dùng cho người bệnh thận nặng")
                .active(true)
                .build());

        medicines.add(Medicine.builder()
                .code("MED007")
                .name("Lisinopril 10mg")
                .description("Hạ huyết áp, bảo vệ tim mạch")
                .manufacturer("Công ty Cổ phần Traphaco")
                .price(new BigDecimal("28000"))
                .quantity(150)
                .unit("Viên")
                .dosage("10mg")
                .usageInstructions("Dùng 1 viên/ngày, vào sáng sớm")
                .sideEffects("Có thể gây ho, chóng mặt")
                .contraindications("Không dùng khi mang thai")
                .active(true)
                .build());

        medicines.add(Medicine.builder()
                .code("MED008")
                .name("Omeprazole 20mg")
                .description("Giảm axit dạ dày, chữa viêm loét")
                .manufacturer("Công ty Fimexco")
                .price(new BigDecimal("24000"))
                .quantity(100)
                .unit("Viên")
                .dosage("20mg")
                .usageInstructions("Dùng 1 viên/ngày, 30 phút trước ăn sáng")
                .sideEffects("Hiếm gặp, có thể gây đau đầu")
                .contraindications("Tương đối an toàn")
                .active(true)
                .build());

        medicineRepository.saveAll(medicines);
        System.out.println("✓ Created " + medicines.size() + " medicines");
    }

    private void seedTestTypes() {
        if (testTypeRepository.count() > 0) {
            System.out.println("⏭️  Test Types already seeded. Skipping...");
            return;
        }

        List<TestType> testTypes = new ArrayList<>();

        testTypes.add(TestType.builder()
                .code("BLOOD001")
                .name("Xét nghiệm máu tổng quát")
                .description("Kiểm tra tế bào máu toàn bộ")
                .category("Xét nghiệm máu")
                .price(new BigDecimal("150000"))
                .sampleType("Máu tĩnh mạch")
                .preparationInstructions("Kiêng ăn từ tối hôm trước, uống nước lọc được")
                .resultInterpretation("Bình thường: WBC 4.5-11, RBC 4.5-5.9, HGB 13.5-17.5")
                .processingTimeHours(24)
                .active(true)
                .build());

        testTypes.add(TestType.builder()
                .code("BLOOD002")
                .name("Kiểm tra đường huyết")
                .description("Xét nghiệm mức đường trong máu")
                .category("Xét nghiệm máu")
                .price(new BigDecimal("100000"))
                .sampleType("Máu tĩnh mạch")
                .preparationInstructions("Kiêng ăn 8-10 tiếng trước khám")
                .resultInterpretation("Bình thường: 70-100 mg/dL (đói), <140 mg/dL (sau ăn)")
                .processingTimeHours(12)
                .active(true)
                .build());

        testTypes.add(TestType.builder()
                .code("BLOOD003")
                .name("Xét nghiệm mỡ máu (Lipid Panel)")
                .description("Kiểm tra cholesterol và triglyceride")
                .category("Xét nghiệm máu")
                .price(new BigDecimal("250000"))
                .sampleType("Máu tĩnh mạch")
                .preparationInstructions("Kiêng ăn 12-14 tiếng trước khám")
                .resultInterpretation("Cholesterol: <200 mg/dL, LDL: <100 mg/dL")
                .processingTimeHours(24)
                .active(true)
                .build());

        testTypes.add(TestType.builder()
                .code("BLOOD004")
                .name("Xét nghiệm chức năng gan")
                .description("Kiểm tra các chỉ số gan (ALT, AST, Bilirubin)")
                .category("Xét nghiệm máu")
                .price(new BigDecimal("200000"))
                .sampleType("Máu tĩnh mạch")
                .preparationInstructions("Kiêng ăn 8 tiếng trước khám")
                .resultInterpretation("ALT: 7-56 U/L, AST: 10-40 U/L")
                .processingTimeHours(24)
                .active(true)
                .build());

        testTypes.add(TestType.builder()
                .code("URINE001")
                .name("Xét nghiệm nước tiểu tổng quát")
                .description("Kiểm tra các chỉ số nước tiểu")
                .category("Xét nghiệm nước tiểu")
                .price(new BigDecimal("80000"))
                .sampleType("Nước tiểu buổi sáng")
                .preparationInstructions("Lấy nước tiểu buổi sáng, khoảng 50ml vào cốc vô trùng")
                .resultInterpretation("Bình thường: không có protein, glucose, ketone")
                .processingTimeHours(12)
                .active(true)
                .build());

        testTypes.add(TestType.builder()
                .code("ECG001")
                .name("Điện tâm đồ (ECG)")
                .description("Ghi lại hoạt động điện của tim")
                .category("Kiểm tra tim mạch")
                .price(new BigDecimal("300000"))
                .sampleType("Không cần mẫu")
                .preparationInstructions("Thoải mái, không tập thể dục trước 1 giờ")
                .resultInterpretation("Kiểm tra nhịp tim, điện tích tim, dấu hiệu bất thường")
                .processingTimeHours(1)
                .active(true)
                .build());

        testTypes.add(TestType.builder()
                .code("XRAY001")
                .name("X-quang ngực")
                .description("Chụp ảnh phổi và tim")
                .category("Chụp X-quang")
                .price(new BigDecimal("200000"))
                .sampleType("Hình ảnh")
                .preparationInstructions("Tháo hết đồ kim loại, quần áo có khuy")
                .resultInterpretation("Kiểm tra phổi, tim, xương sườn")
                .processingTimeHours(24)
                .active(true)
                .build());

        testTypes.add(TestType.builder()
                .code("ULTRASOUND001")
                .name("Siêu âm ổ bụng")
                .description("Kiểm tra các cơ quan trong ổ bụng")
                .category("Siêu âm")
                .price(new BigDecimal("350000"))
                .sampleType("Hình ảnh siêu âm")
                .preparationInstructions("Kiêng ăn 6 tiếng trước khám, uống nước")
                .resultInterpretation("Kiểm tra gan, mật, thận, tuyến tụy")
                .processingTimeHours(24)
                .active(true)
                .build());

        testTypeRepository.saveAll(testTypes);
        System.out.println("✓ Created " + testTypes.size() + " test types");
    }

    private void seedAppointments() {
        if (appointmentRepository.count() > 0) {
            System.out.println("⏭️  Appointments already seeded. Skipping...");
            return;
        }

        List<Patient> allPatients = patientRepository.findAll();
        List<Doctor> allDoctors = doctorRepository.findAll();

        if (allPatients.isEmpty() || allDoctors.isEmpty()) {
            System.out.println("⏭️  No patients or doctors available. Skipping appointments...");
            return;
        }

        List<Appointment> appointments = new ArrayList<>();
        Patient patient1 = allPatients.get(0);
        Patient patient2 = allPatients.size() > 1 ? allPatients.get(1) : allPatients.get(0);
        Doctor doctor1 = allDoctors.get(0);
        Doctor doctor2 = allDoctors.size() > 1 ? allDoctors.get(1) : allDoctors.get(0);

        appointments.add(Appointment.builder()
                .patient(patient1)
                .doctor(doctor1)
                .appointmentDate(LocalDate.now().plusDays(5))
                .appointmentTime(LocalTime.of(9, 0))
                .status(AppointmentStatus.CONFIRMED)
                .note("Tái khám bệnh đau đầu")
                .build());

        appointments.add(Appointment.builder()
                .patient(patient2)
                .doctor(doctor2)
                .appointmentDate(LocalDate.now().plusDays(7))
                .appointmentTime(LocalTime.of(10, 30))
                .status(AppointmentStatus.CONFIRMED)
                .note("Khám sức khỏe định kỳ")
                .build());

        appointments.add(Appointment.builder()
                .patient(patient1)
                .doctor(doctor2)
                .appointmentDate(LocalDate.now().minusDays(2))
                .appointmentTime(LocalTime.of(14, 0))
                .status(AppointmentStatus.COMPLETED)
                .note("Khám chuyên khoa tim mạch")
                .build());

        appointmentRepository.saveAll(appointments);
        System.out.println("✓ Created " + appointments.size() + " appointments");
    }

    private void seedMedicalRecords() {
        if (medicalRecordRepository.count() > 0) {
            System.out.println("⏭️  Medical Records already seeded. Skipping...");
            return;
        }

        List<MedicalRecord> medicalRecords = new ArrayList<>();
        List<Appointment> completedAppointments = appointmentRepository.findAll().stream()
                .filter(a -> a.getStatus() == AppointmentStatus.COMPLETED)
                .toList();

        if (!completedAppointments.isEmpty()) {
            for (Appointment appointment : completedAppointments) {
                MedicalRecord record = MedicalRecord.builder()
                        .appointment(appointment)
                        .patient(appointment.getPatient())
                        .doctor(appointment.getDoctor())
                        .symptoms("Đau đầu, chóng mặt, khó ngủ")
                        .diagnosis("Rối loạn thần kinh, căng thẳng")
                        .conclusion("Tư vấn thay đổi lối sống, uống thuốc theo đơn")
                        .createdAt(LocalDateTime.now())
                        .build();
                medicalRecords.add(record);
            }

            medicalRecordRepository.saveAll(medicalRecords);
            System.out.println("✓ Created " + medicalRecords.size() + " medical records");
        } else {
            System.out.println("⏭️  No completed appointments. Skipping medical records...");
        }
    }

    private void seedPrescriptions() {
        if (prescriptionRepository.count() > 0) {
            System.out.println("⏭️  Prescriptions already seeded. Skipping...");
            return;
        }

        List<Prescription> prescriptions = new ArrayList<>();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

        if (!medicalRecords.isEmpty()) {
            for (MedicalRecord record : medicalRecords) {
                Prescription prescription = Prescription.builder()
                        .medicalRecord(record)
                        .doctor(record.getDoctor())
                        .patient(record.getPatient())
                        .status(PrescriptionStatus.PENDING_DISPENSE)
                        .createdAt(LocalDateTime.now())
                        .details(new ArrayList<>())
                        .build();
                prescriptions.add(prescription);
            }

            prescriptionRepository.saveAll(prescriptions);
            System.out.println("✓ Created " + prescriptions.size() + " prescriptions");

            // Seed Prescription Details
            seedPrescriptionDetails(prescriptions);
        } else {
            System.out.println("⏭️  No medical records. Skipping prescriptions...");
        }
    }

    private void seedPrescriptionDetails(List<Prescription> prescriptions) {
        if (prescriptionDetailRepository.count() > 0) {
            System.out.println("⏭️  Prescription Details already seeded. Skipping...");
            return;
        }

        List<PrescriptionDetail> details = new ArrayList<>();
        List<Medicine> medicines = medicineRepository.findAll();

        if (medicines.isEmpty()) {
            System.out.println("⏭️  No medicines available. Skipping prescription details...");
            return;
        }

        for (Prescription prescription : prescriptions) {
            // Add 2-3 medicines to each prescription
            for (int i = 0; i < 2 && i < medicines.size(); i++) {
                PrescriptionDetail detail = PrescriptionDetail.builder()
                        .prescription(prescription)
                        .medicine(medicines.get(i))
                        .quantity(10 + (i * 5))
                        .usageInstructions("Uống " + (1 + i) + " viên, 2-3 lần/ngày, sau ăn")
                        .build();
                details.add(detail);
            }
        }

        prescriptionDetailRepository.saveAll(details);
        System.out.println("✓ Created " + details.size() + " prescription details");
    }
}
