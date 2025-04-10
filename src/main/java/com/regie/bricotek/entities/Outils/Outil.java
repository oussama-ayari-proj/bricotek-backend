package com.regie.bricotek.entities.Outils;

import com.regie.bricotek.User.IdGenerator;
import com.regie.bricotek.entities.Pret.Pret;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
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
    private String outilId;
    @PrePersist
    public void generateCustomId() {
        if (this.outilId == null) {
            this.outilId = IdGenerator.generateNextOutilId();
        }
    }
    private String imgPath;
    private String videoPath;
    private boolean etat;
    private String nom;
    private String marque;
    private String codeRangement;
    @Enumerated(EnumType.STRING)
    private CategorieOutils categorieOutils;
    @OneToMany(mappedBy = "outil",fetch = FetchType.LAZY)
    private Set<Pret> prets;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    ImageData imageData;
}
