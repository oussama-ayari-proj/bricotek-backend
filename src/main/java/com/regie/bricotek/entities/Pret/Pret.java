package com.regie.bricotek.entities.Pret;

import com.regie.bricotek.User.User;
import com.regie.bricotek.entities.Outils.Outil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private Date dateRetour=new Date();

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "outilId")
    private Outil outil;
}
