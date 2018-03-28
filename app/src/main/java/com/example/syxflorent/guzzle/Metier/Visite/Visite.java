package com.example.syxflorent.guzzle.Metier.Visite;


import com.example.syxflorent.guzzle.Metier.Medecin.Medecin;
import com.example.syxflorent.guzzle.Metier.Visiteur.Visiteur;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Visite implements Serializable{

    private String id;
    private String date;
    private String commentaire;
    private Medecin medecin;
    private String medecin_id;
    private Visiteur visiteur;
    private String visiteur_id;


    public Visite(String unId, String uneDate, String unCommentaire, String unIdMedecin, String unIdVisiteur){
        this.id = unId;
        this.date = uneDate;
        this.commentaire = unCommentaire;
        this.medecin_id = unIdMedecin;
        this.visiteur_id = unIdVisiteur;
    }

    public HashMap<String, String> getVisite(){
        HashMap<String, String> ligne = new HashMap<String, String>();
        ligne.put("id", this.id);
        ligne.put("date", this.date);
        ligne.put("commentaire", this.commentaire);
        ligne.put("medecin_id", this.medecin_id);
        ligne.put("visiteur_id", this.visiteur_id);
        return ligne;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateVisite() {
        String uneDate = date.substring(0, Math.min(date.length(), 10));
        return  uneDate;
    }

    public void setDate(String uneDate) {
        this.date = uneDate;
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

    public String getVisiteur_id() {
        return visiteur_id;
    }

    public void setVisiteur_id(String visiteur_id) { this.visiteur_id = visiteur_id; }

    public String getMedecin_id() { return medecin_id; }

    public void setMedecin_id(String medecin_id) { this.medecin_id = medecin_id; }
}
