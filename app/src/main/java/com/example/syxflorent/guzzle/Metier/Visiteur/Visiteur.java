package com.example.syxflorent.guzzle.Metier.Visiteur;


import com.example.syxflorent.guzzle.Metier.Visite.Visite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Visiteur implements Serializable{

    private String id;
    private String nom;
    private String prenom;
    private ArrayList<Visite> lesVisites;


    public Visiteur (String unId, String unNom, String unPrenom){
        this.id = unId;
        this.nom = unNom;
        this.prenom = unPrenom;
        this.lesVisites = new ArrayList<>();
    }

    public HashMap<String, String> getVisiteur(){
        HashMap<String, String> ligne = new HashMap<String, String>();
        ligne.put("id", this.id);
        ligne.put("nom", this.nom);
        ligne.put("prenom", this.prenom);
        return ligne;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }

    public String getId() {
        return id;
    }
    public ArrayList<Visite> getLesVisites(){ return lesVisites; }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
