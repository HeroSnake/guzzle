package com.example.syxflorent.guzzle.Visite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.example.syxflorent.guzzle.R;
import com.example.syxflorent.guzzle.Visiteur.CreateVisiteurActivity;

import java.util.HashMap;
import java.util.Map;

public class CreateVisiteActivity extends AppCompatActivity {
    DatePicker laDate;
    EditText leCommentaire;
    Spinner leMedecin;
    Spinner leVisiteur;
    String addVisiteUrl = "http://192.168.210.2:22545/cakephp/visiteurs/add.json";
    RequestQueue requestQueue;
    Button buttonValider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_visiteur);

        laDate =  findViewById(R.id.dateVisite);
        leCommentaire = findViewById(R.id.commentaireVisite);
        leMedecin = findViewById(R.id.medecinVisite);
        leVisiteur = findViewById(R.id.visiteurVisite);

        buttonValider = findViewById(R.id.btnValiderVisiteur);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        buttonValider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
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
                        parameters.put("date",laDate.toString());
                        parameters.put("commentaire",leCommentaire.getText().toString());
                        parameters.put("medecin", String.valueOf(leMedecin));
                        parameters.put("visiteur", String.valueOf(leVisiteur));
                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });
    }
}