package com.example.syxflorent.guzzle.Visite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.syxflorent.guzzle.Metier.GsonRequest;
import com.example.syxflorent.guzzle.Metier.Visite.Visite;
import com.example.syxflorent.guzzle.Metier.Visite.VisiteAdapter;
import com.example.syxflorent.guzzle.Metier.Visite.Visites;
import com.example.syxflorent.guzzle.Metier.VolleyHelper;
import com.example.syxflorent.guzzle.R;

import java.util.ArrayList;

/**
 * Created by syx.florent on 13/03/2018.
 */

public class VisitesActivity extends Activity {
    RequestQueue requestQueue;
    //URL de l'API REST (envoie des données au format JSON)
    String visitesUrl = "http://192.168.210.2:22545/cakephp/visites.json";
    ListView listViewVisites;
    Button btnAddVisite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visites);

        listViewVisites = findViewById(R.id.lv_visites);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        btnAddVisite = findViewById(R.id.add_visite);

        final GsonRequest gsonRequest = new GsonRequest(visitesUrl, Visites.class, null, new Response.Listener<Visites>() {
            @Override
            public void onResponse(Visites visites) {
                ArrayList<Visite> liste = visites.getVisites();
                VisiteAdapter adapterVisite = new VisiteAdapter(getApplicationContext(), liste);
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
                Intent i = new Intent(VisitesActivity.this, VisiteDetailActivity.class);
                i.putExtra("Visite", maVisite);
                startActivity(i);
            }
        });

        btnAddVisite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateVisiteActivity.class);
                startActivity(intent);
            }
        });

        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
    }

}
