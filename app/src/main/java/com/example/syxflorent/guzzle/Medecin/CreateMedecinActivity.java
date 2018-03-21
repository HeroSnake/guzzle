package com.example.syxflorent.guzzle.Medecin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.syxflorent.guzzle.R;
import com.example.syxflorent.guzzle.Visiteur.CreateVisiteurActivity;
import com.example.syxflorent.guzzle.Visiteur.VisiteursActivity;

import java.util.HashMap;
import java.util.Map;

public class CreateMedecinActivity extends AppCompatActivity {
    EditText leNom;
    EditText lePrenom;
    String addMedecinUrl = "http://192.168.210.2:22545/cakephp/medecins/add.json";
    RequestQueue requestQueue;
    Button buttonValider;
    Button buttonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_medecin);

        leNom =  findViewById(R.id.nomMedecinCreer);
        lePrenom = findViewById(R.id.prenomMedecinCreer);
        buttonValider = findViewById(R.id.btnValiderMedecin);
        buttonRetour = findViewById(R.id.btnRetourCreateMedecin);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        buttonValider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, addMedecinUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CreateMedecinActivity.this, "Nouveau Médecin " + leNom.getText().toString() + " "
                                        + lePrenom.getText().toString() + " a été créé",
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
    }
}

