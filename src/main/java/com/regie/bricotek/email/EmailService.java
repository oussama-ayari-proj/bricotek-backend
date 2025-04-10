package com.regie.bricotek.email;

import jakarta.mail.MessagingException;
;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;
    public void sendValidationEmail(String email, String pass, String userId) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("oussema.ayari2001@gmail.com");
        message.setTo(email);
        message.setSubject("Mail de confirmation");
        message.setText("votre mot de passe de connexion : "+pass+ " et votre numéro d'adhésion est : "+userId);
        emailSender.send(message);
    }
}
