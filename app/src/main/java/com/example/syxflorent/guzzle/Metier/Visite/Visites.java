package com.example.syxflorent.guzzle.Metier.Visite;

import com.example.syxflorent.guzzle.Metier.Visiteur.Visiteur;

import java.util.ArrayList;
import java.util.HashMap;


public class Visites {
    private ArrayList<Visite> visites;
    public ArrayList<Visite> getVisites() {
        return visites;
    }

    public ArrayList<HashMap<String, String>> getVisiteursArray(){
        ArrayList<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
        for(Visite visite : getVisites()){
            liste.add(visite.getVisite());
        }
        return liste;
    }
}