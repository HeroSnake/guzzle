package com.example.syxflorent.guzzle.Visite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.syxflorent.guzzle.Metier.Visite.Visite;
import com.example.syxflorent.guzzle.R;

import java.util.HashMap;
import java.util.Map;

public class VisiteDetailActivity extends AppCompatActivity {

    Visite uneVisite;
    String DelVisiteUrl = "http://192.168.210.2:22545/cakephp/visites/delete/";
    RequestQueue requestQueue;
    String idVisite;
    Button buttonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visite_detail);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Intent intent = getIntent();
        uneVisite = (Visite) intent.getSerializableExtra("Visite");
        idVisite = uneVisite.getId();
        buttonRetour = findViewById(R.id.btnRetourDetailsVisite);

        TextView viewDate = findViewById(R.id.tv_date_visite);
        viewDate.setText(uneVisite.getDateVisite());

        TextView viewCommentaire = findViewById(R.id.tv_commentaire_visite);
        viewCommentaire.setText(uneVisite.getCommentaire());

        TextView viewMedecin = findViewById(R.id.tv_medecin_visite);
        viewMedecin.setText(uneVisite.getMedecin().toString());

        TextView viewVisiteur = findViewById(R.id.tv_visiteur_visite);
        viewVisiteur.setText(uneVisite.getVisiteur().toString());

        Button buttonDel = findViewById(R.id.btnSupprimer);

        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.DELETE, DelVisiteUrl + idVisite, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(VisiteDetailActivity.this, "La Visite du " + uneVisite.getDateVisite() + " a été supprimée",
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
    }
}
