package com.regie.bricotek.User;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class UserResponse {
    private Integer userId;
    private String nom;
    private String prenom;
    private Date dateOfBirth;
    private String email;
    private String addresse;
    private String numAdh;
    private String numTel;
    private Role role;
    private boolean enabled;


    public static UserResponse fromEntity(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .dateOfBirth(user.getDateOfBirth())
                .email(user.getEmail())
                .addresse(user.getAddresse())
                .numAdh(user.getNumAdh())
                .numTel(user.getNumTel())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .build();
    }

}
