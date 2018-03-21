package com.example.syxflorent.guzzle.Metier.Visiteur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.syxflorent.guzzle.R;

import java.util.ArrayList;

/**
 * Created by syx.florent on 13/03/2018.
 */

public class VisiteurAdapter extends ArrayAdapter<Visiteur>{

    public VisiteurAdapter (Context context, ArrayList<Visiteur> visiteurs) {

        super(context,0,  visiteurs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_visiteurs, parent, false);
        }
        Visiteur VisiteurSelectionne = getItem(position);

        TextView nomTextView = listItemView.findViewById(R.id.tv_nom_visiteur);
        nomTextView.setText(VisiteurSelectionne.getNom());

        TextView prenomTextView = listItemView.findViewById(R.id.tv_prenom_visiteur);
        prenomTextView.setText(VisiteurSelectionne.getPrenom());

        return listItemView;
    }
}

