package re.java_application_project_final;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class JavaApplicationProjectFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaApplicationProjectFinalApplication.class, args);
    }

}
