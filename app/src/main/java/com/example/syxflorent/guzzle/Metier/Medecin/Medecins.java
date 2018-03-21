package com.example.syxflorent.guzzle.Metier.Medecin;

import com.example.syxflorent.guzzle.Metier.Visiteur.Visiteur;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by syx.florent on 20/03/2018.
 */

public class Medecins {
    private ArrayList<Medecin> medecins;

    public ArrayList<Medecin> getMedecins() {
        return medecins;
    }

    public ArrayList<HashMap<String, String>> getMedecinsArray(){
        ArrayList<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
        for(Medecin medecin : getMedecins()){
            liste.add(medecin.getMedecin());
        }
        return liste;
    }
}
