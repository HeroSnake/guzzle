package com.example.syxflorent.guzzle.Visiteur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.syxflorent.guzzle.Metier.GsonRequest;
import com.example.syxflorent.guzzle.Metier.Medecin.Medecin;
import com.example.syxflorent.guzzle.Metier.Medecin.MedecinAdapter;
import com.example.syxflorent.guzzle.Metier.Medecin.Medecins;
import com.example.syxflorent.guzzle.Metier.Visiteur.Visiteur;
import com.example.syxflorent.guzzle.Metier.Visiteur.VisiteurAdapter;
import com.example.syxflorent.guzzle.Metier.Visiteur.Visiteurs;
import com.example.syxflorent.guzzle.Metier.VolleyHelper;
import com.example.syxflorent.guzzle.R;

import java.util.ArrayList;

public class VisiteursActivity extends Activity {

    RequestQueue requestQueue;
    //URL de l'API REST (envoie des données au format JSON)
    String visiteursUrl = "http://192.168.210.2:22545/cakephp/visiteurs.json";
    ListView listViewVisiteurs;
    Button btnAddVisiteur;
    ImageView actualizeVisiteur;
    Button buttonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiteurs);

        listViewVisiteurs = findViewById(R.id.lv_visiteurs);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        btnAddVisiteur = findViewById(R.id.add_visiteur);
        buttonRetour = findViewById(R.id.btnRetourVisiteurs);
        actualizeVisiteur = findViewById(R.id.actualizeVisiteurs);

        final GsonRequest gsonRequest = new GsonRequest(visiteursUrl, Visiteurs.class, null, new Response.Listener<Visiteurs>() {
            @Override
            public void onResponse(Visiteurs visiteurs) {
                ArrayList<Visiteur> liste = visiteurs.getVisiteurs();
                VisiteurAdapter adapterVisiteur = new VisiteurAdapter(getApplicationContext(), liste);
                listViewVisiteurs.setAdapter(adapterVisiteur);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError != null) Log.e("VisiteursActivity", volleyError.getMessage());
            }
        });

        listViewVisiteurs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Visiteur monVisiteur;
                monVisiteur = (Visiteur) parent.getItemAtPosition(position);
                Intent i = new Intent(VisiteursActivity.this, VisiteurDetailActivity.class);
                i.putExtra("Visiteur", monVisiteur);
                startActivity(i);
            }
        });

        btnAddVisiteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateVisiteurActivity.class);
                startActivity(intent);
            }
        });
        actualizeVisiteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final GsonRequest gsonRequest = new GsonRequest(visiteursUrl, Visiteurs.class, null, new Response.Listener<Visiteurs>() {
                    @Override
                    public void onResponse(Visiteurs visiteurs) {
                        ArrayList<Visiteur> liste = visiteurs.getVisiteurs();
                        VisiteurAdapter adapterVisiteur = new VisiteurAdapter(getApplicationContext(), liste);
                        listViewVisiteurs.setAdapter(adapterVisiteur);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (volleyError != null)
                            Log.e("VisiteursActivity", volleyError.getMessage());
                    }
                });
                VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
            }
        });

        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        actualizeVisiteur.callOnClick();
    }
}