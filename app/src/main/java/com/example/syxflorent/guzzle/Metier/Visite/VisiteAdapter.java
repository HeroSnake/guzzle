package com.example.syxflorent.guzzle.Metier.Visite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.syxflorent.guzzle.Metier.Visiteur.Visiteur;
import com.example.syxflorent.guzzle.R;

import java.util.ArrayList;

/**
 * Created by syx.florent on 13/03/2018.
 */

public class VisiteAdapter extends ArrayAdapter<Visite>{

    public VisiteAdapter(Context context, ArrayList<Visite> visites) {

        super(context,0,  visites);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_visites, parent, false);
        }
        Visite VisiteSelectionne = getItem(position);

        TextView dateTextView = listItemView.findViewById(R.id.tv_date_visite);
        dateTextView.setText(VisiteSelectionne.getDateVisite());

        TextView medecinTextView = listItemView.findViewById(R.id.tv_medecin_visite);
        medecinTextView.setText(VisiteSelectionne.getMedecin().toString());

        TextView visiteurTextView = listItemView.findViewById(R.id.tv_visiteur_visite);
        visiteurTextView.setText(VisiteSelectionne.getVisiteur().toString());

        return listItemView;
    }
}

