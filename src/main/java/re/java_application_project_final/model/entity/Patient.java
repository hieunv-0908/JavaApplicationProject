package re.java_application_project_final.model.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Liên kết 1-1 với tài khoản
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private LocalDate dateOfBirth;

    // Enumerated chuyển đổi kiểu enum thành String cho db có thể đọc được
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Gender gender;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

//    // Một bệnh nhân có nhiều lịch khám
//    // Appoiinment: Cuộc hẹn
//    @OneToMany(mappedBy = "patient")
//    private List<Appointment> appointments;
//
//    // Một bệnh nhân có nhiều hồ sơ khám
//    // Medical Record: hồ sơ y tế
//    @OneToMany(mappedBy = "patient")
//    private List<MedicalRecord> medicalRecords;
}

