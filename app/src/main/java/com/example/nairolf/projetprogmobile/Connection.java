package com.example.nairolf.projetprogmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Etudiant;
import android.database.EtudiantManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Connection extends AppCompatActivity{

    SharedPreferences sp;
    SharedPreferences.OnSharedPreferenceChangeListener spChanged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getBaseContext().getSharedPreferences("mode", MODE_PRIVATE);
        String s = sp.getString("mode","test");
        if (s == "dark") {
            setTheme(R.style.ActivityTheme_Primary_Base_Dark);
        }else if(s == "light"){
            setTheme(R.style.ActivityTheme_Primary_Base_Light);
        }



        spChanged = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                  String key) {
                if (key == "mode") {
                    recreate();
                }
            }
        };
        sp.registerOnSharedPreferenceChangeListener(spChanged);
        setContentView(R.layout.activity_connection);

        final AutoCompleteTextView actv = (AutoCompleteTextView)findViewById(R.id.EmailCo);
        actv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String[] tab = new String[]{s.toString()+"@etu.umontpellier.fr"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Connection.this, android.R.layout.simple_dropdown_item_1line,tab );
                actv.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ThreadSensor ts = new ThreadSensor(this);
        ts.run();

    }

    public void ouvrirIns(View view){
        Intent intent = new Intent(this,Inscription.class);
        startActivity(intent);
    }

    public void connection(View view) throws NoSuchAlgorithmException,UnsupportedEncodingException{
        boolean b = true;
        EditText mail = (EditText)findViewById(R.id.EmailCo);
        EditText psw = (EditText)findViewById(R.id.Password);

        if(mail.getText().toString().equals("")){
            mail.setError("Ce champ est vide !");
            b = false;
        }else if(!mail.getText().toString().endsWith("@etu.umontpellier.fr")){
            mail.setError("L'email n'est pas valide !");
            b = false;
        }

        if(psw.getText().toString().equals("")){
            psw.setError("Ce champ est vide !");
            b = false;
        }

        if(b) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String mdp = digest.digest(psw.getText().toString().getBytes("UTF-8")).toString();
            EtudiantManager em = new EtudiantManager(this);
            em.open();
            Etudiant e = em.getEtudiant(mail.getText().toString(),psw.getText().toString());
            if(e == null){
                mail.setError("Mail ou mot de passe incorrecte !");
                psw.setError("Mail ou mot de passe incorrecte !");
            }else{
                SharedPreferences tmp = getBaseContext().getSharedPreferences("user", MODE_PRIVATE);
                tmp.edit().putInt("user_id",e.getId_etudiant()).apply();
                Intent intent = new Intent(this,Connected2.class);
                startActivity(intent);
            }
        }
    }

}
