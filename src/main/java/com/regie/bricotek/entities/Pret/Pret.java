package com.regie.bricotek.entities.Pret;

import com.regie.bricotek.User.User;
import com.regie.bricotek.entities.Outils.Outil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Prêts")
@EntityListeners(AuditingEntityListener.class)
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pretId;

    @Temporal(TemporalType.DATE)
    private LocalDate dateRetour;

    private LocalDateTime dateDemande;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "outilId")
    private Outil outil;

    @Enumerated(EnumType.STRING)
    private Etat etat;
}
