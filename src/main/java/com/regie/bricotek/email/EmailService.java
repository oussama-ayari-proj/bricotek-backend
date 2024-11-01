package com.regie.bricotek.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
    public void sendValidationEmail(String email, String pass) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("oussema.ayari2001@gmail.com");
        message.setTo(email);
        message.setSubject("Mail de confirmation");
        message.setText("votre mot de passe de connexion : "+pass);
        emailSender.send(message);
    }
}
