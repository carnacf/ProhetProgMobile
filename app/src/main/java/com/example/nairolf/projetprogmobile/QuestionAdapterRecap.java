package com.example.nairolf.projetprogmobile;

import android.content.Context;
import android.database.Question;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Nairolf on 26/05/2017.
 */

public class QuestionAdapterRecap extends BaseAdapter {

    private final Question[] q;
    private final int[] rep;
    private final Context c;

    public QuestionAdapterRecap(Question[] _q, int[] _rep, Context _c){
        rep = _rep;
        q = _q;
        c = _c;
    }

    @Override
    public int getCount() {
        return q.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.case_recap, parent, false);

            TextView tv1 = (TextView) convertView.findViewById(R.id.question_nomR);
            TextView tv2= (TextView) convertView.findViewById(R.id.question_BonneR);
            TextView tv3 = (TextView) convertView.findViewById(R.id.question_BonneRD);
            TextView tv4 = (TextView) convertView.findViewById(R.id.mauvaiseRep);
            TextView tv5 = (TextView) convertView.findViewById(R.id.mauvaiseRepD);

            tv1.setText(q[position].getNom());
            tv2.setText("La bonne réponse :");
            switch(q[position].getReponse()){
                case 1:
                    tv3.setText(q[position].getR1());
                    break;
                case 2:
                    tv3.setText(q[position].getR2());
                    break;
                case 3:
                    tv3.setText(q[position].getR4());
                    break;
                case 4:
                    tv3.setText(q[position].getR4());
                    break;
            }
            tv3.setTextColor(Color.GREEN);

            tv4.setText("Vôtre réponse :");
            switch(rep[position]){
                case 1:
                    tv5.setText(q[position].getR1());
                    break;
                case 2:
                    tv5.setText(q[position].getR2());
                    break;
                case 3:
                    tv5.setText(q[position].getR4());
                    break;
                case 4:
                    tv5.setText(q[position].getR4());
                    break;
            }
            if(rep[position] == q[position].getReponse()){
                tv5.setTextColor(Color.GREEN);
            }else{
                tv5.setTextColor(Color.RED);
            }
        }
        return convertView;
    }
}
