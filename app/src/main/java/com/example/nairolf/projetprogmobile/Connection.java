package com.example.nairolf.projetprogmobile;

import android.content.Intent;
import android.database.Etudiant;
import android.database.EtudiantManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


public class Connection extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        /*EtudiantManager em = new EtudiantManager(this);
        em.open();
        em.addEtudiant(new Etudiant(666,"Azerty","test","aaa","a","a,","a","ad"));
        em.addEtudiant(new Etudiant(667,"Test","test","zzz","a","a,","a","ad"));
        Etudiant e = em.getEtudiant(1);
        Log.v("test affichage ",e.getNom());
        em.close();*/
    }

    public void ouvrirIns(View view){
        Intent intent = new Intent(this,Inscription.class);
        startActivity(intent);
    }

}
