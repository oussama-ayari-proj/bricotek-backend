package com.regie.bricotek.auth;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.regie.bricotek.Security.JwtService;
import com.regie.bricotek.User.*;
import com.regie.bricotek.email.EmailService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void modifyUser(String id,RegistrationRequest request){
        if(userRepository.findByUserId(id).isPresent()){
            User user=userRepository.findByUserId(id).get();
            user.setAddresse(request.getAdresse());
            user.setNom(request.getNom());
            user.setEmail(request.getEmail());
            user.setPrenom(request.getPrenom());
            user.setNumTel(request.getNumTel());
            user.setDateAdh(request.getDateAdh());
            user.setDateFinAdh(request.getDateFinAdh());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEnabled(true);
            user.setRole(user.getRole());
            user.setCotisation(request.getCotisation());
            userRepository.save(user);
        }
    }
    public void register(RegistrationRequest request) throws MessagingException {
        System.out.println(request.getDateAdh());
        System.out.println(request.getDateFinAdh());
        var user= User.builder()
                .email(request.getEmail())
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .role(Role.USER)
                .addresse(request.getAdresse())
                .password(passwordEncoder.encode(request.getPassword()))
                .numTel(request.getNumTel())
                .enabled(true)
                .dateAdh(request.getDateAdh())
                .dateFinAdh(request.getDateFinAdh())
                .cotisation(request.getCotisation())
                .build();
        userRepository.save(user);
        //send validation email
        sendValidationEmail(user,request.getPassword(),user.getUserId());
    }

    private void sendValidationEmail(User user, String plainPass, String userId) throws MessagingException {
        //send mail logic
        emailService.sendValidationEmail(user.getEmail(),plainPass,userId);
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String email= request.getEmail();
        String password= request.getPassword();
        User user;
        if(userRepository.findByEmail(email).isPresent()){
            user=userRepository.findByEmail(email).get();
        }else if(userRepository.findByUserId(email).isPresent()){
            user=userRepository.findByUserId(email).get();
        }else return AuthenticationResponse.builder()
                .token("Wrong email or numAdh!")
                .build();
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

    public RegistrationRequest getUserById(String id) {
        if(userRepository.findByUserId(id).isPresent()){
            User user =userRepository.findByUserId(id).get();
            return RegistrationRequest
                    .builder()
                    .email(user.getEmail())
                    .nom(user.getNom())
                    .prenom(user.getPrenom())
                    .adresse(user.getAddresse())
                    .cotisation(user.getCotisation())
                    .role(user.getRole())
                    .numTel(user.getNumTel())
                    .build();
        }
        return RegistrationRequest.builder().build();
    }
}
