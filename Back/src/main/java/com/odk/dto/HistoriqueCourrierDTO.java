package com.odk.dto;

import java.util.Date;

import lombok.Data;

@Data
public class HistoriqueCourrierDTO {


    private String statut;
    private String commentaire;
    private Date dateAction;
    private String utilisateur;
    private String entite;
}
