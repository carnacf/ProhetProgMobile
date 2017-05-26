package com.example.nairolf.projetprogmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Etudiant;
import android.database.EvalEtud;
import android.database.EvalEtudManager;
import android.database.Evaluation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nairolf on 19/05/2017.
 */

public class GridQuestionAdapter extends BaseAdapter {

    private final Evaluation [] lstEvals;
    private final Context c;
    private final Etudiant e;

    public GridQuestionAdapter(Context _c, Evaluation [] evals, Etudiant _e){
        c = _c;
        lstEvals = evals;
        e = _e;
    }

    @Override
    public int getCount() {return lstEvals.length;}

    @Override
    public Object getItem(int position) {return lstEvals[position];}

    @Override
    public long getItemId(int position) {return 0;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = layoutInflater.inflate(R.layout.case_questionnaire, parent, false);

        EvalEtudManager eem = new EvalEtudManager(c);
        eem.open();
        EvalEtud ee = eem.getEvalEtud(lstEvals[position].getId_evaluation(),e.getId_etudiant());


        TextView titleText = (TextView) root.findViewById(R.id.noteQ);
        TextView titleText2 = (TextView) root.findViewById(R.id.titleQ);
        ImageView img = (ImageView) root.findViewById(R.id.imageQ);
        titleText2.setText(lstEvals[position].getNom());
        final int pos = position;
        if(ee == null){
            titleText.setText("Note : Non répondu");
            img.setImageResource(R.mipmap.ic_interrogation);
            root.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    SharedPreferences sp = c.getSharedPreferences("questionnaire",Context.MODE_PRIVATE);
                    sp.edit().putInt("questionnaire",lstEvals[pos].getId_evaluation()).apply();
                    Intent i = new Intent(c,QuestionActivity.class);
                    c.startActivity(i);
                }});

        }else{
            titleText.setText("Note : "+ee.getNote()+"/20");
            if(ee.getNote() >= 10){
                img.setImageResource(R.mipmap.ic_green);
            }else{
                img.setImageResource(R.mipmap.ic_red);
            }
        }

        return root;
    }
}
