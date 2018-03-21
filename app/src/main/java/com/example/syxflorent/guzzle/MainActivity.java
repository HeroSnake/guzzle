package com.example.syxflorent.guzzle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.syxflorent.guzzle.Medecin.MedecinsActivity;
import com.example.syxflorent.guzzle.Visite.VisitesActivity;
import com.example.syxflorent.guzzle.Visiteur.VisiteursActivity;

public class MainActivity extends AppCompatActivity {


    TextView textViewVisiteurs;
    TextView textViewMedecins;
    //TextView textViewVisites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewVisiteurs = findViewById(R.id.tv_visiteurs);
        textViewMedecins = findViewById(R.id.tv_medecins);
        //textViewVisites = findViewById(R.id.tv_visites);

        textViewVisiteurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VisiteursActivity.class);
                startActivity(intent);
            }
        });
        textViewMedecins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MedecinsActivity.class);
                startActivity(intent);
            }
        });
        /*textViewVisites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VisitesActivity.class);
                startActivity(intent);
            }
        });*/
    }
}

