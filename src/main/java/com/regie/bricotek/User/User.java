package com.regie.bricotek.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.regie.bricotek.entities.Pret.Pret;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilisateur")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {
    /*@Id
    @SequenceGenerator(name = "EntityTwoSequence", initialValue = 1000)
    @GeneratedValue(generator = "EntityTwoSequence")
    @Column(name = "numAdh")
    private Integer userId;*/
    @Id
    @Column(name = "numAdh")
    private String userId;
    @PrePersist
    public void generateCustomId() {
        if (this.userId == null) {
            this.userId = IdGenerator.generateNextId();
        }
    }
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(columnDefinition = "boolean default false")
    private Boolean cotisation;
    private String addresse;
    private String numTel;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean enabled;
    private LocalDate dateFinAdh;
    private LocalDate dateAdh;
    @OneToMany(mappedBy = "user")
    private Set<Pret> prets;

    @Override
    public String getName() {
        return email;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private String fullName(){
        return nom+" "+prenom;
    }

}
