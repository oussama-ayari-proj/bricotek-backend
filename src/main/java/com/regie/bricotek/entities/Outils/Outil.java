package com.regie.bricotek.entities.Outils;

import com.regie.bricotek.entities.Pret.Pret;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "outils")
@EntityListeners(AuditingEntityListener.class)
public class Outil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer outilId;
    @Column(unique = true)
    private String codeABarre;
    private String imgPath;
    private String videoPath;
    private boolean etat;
    private String nom;
    @Enumerated(EnumType.STRING)
    private CategorieOutils categorieOutils;
    @OneToMany(mappedBy = "outil")
    private Set<Pret> prets;
}
