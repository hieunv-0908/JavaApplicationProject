package re.java_application_project_final.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.java_application_project_final.model.entity.*;
import re.java_application_project_final.repository.SpecialtyRepository;
import re.java_application_project_final.repository.TestTypeRepository;
import re.java_application_project_final.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

// ...existing code...

@Service
public class SeedDataService implements CommandLineRunner {

    @Autowired
    private SpecialtyRepository specialtyRepository;
    
    @Autowired
    private TestTypeRepository testTypeRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        seedAdminAccount();
        seedSpecialties();
        seedTestTypes();
    }

    private void seedSpecialties() {
        if (specialtyRepository.count() == 0) {
            List<Specialty> specialties = Arrays.asList(
                Specialty.builder()
                    .code("CARD")
                    .name("Tim mạch")
                    .description("Chuyên khoa về các bệnh liên quan đến tim và mạch máu")
                    .build(),
                    
                Specialty.builder()
                    .code("NEU")
                    .name("Thần kinh")
                    .description("Chuyên khoa về các bệnh liên quan đến hệ thần kinh")
                    .build(),
                    
                Specialty.builder()
                    .code("GAS")
                    .name("Tiêu hóa")
                    .description("Chuyên khoa về các bệnh liên quan đến hệ tiêu hóa")
                    .build(),
                    
                Specialty.builder()
                    .code("RESP")
                    .name("Hô hấp")
                    .description("Chuyên khoa về các bệnh liên quan đến hệ hô hấp")
                    .build(),
                    
                Specialty.builder()
                    .code("ENDO")
                    .name("Nội tiết")
                    .description("Chuyên khoa về các bệnh liên quan đến hệ nội tiết")
                    .build(),
                    
                Specialty.builder()
                    .code("ORTHO")
                    .name("Cơ xương khớp")
                    .description("Chuyên khoa về các bệnh liên quan đến cơ, xương, khớp")
                    .build(),
                    
                Specialty.builder()
                    .code("DERM")
                    .name("Da liễu")
                    .description("Chuyên khoa về các bệnh liên quan đến da")
                    .build(),
                    
                Specialty.builder()
                    .code("OPHTH")
                    .name("Mắt")
                    .description("Chuyên khoa về các bệnh liên quan đến mắt")
                    .build(),
                    
                Specialty.builder()
                    .code("ENT")
                    .name("Tai mũi họng")
                    .description("Chuyên khoa về các bệnh liên quan đến tai, mũi, họng")
                    .build(),
                    
                Specialty.builder()
                    .code("PED")
                    .name("Nhi")
                    .description("Chuyên khoa về các bệnh ở trẻ em")
                    .build(),
                    
                Specialty.builder()
                    .code("OBGYN")
                    .name("Sản phụ khoa")
                    .description("Chuyên khoa về sức khỏe phụ nữ và sản khoa")
                    .build(),
                    
                Specialty.builder()
                    .code("UROL")
                    .name("Tiết niệu")
                    .description("Chuyên khoa về các bệnh liên quan đến hệ tiết niệu")
                    .build(),
                    
                Specialty.builder()
                    .code("PSYCH")
                    .name("Tâm thần")
                    .description("Chuyên khoa về các bệnh liên quan đến tâm thần")
                    .build(),
                    
                Specialty.builder()
                    .code("ONCO")
                    .name("Ung bướu")
                    .description("Chuyên khoa về các bệnh ung thư")
                    .build(),
                    
                Specialty.builder()
                    .code("NEPH")
                    .name("Thận")
                    .description("Chuyên khoa về các bệnh liên quan đến thận")
                    .build()
            );
            
            specialtyRepository.saveAll(specialties);
            System.out.println("Seeded " + specialties.size() + " specialties");
        }
    }

    private void seedTestTypes() {
        if (testTypeRepository.count() == 0) {
            List<TestType> testTypes = Arrays.asList(
                // Xét nghiệm máu
                TestType.builder()
                    .code("CBC")
                    .name("Công thức máu")
                    .description("Đánh giá các thành phần tế bào trong máu")
                    .category("Xét nghiệm máu")
                    .price(new BigDecimal("120000"))
                    .sampleType("Máu")
                    .preparationInstructions("Không cần chuẩn bị đặc biệt")
                    .resultInterpretation("Bác sĩ sẽ giải thích kết quả")
                    .processingTimeHours(2)
                    .build(),
                    
                TestType.builder()
                    .code("CRP")
                    .name("CRP")
                    .description("Đánh giá tình trạng viêm trong cơ thể")
                    .category("Xét nghiệm máu")
                    .price(new BigDecimal("80000"))
                    .sampleType("Máu")
                    .preparationInstructions("Không cần chuẩn bị đặc biệt")
                    .resultInterpretation("Giá trị cao có thể chỉ định tình trạng viêm")
                    .processingTimeHours(2)
                    .build(),
                    
                TestType.builder()
                    .code("ESR")
                    .name("Tốc độ lắng huyết tương")
                    .description("Đánh giá tình trạng viêm")
                    .category("Xét nghiệm máu")
                    .price(new BigDecimal("60000"))
                    .sampleType("Máu")
                    .preparationInstructions("Không cần chuẩn bị đặc biệt")
                    .resultInterpretation("Giá trị cao có thể chỉ định tình trạng viêm")
                    .processingTimeHours(1)
                    .build(),
                    
                // Xét nghiệm sinh hóa
                TestType.builder()
                    .code("GLU")
                    .name("Đường huyết")
                    .description("Đo lượng đường trong máu")
                    .category("Xét nghiệm sinh hóa")
                    .price(new BigDecimal("50000"))
                    .sampleType("Máu")
                    .preparationInstructions("Nhịn ăn 8-12 giờ")
                    .resultInterpretation("Đường huyết cao có thể chỉ định tiểu đường")
                    .processingTimeHours(1)
                    .build(),
                    
                TestType.builder()
                    .code("CHOL")
                    .name("Máu mỡ tổng thể")
                    .description("Đánh giá mỡ trong máu")
                    .category("Xét nghiệm sinh hóa")
                    .price(new BigDecimal("150000"))
                    .sampleType("Máu")
                    .preparationInstructions("Nhịn ăn 12 giờ")
                    .resultInterpretation("Đánh giá nguy cơ bệnh tim mạch")
                    .processingTimeHours(3)
                    .build(),
                    
                TestType.builder()
                    .code("LIVER")
                    .name("Chức năng gan")
                    .description("Đánh giá chức năng gan")
                    .category("Xét nghiệm sinh hóa")
                    .price(new BigDecimal("120000"))
                    .sampleType("Máu")
                    .preparationInstructions("Nhịn ăn 8-12 giờ")
                    .resultInterpretation("Đánh giá sức khỏe gan")
                    .processingTimeHours(2)
                    .build(),
                    
                TestType.builder()
                    .code("KIDNEY")
                    .name("Chức năng thận")
                    .description("Đánh giá chức năng thận")
                    .category("Xét nghiệm sinh hóa")
                    .price(new BigDecimal("100000"))
                    .sampleType("Máu")
                    .preparationInstructions("Không cần chuẩn bị đặc biệt")
                    .resultInterpretation("Đánh giá sức khỏe thận")
                    .processingTimeHours(2)
                    .build(),
                    
                // Xét nghiệm nước tiểu
                TestType.builder()
                    .code("URINE")
                    .name("Tổng phân tích nước tiểu")
                    .description("Đánh giá sức khỏe qua nước tiểu")
                    .category("Xét nghiệm nước tiểu")
                    .price(new BigDecimal("40000"))
                    .sampleType("Nước tiểu")
                    .preparationInstructions("Lấy nước tiểu giữa dòng")
                    .resultInterpretation("Phát hiện các vấn đề về đường tiết niệu")
                    .processingTimeHours(1)
                    .build(),
                    
                // Xét nghiệm hormone
                TestType.builder()
                    .code("TSH")
                    .name("TSH")
                    .description("Đánh giá chức năng tuyến giáp")
                    .category("Xét nghiệm hormone")
                    .price(new BigDecimal("180000"))
                    .sampleType("Máu")
                    .preparationInstructions("Không cần chuẩn bị đặc biệt")
                    .resultInterpretation("Đánh giá chức năng tuyến giáp")
                    .processingTimeHours(4)
                    .build(),
                    
                // Xét nghiệm miễn dịch
                TestType.builder()
                    .code("HBSAG")
                    .name("HBsAg")
                    .description("Phát hiện virus viêm gan B")
                    .category("Xét nghiệm miễn dịch")
                    .price(new BigDecimal("120000"))
                    .sampleType("Máu")
                    .preparationInstructions("Không cần chuẩn bị đặc biệt")
                    .resultInterpretation("Dương tính chỉ định nhiễm virus viêm gan B")
                    .processingTimeHours(3)
                    .build(),
                    
                TestType.builder()
                    .code("HIV")
                    .name("HIV")
                    .description("Phát hiện virus HIV")
                    .category("Xét nghiệm miễn dịch")
                    .price(new BigDecimal("200000"))
                    .sampleType("Máu")
                    .preparationInstructions("Không cần chuẩn bị đặc biệt")
                    .resultInterpretation("Bảo mật tuyệt đối")
                    .processingTimeHours(4)
                    .build()
            );
            
            testTypeRepository.saveAll(testTypes);
            System.out.println("Seeded " + testTypes.size() + " test types");
        }
    }
    
    private void seedAdminAccount() {
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123456"))
                .email("admin@medical.com")
                .role(Role.ADMIN)
                .enabled(true)
                .build();
            
            userRepository.save(admin);
            System.out.println("✓ Created admin account: username=admin, password=admin123456");
        }
    }
}
