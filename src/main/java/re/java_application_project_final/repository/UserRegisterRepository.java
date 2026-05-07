package re.java_application_project_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.java_application_project_final.model.entity.User;

@Repository
public interface UserRegisterRepository extends JpaRepository<User, Long> {
}
