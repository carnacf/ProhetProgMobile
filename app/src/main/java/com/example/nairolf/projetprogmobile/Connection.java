package com.example.nairolf.projetprogmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Etudiant;
import android.database.EtudiantManager;
import android.database.Evaluation;
import android.database.EvaluationManager;
import android.database.Question;
import android.database.QuestionManager;
import android.database.Questionnaire;
import android.database.QuestionnaireManager;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        sp = getBaseContext().getSharedPreferences("mode", MODE_PRIVATE);
        String s = sp.getString("mode","test");
        if (s == "dark") {
            setTheme(R.style.ActivityTheme_Primary_Base_Dark);
        }else if(s == "light"){
            setTheme(R.style.ActivityTheme_Primary_Base_Light);
        }

       /*EvaluationManager em = new EvaluationManager(this);
        em.open();

        em.addEvaluation(new Evaluation(0,"Android : Les capteurs","Master1","Informatique"));

        QuestionManager qm = new QuestionManager(this);
        qm.open();
        qm.addQuestion(new Question(0,"Quels est le taux de rafraichissement de type SENSOR_DELAY_NORMAL ?",
                "60 000 microsecondes","200 000 microsecondes","20 000 microsecondes","100 000 microsecondes",2));
        qm.addQuestion(new Question(0,"Que fait le capteur gyroscope ?","Il mesure la rotation en terme de vitesse autour de chaque axe",
                "Il mesure l’acceleration","Il mesure la distance ente l’appareil et un objet ciblé",
                "Il mesure l’angle entre le nord magnétique",1));
        qm.addQuestion(new Question(0,"Quels sont la ou les fonctions à implémenter pour recevoir les données d’un capteur ?",
                "onChanged()","onSensorEvent()","onAccuracyChanged() et onSensorChanged()","onChanged() et onSensorEvent()",3));
        qm.addQuestion(new Question(0,"Parmi ces classes, laquelle ne fait pas partie de l’API Sensor Android ?",
                "SernsorManager","SensorUtility","SensorEvent","Sensor",2));
        qm.addQuestion(new Question(0,"A quoi sert l’element <use-feature> dans la manifest ?",
                "A récupérer les droits au près de l’utilisateur","A permettre l’utilisation du capteur","A sert à déclarer l’utilisation faite des capteurs",
                "A filtrer les appareils possédant le capteur en question dans Google play",4));


        QuestionnaireManager qsm = new QuestionnaireManager(this);
        qsm.open();

        Evaluation e = em.getEvaluation("Android : Les capteurs");

        Question q1 = qm.getQuestion("Quels est le taux de rafraichissement de type SENSOR_DELAY_NORMAL ?");
        Question q2 = qm.getQuestion("Que fait le capteur gyroscope ?");
        Question q3 = qm.getQuestion("Quels sont la ou les fonctions à implémenter pour recevoir les données d’un capteur ?");
        Question q4 = qm.getQuestion("Parmi ces classes, laquelle ne fait pas partie de l’API Sensor Android ?");
        Question q5 = qm.getQuestion("A quoi sert l’element <use-feature> dans la manifest ?");

        qsm.addQuestionnaire(new Questionnaire(0,e.getId_evaluation(),q1.getId_question()));
        qsm.addQuestionnaire(new Questionnaire(0,e.getId_evaluation(),q2.getId_question()));
        qsm.addQuestionnaire(new Questionnaire(0,e.getId_evaluation(),q3.getId_question()));
        qsm.addQuestionnaire(new Questionnaire(0,e.getId_evaluation(),q4.getId_question()));
        qsm.addQuestionnaire(new Questionnaire(0,e.getId_evaluation(),q5.getId_question()));*/

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
                mail.setError("Mail ou mot de passe incorrect !");
                psw.setError("Mail ou mot de passe incorrect !");
            }else{
                SharedPreferences tmp = getBaseContext().getSharedPreferences("user", MODE_PRIVATE);
                tmp.edit().putInt("user_id",e.getId_etudiant()).apply();
                Intent intent = new Intent(this,Connected2.class);
                startActivity(intent);
            }
        }
    }

}
