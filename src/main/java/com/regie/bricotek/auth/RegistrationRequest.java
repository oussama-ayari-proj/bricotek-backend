package com.regie.bricotek.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.regie.bricotek.User.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class RegistrationRequest {
    @NotEmpty(message = "Nom est obligatoire")
    @NotNull(message = "Nom est obligatoire")
    private String nom;
    @NotEmpty(message = "Prenom est obligatoire")
    @NotNull(message = "Prenom est obligatoire")
    private String prenom;
    @Email(message = "Email incorrect")
    @NotEmpty(message = "Email est obligatoire")
    @NotNull(message = "Email est obligatoire")
    private String email;
    @NotEmpty(message = "Password est obligatoire")
    @NotNull(message = "Password est obligatoire")
    private String password;
    private String adresse;
    private String numTel;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFinAdh;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateAdh;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean cotisation;
}
