package com.example.nairolf.projetprogmobile;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cours;
import android.database.CoursManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Nairolf on 11/05/2017.
 */

public class GridCoursAdapter extends BaseAdapter {

    Context context;
    String path;
    Cours[] listCours;

    public GridCoursAdapter(Context c, String _path, Cours[] cours){
        context = c;
        path = _path;
        listCours = cours;
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

        final String s = listCours[position].getNom() +"."+listCours[position].getFormat();
        TextView titleText = (TextView) root.findViewById(R.id.title);
        ImageView img = (ImageView) root.findViewById(R.id.image);

        titleText.setText(listCours[position].getNom());
        if(listCours[position].getFormat().equals("pdf")) {
            img.setImageResource(R.mipmap.ic_pdf);
        }else{
            img.setImageResource(R.mipmap.ic_img);
        }
        final String form = listCours[position].getFormat();
        root.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                AssetManager assetManager = context.getAssets();

                InputStream in = null;
                OutputStream out = null;
                File file = new File(context.getFilesDir(),s);
                try
                {
                    in = assetManager.open(path+"/"+s);
                    out = context.openFileOutput(file.getName(),Context.MODE_WORLD_READABLE);

                    copyFile(in, out);
                    in.close();
                    in = null;
                    out.flush();
                    out.close();
                    out = null;
                } catch (Exception e)
                {
                    Log.e("tag", e.getMessage());
                }

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(
                        Uri.parse("file://" + context.getFilesDir() +"/"+s),
                        "application/"+form);

                context.startActivity(intent);
        }});
        return root;
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }
}
