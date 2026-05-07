package re.java_application_project_final.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tài khoản đăng nhập
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // Họ tên bác sĩ
    @Column(nullable = false)
    private String fullName;

    // Số điện thoại
    @Column(nullable = false)
    private String phone;

    // Địa chỉ
    private String address;

    // Ngày sinh
    private LocalDate dateOfBirth;

    // Giới tính
    @Enumerated(EnumType.STRING)
    private Gender gender;

    // Bằng cấp
    private String degree;

    // Số năm kinh nghiệm
    private Integer experienceYears;

    // Mô tả giới thiệu
    @Column(columnDefinition = "TEXT")
    private String biography;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;
//
//    // Chuyên khoa
//    @ManyToOne
//    @JoinColumn(name = "specialty_id")
//    private Specialty specialty;

//    // Danh sách lịch khám
//    @OneToMany(mappedBy = "doctor")
//    private List<Appointment> appointments;
//
//    // Danh sách bệnh án đã khám
//    @OneToMany(mappedBy = "doctor")
//    private List<MedicalRecord> medicalRecords;
}