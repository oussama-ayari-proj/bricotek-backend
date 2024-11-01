package com.regie.bricotek.auth;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.regie.bricotek.Security.JwtService;
import com.regie.bricotek.User.*;
import com.regie.bricotek.email.EmailService;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public void register(RegistrationRequest request) throws MessagingException {
        var user= User.builder()
                .email(request.getEmail())
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .role(Role.USER)
                .addresse(request.getAdresse())
                .password(passwordEncoder.encode(request.getPassword()))
                .numTel(request.getNumTel())
                .enabled(true)
                .dateOfBirth(request.getDateOfBirth())
                .build();

        userRepository.save(user);
        //send validation email
        sendValidationEmail(user,request.getPassword());
    }

    private void sendValidationEmail(User user,String plainPass) throws MessagingException {
        //send mail logic
        emailService.sendValidationEmail(user.getEmail(),plainPass);
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String email= request.getEmail();
        String password= request.getPassword();
        User user=userRepository.findByEmail(email).orElseThrow(()->new SecurityException("User not found !"));
        System.out.println(passwordEncoder.encode("admin"));
        if(!passwordEncoder.matches(password, user.getPassword()))
            return AuthenticationResponse.builder()
                .token("Wrong password!")
                .build();

        if(!user.isEnabled())
            return AuthenticationResponse.builder()
                    .token("Account is disabled!")
                    .build();

        var jwtToken= jwtService.generateToken(user.getUsername());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void forgetPassword(String email, String newPassword) {
        User user=userRepository.findByEmail(email).orElseThrow(()-> new SecurityException("User not found !"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
