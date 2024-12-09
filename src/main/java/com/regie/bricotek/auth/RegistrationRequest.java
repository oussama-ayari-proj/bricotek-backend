package com.regie.bricotek.auth;

import com.regie.bricotek.User.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Role role;
}
