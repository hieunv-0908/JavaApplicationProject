package re.java_application_project_final.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prescription_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Đơn thuốc cha
    @ManyToOne
    @JoinColumn(
            name = "prescription_id",
            nullable = false
    )
    private Prescription prescription;

    // Thuốc được kê
    @ManyToOne
    @JoinColumn(
            name = "medicine_id",
            nullable = false
    )
    private Medicine medicine;

    // Số lượng thuốc
    @Column(nullable = false)
    private Integer quantity;

    // Hướng dẫn sử dụng
    @Column(columnDefinition = "TEXT")
    private String usageInstructions;
}