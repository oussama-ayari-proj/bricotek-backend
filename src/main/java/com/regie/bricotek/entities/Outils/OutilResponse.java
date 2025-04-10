package com.regie.bricotek.entities.Outils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OutilResponse {
    private String outilId;
    private String videoPath;
    private String marque;
    private String code_rangement;
    private boolean etat;
    private String nom;
    private Long image;
    private CategorieOutils categorieOutils;
}
