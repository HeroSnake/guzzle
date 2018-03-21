package com.example.syxflorent.guzzle.Visiteur;

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

import java.util.HashMap;
import java.util.Map;

public class CreateVisiteurActivity extends AppCompatActivity {
    EditText leNom;
    EditText lePrenom;
    String addVisiteurUrl = "http://192.168.210.2:22545/cakephp/visiteurs/add.json";
    RequestQueue requestQueue;
    Button buttonValider;
    Button buttonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_visiteur);

        leNom =  findViewById(R.id.nomVisiteurCreer);
        lePrenom = findViewById(R.id.prenomVisiteurCreer);
        buttonValider = findViewById(R.id.btnValiderVisiteur);
        buttonRetour = findViewById(R.id.btnRetourCreateVisiteur);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        buttonValider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, addVisiteurUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CreateVisiteurActivity.this, "Nouveau Visiteur " + leNom.getText().toString() + " "
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