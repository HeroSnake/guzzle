package com.example.syxflorent.guzzle.Metier.Visite;


import com.example.syxflorent.guzzle.Metier.Medecin.Medecin;
import com.example.syxflorent.guzzle.Metier.Visiteur.Visiteur;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Visite implements Serializable{

    private String id;
    private String date;
    private String commentaire;
    private Medecin medecin;
    private int medecin_id;
    private Visiteur visiteur;
    private int visiteur_id;


    public Visite(String unId, String uneDate, String unCommentaire, Medecin unMedecin, int unIdMedecin, Visiteur unVisiteur, int unIdVisiteur){
        this.id = unId;
        this.date = uneDate;
        this.commentaire = unCommentaire;
        this.medecin = unMedecin;
        this.medecin_id = unIdMedecin;
        this.visiteur = unVisiteur;
        this.visiteur_id = unIdVisiteur;
    }

    public HashMap<String, String> getVisite(){
        HashMap<String, String> ligne = new HashMap<String, String>();
        ligne.put("id", this.id);
        ligne.put("date", this.date);
        ligne.put("commentaire", this.commentaire);
        ligne.put("medecin", this.medecin.toString());
        ligne.put("visite", this.visiteur.toString());
        return ligne;
    }

    @Override
    public String toString() {
        return "Date : " + date + '\''
                + "Id : " + medecin_id + '\''
                + "Médecin : " + medecin.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateVisite() { return  "Date : " + date;}

    public void setDateVisite(String uneDateVisite) {
        this.date = uneDateVisite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String unCommentaire) {
        this.commentaire = unCommentaire;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin unMedecin) {
        this.medecin = unMedecin;
    }

    public Visiteur getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(Visiteur unVisiteur) {
        this.visiteur = unVisiteur;
    }
}
