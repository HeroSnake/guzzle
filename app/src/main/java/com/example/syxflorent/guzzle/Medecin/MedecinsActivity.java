package com.example.syxflorent.guzzle.Medecin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
import com.example.syxflorent.guzzle.Visiteur.CreateVisiteurActivity;
import com.example.syxflorent.guzzle.Visiteur.VisiteurDetailActivity;
import com.example.syxflorent.guzzle.Visiteur.VisiteursActivity;

import java.util.ArrayList;

public class MedecinsActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    //URL de l'API REST (envoie des données au format JSON)
    String medecinsUrl = "http://192.168.210.2:22545/cakephp/Medecins.json";
    ListView listViewMedecins;
    Button btnAddMedecin;
    ImageView btnActualizeMedecin;
    Button buttonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecins);

        listViewMedecins = findViewById(R.id.lv_medecins);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        btnAddMedecin = findViewById(R.id.add_medecin);
        btnActualizeMedecin = findViewById(R.id.actualizeMedecins);
        buttonRetour = findViewById(R.id.btnRetourMedecins);

        final GsonRequest gsonRequest = new GsonRequest(medecinsUrl, Medecins.class, null, new Response.Listener<Medecins>() {
            @Override
            public void onResponse(Medecins medecins) {
                ArrayList<Medecin> liste = medecins.getMedecins();
                MedecinAdapter adapterMedecin = new MedecinAdapter(getApplicationContext(), liste);
                listViewMedecins.setAdapter(adapterMedecin);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError != null) Log.e("MedecinsActivity", volleyError.getMessage());
            }
        });

        listViewMedecins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Medecin monMedecin;
                monMedecin = (Medecin) parent.getItemAtPosition(position);
                Intent i = new Intent(MedecinsActivity.this, MedecinDetailActivity.class);
                i.putExtra("Medecin", monMedecin);
                startActivity(i);
            }
        });

        btnAddMedecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateMedecinActivity.class);
                startActivity(intent);
            }
        });
        btnActualizeMedecin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                final GsonRequest gsonRequest = new GsonRequest(medecinsUrl, Medecins.class, null, new Response.Listener<Medecins>() {
                    @Override
                    public void onResponse(Medecins medecins) {
                        ArrayList<Medecin> liste = medecins.getMedecins();
                        MedecinAdapter adapterMedecin = new MedecinAdapter(getApplicationContext(), liste);
                        listViewMedecins.setAdapter(adapterMedecin);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (volleyError != null)
                            Log.e("MedecinsActivity", volleyError.getMessage());
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
        btnActualizeMedecin.callOnClick();
    }
}

