package com.example.syxflorent.guzzle.Metier.Medecin;


import java.io.Serializable;
import java.util.HashMap;

public class Medecin implements Serializable{

    private String id;
    private String nom;
    private String prenom;


    public Medecin(String unId, String unNom, String unPrenom){
        this.id = unId;
        this.nom = unNom;
        this.prenom = unPrenom;
    }

    public HashMap<String, String> getMedecin(){
        HashMap<String, String> ligne = new HashMap<String, String>();
        ligne.put("id", this.id);
        ligne.put("nom", this.nom);
        ligne.put("prenom", this.prenom);
        return ligne;
    }

    @Override
    public String toString() {
        return "Médecin : " + nom + " " + prenom;
    }

    public String getId() {
        return id;
    }

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
