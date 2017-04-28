package com.example.nairolf.projetprogmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Connection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

    }

    public void ouvrirIns(View view){
        Intent intent = new Intent(this,Inscription.class);
        startActivity(intent);
    }
}
