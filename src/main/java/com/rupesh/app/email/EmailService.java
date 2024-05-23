package com.rupesh.app.email;

import com.rupesh.app.notification.INotificationService;
import com.rupesh.app.user.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
public class EmailService implements INotificationService<EmailRequest> {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final UserRepository userRepository;

    @Async
    @Override
    public void send(EmailRequest request) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", request.getUsername());
        properties.put("name", request.getName());
        properties.put("confirmationUrl", request.getConfirmationUrl());
        properties.put("token", request.getToken());
        Context context = new Context();
        context.setVariables(properties);

        try {
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED,
                    UTF_8.name()
            );

            helper.setFrom("dulalrupesh77@gmail.com");
            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());

            String template = templateEngine.process(request.getTemplate().getName(), context);
            helper.setText(template, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
