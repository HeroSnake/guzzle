package com.example.syxflorent.guzzle.Visiteur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.syxflorent.guzzle.Metier.GsonRequest;
import com.example.syxflorent.guzzle.Metier.Visite.Visite;
import com.example.syxflorent.guzzle.Metier.Visite.VisiteAdapter;
import com.example.syxflorent.guzzle.Metier.Visite.Visites;
import com.example.syxflorent.guzzle.Metier.Visiteur.Visiteur;
import com.example.syxflorent.guzzle.Metier.VolleyHelper;
import com.example.syxflorent.guzzle.R;
import com.example.syxflorent.guzzle.Visite.CreateVisiteActivity;
import com.example.syxflorent.guzzle.Visite.VisiteDetailActivity;
import com.example.syxflorent.guzzle.Visite.VisitesActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VisiteurDetailActivity extends AppCompatActivity {

    EditText leNom, lePrenom;
    Visiteur unVisiteur;
    String ModVisiteurUrl = "http://192.168.210.2:22545/cakephp/visiteurs/edit/";
    String DelVisiteurUrl = "http://192.168.210.2:22545/cakephp/visiteurs/delete/";
    String visitesUrl = "http://192.168.210.2:22545/cakephp/visites.json";
    RequestQueue requestQueue;
    ListView listViewVisites;
    String idVisiteur;
    Button buttonRetour, buttonCreerVisite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiteur_detail);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Intent intent = getIntent();
        unVisiteur = (Visiteur) intent.getSerializableExtra("Visiteur");
        idVisiteur = unVisiteur.getId();
        leNom = findViewById(R.id.tv_nom_visiteur);
        lePrenom = findViewById(R.id.tv_prenom_visiteur);
        listViewVisites = findViewById(R.id.lv_visites);
        EditText viewNom = findViewById(R.id.tv_nom_visiteur);
        viewNom.setText(unVisiteur.getNom());
        EditText viewPrenom = findViewById(R.id.tv_prenom_visiteur);
        viewPrenom.setText(unVisiteur.getPrenom());
        Button buttonMod = findViewById(R.id.btnModifierVisiteur);
        Button buttonDel = findViewById(R.id.btnSupprimerVisiteur);
        buttonRetour = findViewById(R.id.btnRetourDetailsVisiteurs);
        buttonCreerVisite = findViewById(R.id.btnCreerVisite);

        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.DELETE, DelVisiteurUrl + idVisiteur, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(VisiteurDetailActivity.this, "Le Visiteur " + unVisiteur.getNom() + " "
                                        + unVisiteur.getPrenom() + " a été supprimé",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<>();
                        parameters.put("nom", leNom.getText().toString());
                        parameters.put("prenom", lePrenom.getText().toString());
                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });
        buttonMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.PUT, ModVisiteurUrl + idVisiteur, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(VisiteurDetailActivity.this, "Le Visiteur " + unVisiteur.getNom() + " "
                                        + unVisiteur.getPrenom() + " a été modifié",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<>();
                        parameters.put("nom",leNom.getText().toString());
                        parameters.put("prenom",lePrenom.getText().toString());
                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listViewVisites = findViewById(R.id.lv_visites);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        final GsonRequest gsonRequest = new GsonRequest(visitesUrl, Visites.class, null, new Response.Listener<Visites>() {
            @Override
            public void onResponse(Visites visites) {
                ArrayList<Visite> liste = visites.getVisites();
                ArrayList<Visite> listeTri = new ArrayList<>();
                for(Visite element : liste){
                    if( element.getVisiteur_id().equals(unVisiteur.getId())){
                        listeTri.add(element);
                    }
                }
                VisiteAdapter adapterVisite = new VisiteAdapter(getApplicationContext(), listeTri);
                listViewVisites.setAdapter(adapterVisite);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError != null) Log.e("VisitesActivity", volleyError.getMessage());
            }
        });

        listViewVisites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Visite maVisite;
                maVisite = (Visite) parent.getItemAtPosition(position);
                Intent i = new Intent(VisiteurDetailActivity.this, VisiteDetailActivity.class);
                i.putExtra("Visite", maVisite);
                startActivity(i);
            }
        });

        buttonCreerVisite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateVisiteActivity.class);
                intent.putExtra("Visiteur", unVisiteur);
                startActivity(intent);
            }
        });

        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
    }
}

