package com.example.syxflorent.guzzle.Visite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.syxflorent.guzzle.Metier.GsonRequest;
import com.example.syxflorent.guzzle.Metier.Medecin.Medecin;
import com.example.syxflorent.guzzle.Metier.Medecin.MedecinAdapter;
import com.example.syxflorent.guzzle.Metier.Medecin.Medecins;
import com.example.syxflorent.guzzle.Metier.Visiteur.Visiteur;
import com.example.syxflorent.guzzle.Metier.VolleyHelper;
import com.example.syxflorent.guzzle.R;

import java.util.HashMap;
import java.util.Map;

public class CreateVisiteActivity extends AppCompatActivity {
    EditText leCommentaire, laDate;
    Visiteur unVisiteur;
    Spinner spinnerMedecin;
    String addVisiteUrl = "http://192.168.210.2:22545/cakephp/visites/add.json";
    String medecinsUrl = "http://192.168.210.2:22545/cakephp/medecins.json";
    RequestQueue requestQueue;
    Button buttonValider;
    Medecin leMedecin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_visite);

        Intent intent = getIntent();
        unVisiteur = (Visiteur) intent.getSerializableExtra("Visiteur");

        laDate =  findViewById(R.id.dateVisite);
        leCommentaire = findViewById(R.id.commentaireVisite);
        spinnerMedecin = findViewById(R.id.spinnerMedecinVisite);
        buttonValider = findViewById(R.id.btnValiderVisite);
        requestQueue = Volley.newRequestQueue(getApplicationContext());


        final GsonRequest gsonRequest = new GsonRequest(medecinsUrl, Medecins.class, null, new Response.Listener<Medecins>() {
            @Override
            public void onResponse(Medecins medecins) {

                MedecinAdapter medecinAdapter = new MedecinAdapter(getApplicationContext(), medecins.getMedecins());
                medecinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerMedecin.setAdapter(medecinAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError != null)
                    Log.e("CreateVisiteActivity", volleyError.getMessage());
            }
        });
        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leMedecin = (Medecin)spinnerMedecin.getSelectedItem();
                StringRequest request = new StringRequest(Request.Method.POST, addVisiteUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CreateVisiteActivity.this, "Nouvelle Visite du " + laDate + " créée",
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
                        parameters.put("date",laDate + "T15:43:00+00:00");
                        parameters.put("commentaire",leCommentaire.getText().toString());
                        parameters.put("medecin_id", leMedecin.getId());
                        parameters.put("visiteur_id", unVisiteur.getId());
                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });
        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
    }
}