package com.example.nairolf.projetprogmobile;

import android.content.Context;
import android.database.CoursManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nairolf on 11/05/2017.
 */

public class GridCoursAdapter extends BaseAdapter {

    String [] listCours;
    Context context;

    public GridCoursAdapter(Context c,String[] strs){
        context = c;
        listCours = strs;
    }

    @Override
    public int getCount() {
        return listCours.length;
    }

    @Override
    public Object getItem(int position) {
        return listCours[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = layoutInflater.inflate(R.layout.case_cours, parent, false);

        String s = listCours[position];

        TextView titleText = (TextView) root.findViewById(R.id.title);
        ImageView img = (ImageView) root.findViewById(R.id.image);

        titleText.setText(s);
        if(s.endsWith("pdf")) {
            img.setImageResource(R.mipmap.ic_pdf);
        }else{
            img.setImageResource(R.mipmap.ic_img);
        }
        return root;
    }
}
