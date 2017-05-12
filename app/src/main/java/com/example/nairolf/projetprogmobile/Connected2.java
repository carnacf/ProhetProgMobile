package com.example.nairolf.projetprogmobile;

import android.content.SharedPreferences;
import android.database.Cours;
import android.database.CoursManager;
import android.database.Etudiant;
import android.database.EtudiantManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.io.IOException;


public class Connected2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private SharedPreferences sp;
    private SharedPreferences.OnSharedPreferenceChangeListener spChanged;
    private GridView gv;
    private Etudiant e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = getBaseContext().getSharedPreferences("mode", MODE_PRIVATE);
        String s = sp.getString("mode","test");
        if (s == "dark") {
            setTheme(R.style.ActivityTheme_Primary_Base_Dark);
        }else if(s == "light"){
            setTheme(R.style.AppTheme_NoActionBar);
        }
        setContentView(R.layout.activity_connected2);

        gv = (GridView) findViewById(R.id.grid);

        spChanged = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,String key) {
                if (key == "mode") {
                    recreate();
                }
            }
        };
        sp.registerOnSharedPreferenceChangeListener(spChanged);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        EtudiantManager em = new EtudiantManager(this);
        em.open();

        SharedPreferences tmp = getBaseContext().getSharedPreferences("user", MODE_PRIVATE);
        int i = tmp.getInt("user_id",0);
        e = em.getEtudiant(i);

        Menu m = navigationView.getMenu();

        String path = e.getNiveau_etud() + "/" + e.getSpecialite();
        String [] list;
        CoursManager cm = new CoursManager(this);
        cm.open();
        try {
            list = getAssets().list(path);
            String cur;
            SharedPreferences current = getBaseContext().getSharedPreferences("current", MODE_PRIVATE);
            cur = current.getString("current","");
            boolean t = false;
            for(String str:list){
                m.add(str);
                if(str.equals(cur) && !t){

                    path+="/"+str;
                    Log.v("test",path);
                    t = true;
                }

            }
            if(!t){
                path += "/"+list[0];
                cur = list[0];
            }
            Log.v("test",path);
            for(String str: getAssets().list(path)){
                Log.v("test",str);
                if(cm.getCours(cur,e.getNiveau_etud(),e.getSpecialite()) == null){
                    cm.addCours(new Cours(0,str.split("\\.")[0],str.split("\\.")[1],cur,e.getNiveau_etud(),e.getSpecialite()));
                }
            }
            GridCoursAdapter gca = new GridCoursAdapter(this,getAssets().list(path));
            gv.setAdapter(gca);
            setTitle(cur);
        }catch (IOException e1) {
            e1.printStackTrace();
        }


        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        String path = e.getNiveau_etud() + "/" + e.getSpecialite()+"/"+item.getTitle();
        CoursManager cm = new CoursManager(this);
        cm.open();
        SharedPreferences current = getBaseContext().getSharedPreferences("current", MODE_PRIVATE);
        current.edit().putString("current",item.getTitle().toString()).apply();

        try {
            for(String str: getAssets().list(path)){
                if(cm.getCours(item.getTitle().toString(),e.getNiveau_etud(),e.getSpecialite()) == null){
                    cm.addCours(new Cours(0,str.split("\\.")[0],str.split("\\.")[1],item.getTitle().toString(),e.getNiveau_etud(),e.getSpecialite()));
                }
            }
            GridCoursAdapter gca = new GridCoursAdapter(this,getAssets().list(path));
            gv.setAdapter(gca);
            setTitle(item.getTitle());
        }catch (IOException e1) {
            e1.printStackTrace();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
