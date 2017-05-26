package com.example.nairolf.projetprogmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.Etudiant;
import android.database.EtudiantManager;
import android.database.EvalEtud;
import android.database.EvalEtudManager;
import android.database.Evaluation;
import android.database.EvaluationManager;
import android.database.Question;
import android.database.QuestionManager;
import android.database.Questionnaire;
import android.database.QuestionnaireManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {

    private Etudiant e;
    private Evaluation eval;
    private int[] lstQid;
    private Question [] lstQ;
    private ListView lv;
    private SharedPreferences sp;
    private SharedPreferences.OnSharedPreferenceChangeListener spChanged;
    int note = 0;
    boolean answered = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        sp = getBaseContext().getSharedPreferences("mode", MODE_PRIVATE);
        String s = sp.getString("mode","test");
        if (s == "dark") {
            setTheme(R.style.ActivityTheme_Primary_Base_Dark2);
        }else if(s == "light"){
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_question);

        spChanged = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,String key) {
                if (key == "mode") {
                    recreate();
                }
            }
        };
        sp.registerOnSharedPreferenceChangeListener(spChanged);

        SharedPreferences spE = getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences spQ = getSharedPreferences("questionnaire",MODE_PRIVATE);

        int idE = spE.getInt("user_id",0);
        int idQ = spQ.getInt("questionnaire",0);

        EtudiantManager em = new EtudiantManager(this);
        em.open();
        e = em.getEtudiant(idE);

        EvaluationManager evm = new EvaluationManager(this);
        evm.open();
        eval = evm.getEvaluation(idQ);

        QuestionnaireManager qum = new QuestionnaireManager(this);
        qum.open();

        lstQid = qum.getidQuestions(eval.getId_evaluation());
        Log.v("IDEVAL",eval.getId_evaluation()+"");



        QuestionManager qm = new QuestionManager(this);
        qm.open();
        lstQ = new Question[lstQid.length];
        for(int i = 0;i<lstQ.length;i++){
            lstQ[i] = qm.getQuestion(lstQid[i]);
            Log.v("QUESTION",lstQ[i].getNom());
        }

        Log.v("lstQ",lstQ.length+"");

        lv = (ListView) findViewById(R.id.list);
        QuestionAdapter qa = new QuestionAdapter(this,lstQ,lstQid);
        lv.setAdapter(qa);
        this.setTitle(eval.getNom());

    }

   public int validerQ(View view) {
        if (!answered) {
            note = 0;
            int rep[] = new int[lstQ.length];
            for (int i = 0; i < lstQ.length; i++) {
                SharedPreferences sp = getSharedPreferences("Rep", Context.MODE_PRIVATE);
                rep[i] = sp.getInt(i + "", 0);
                if (rep[i] <= 0 || rep[i] >4) {
                    Toast t = Toast.makeText(this, "Vous n'avez pas répondu à toute les questions !", Toast.LENGTH_SHORT);
                    t.show();
                    return -1;
                }
                if (rep[i] == lstQ[i].getReponse()) {
                    note++;
                }
            }
            note = note * 20 / lstQ.length;
            ((Button) findViewById(R.id.buttonValQ)).setText("Ok");
            setTitle(this.getTitle() + " : Récapitulatif");
            answered = true;
            QuestionAdapterRecap qar = new QuestionAdapterRecap(lstQ, rep, this);
            lv.setAdapter(qar);
            return 1;
        }else{
            EvalEtudManager eem = new EvalEtudManager(this);
            eem.open();
            EvalEtud ee = new EvalEtud(0,eval.getId_evaluation(),e.getId_etudiant(),1,note);
            eem.addEvalEtud(ee);
            Intent i = new Intent(this,Connected2.class);
            startActivity(i);
            return 1;
        }
    }
}
