package com.example.nairolf.projetprogmobile;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Question;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Nairolf on 25/05/2017.
 */

public class QuestionAdapter extends BaseAdapter{

    public Context c;
    public Question[] lstQ;
    public int[] lstQid;


    public QuestionAdapter(Context c,Question[] Qs,int [] lstQid){
        this.c = c;
        lstQ = Qs;
        this.lstQid = lstQid;
    }

    @Override
    public int getCount() {
        return lstQid.length;
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

    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.case_question, parent, false);

            TextView tv = (TextView) convertView.findViewById(R.id.question_nom);
            RadioButton r1 = (RadioButton) convertView.findViewById(R.id.q1);
            RadioButton r2 = (RadioButton) convertView.findViewById(R.id.q2);
            RadioButton r3 = (RadioButton) convertView.findViewById(R.id.q3);
            RadioButton r4 = (RadioButton) convertView.findViewById(R.id.q4);


            final int pos = position;
            SharedPreferences sp = c.getSharedPreferences("Rep",Context.MODE_PRIVATE);
            sp.edit().putInt(pos+"",-1).apply();
            r1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp = c.getSharedPreferences("Rep",Context.MODE_PRIVATE);
                    sp.edit().putInt(pos+"",1).apply();
                }
            });
            r2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp = c.getSharedPreferences("Rep",Context.MODE_PRIVATE);
                    sp.edit().putInt(pos+"",2).apply();
                }
            });
            r3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp = c.getSharedPreferences("Rep",Context.MODE_PRIVATE);
                    sp.edit().putInt(pos+"",3).apply();
                }
            });
            r4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp = c.getSharedPreferences("Rep",Context.MODE_PRIVATE);
                    sp.edit().putInt(pos+"",4).apply();
                }
            });

            tv.setText(lstQ[position].getNom());
            Log.v("POS",position+"");
            Log.v("POSQ",lstQ[position].getNom());
            r1.setText(lstQ[position].getR1());
            r2.setText(lstQ[position].getR2());
            r3.setText(lstQ[position].getR3());
            r4.setText(lstQ[position].getR4());
        }
        return convertView;

    }
}
