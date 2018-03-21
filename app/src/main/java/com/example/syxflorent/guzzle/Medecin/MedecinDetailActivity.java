package com.example.syxflorent.guzzle.Medecin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.syxflorent.guzzle.Metier.Medecin.Medecin;
import com.example.syxflorent.guzzle.R;

import java.util.HashMap;
import java.util.Map;

public class MedecinDetailActivity extends AppCompatActivity {
    EditText leNom;
    EditText lePrenom;
    Medecin unMedecin;
    String DelMedecinUrl = "http://192.168.210.2:22545/cakephp/medecins/delete/";
    String ModMedecinUrl = "http://192.168.210.2:22545/cakephp/medecins/edit/";
    RequestQueue requestQueue;
    String idMedecin;
    Button buttonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecin_detail);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Intent intent = getIntent();
        unMedecin = (Medecin) intent.getSerializableExtra("Medecin");
        idMedecin = unMedecin.getId();
        leNom = findViewById(R.id.tv_nom_medecin);
        lePrenom = findViewById(R.id.tv_prenom_medecin);

        EditText viewNom = findViewById(R.id.tv_nom_medecin);
        viewNom.setText(unMedecin.getNom());

        EditText viewPrenom = findViewById(R.id.tv_prenom_medecin);
        viewPrenom.setText(unMedecin.getPrenom());

        Button buttonMod = findViewById(R.id.btnModifierMedecin);
        Button buttonDel = findViewById(R.id.btnSupprimerMedecin);
        buttonRetour = findViewById(R.id.btnRetourDetailsMedecin);

        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.DELETE, DelMedecinUrl + idMedecin, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MedecinDetailActivity.this, "Le Médecin " + unMedecin.getNom() + " "
                                        + unMedecin.getPrenom() + " a été supprimé",
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
                StringRequest request = new StringRequest(Request.Method.PUT, ModMedecinUrl + idMedecin, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MedecinDetailActivity.this, "Le Medecin " + unMedecin.getNom() + " "
                                        + unMedecin.getPrenom() + " a été modifié",
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
