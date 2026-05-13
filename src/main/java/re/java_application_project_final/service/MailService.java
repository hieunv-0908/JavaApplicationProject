package re.java_application_project_final.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    @Async
    public void sendAppointmentEmail(
            String to,
            String patientName,
            String doctorName
    ) {

        try {

            SimpleMailMessage message =
                    new SimpleMailMessage();

            message.setTo(to);

            message.setSubject(
                    "Xác nhận đặt lịch khám"
            );

            message.setText(
                    "Xin chào "
                            + patientName
                            + "\nBạn đã đặt lịch thành công với bác sĩ "
                            + doctorName
            );

            mailSender.send(message);

            System.out.println(
                    "Email sent successfully"
            );

        } catch (Exception e) {

            System.out.println(
                    "Send email failed: "
                            + e.getMessage()
            );
        }
    }
}