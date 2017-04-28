package com.example.nairolf.projetprogmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

public class Inscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        Spinner dropdown = (Spinner)findViewById(R.id.Etude);
        String[] items = new String[]{"License", "Master", "Doctorat"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        DatePicker date = (DatePicker)findViewById(R.id.datepicker);
        date.setMaxDate(new Date().getTime());
        date.init(1990,1,1,null);
    }

    public void retourCo(View view){
        Intent intent = new Intent(this,Connection.class);
        startActivity(intent);
    }

    public void valider(View view){
        EditText nom = (EditText)findViewById(R.id.NomIns);
        if(nom.getText().toString().equals("")){
            nom.setError("Ce champ est obligatoire !");
        }
        EditText prenom = (EditText)findViewById(R.id.PrenomIns);
        if(prenom.getText().toString().equals("")){
            prenom.setError("Ce champ est obligatoire !");
        }

        EditText pass = (EditText)findViewById(R.id.MDPIns);
        EditText passVal = (EditText)findViewById(R.id.MDPInsVal);
        if(pass.getText().toString().equals("")){
            pass.setError("Ce champ est obligatoire !");
        }

        if(passVal.getText().toString().equals("")){
            passVal.setError("Ce champ est obligatoire !");
        }

        if(!pass.getText().toString().equals(passVal.getText().toString())){
            passVal.setError("Les deux mots de passe doivent être identques !");
        }

        EditText mail = (EditText)findViewById(R.id.MailIns);
        if(mail.getText().toString().equals("")){
            mail.setError("Les deux mots de passe doivent être identques !");
        }else if(!mail.getText().toString().endsWith("@etu.umontpellier.fr")){
          mail.setError("L'adresse mail n'est pas correcte !");
        }


    }
}
