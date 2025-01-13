package com.regie.bricotek.entities.Pret;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PretResponse {
    private Integer pretId;
    private String user;
    private String outil;
    private String dateRetour;
}
