    package re.java_application_project_final.model.entity;
    import jakarta.persistence.*;
    import lombok.*;

    @Entity
    @Table(name = "users")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String username;

        // Lưu mật khẩu đã hash BCrypt
        @Column(nullable = false)
        private String password;

        @Column(nullable = false, unique = true)
        private String email;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Role role;

        @Builder.Default
        private boolean enabled = true;
    }
