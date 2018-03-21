package com.example.syxflorent.guzzle.Metier.Medecin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.syxflorent.guzzle.R;

import java.util.ArrayList;

/**
 * Created by syx.florent on 20/03/2018.
 */

public class MedecinAdapter extends ArrayAdapter<Medecin> {
    public MedecinAdapter (Context context, ArrayList<Medecin> medecins) {

        super(context,0,  medecins);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_medecins, parent, false);
        }
        Medecin MedecinSelectionne = getItem(position);

        TextView nomTextView = listItemView.findViewById(R.id.tv_nom_medecin);
        nomTextView.setText(MedecinSelectionne.getNom());

        TextView prenomTextView = listItemView.findViewById(R.id.tv_prenom_medecin);
        prenomTextView.setText(MedecinSelectionne.getPrenom());

        return listItemView;
    }
}
