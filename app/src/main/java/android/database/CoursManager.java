package android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by samuel on 03/05/17.
 */

public class CoursManager {
    private static final String TABLE_NAME = "Cours";
    public static final String KEY_ID_COURS = "id_cours";
    public static final String KEY_NOM_COURS = "nom";
    public static final String KEY_FORMAT_COURS = "format";
    public static final String KEY_MATIERE_COURS = "matiere";
    public static final String KEY_NIVEAU_COURS = "niveau_etud";
    public static final String KEY_SPECIALITE_COURS = "specialite";

    public static final String CREATE_TABLE_COURS = "CREATE TABLE "+TABLE_NAME+
            "("+
            " "+KEY_ID_COURS+" INTEGER primary key,"+
            " "+KEY_NOM_COURS+" TEXT,"+
            " "+KEY_FORMAT_COURS+" TEXT,"+
            " "+KEY_MATIERE_COURS+" TEXT,"+
            " "+KEY_NIVEAU_COURS+" TEXT,"+
            " "+KEY_SPECIALITE_COURS+" TEXT"+
            ");";
    private MySQLite maBaseSQLite;
    private SQLiteDatabase db;

    // Constructeur
    public CoursManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addCours(Cours cours) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_COURS, cours.getNom());
        values.put(KEY_FORMAT_COURS, cours.getFormat());
        values.put(KEY_MATIERE_COURS,cours.getMatiere());
        values.put(KEY_NIVEAU_COURS, cours.getNiveau_etud());
        values.put(KEY_SPECIALITE_COURS, cours.getSpecialite());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modCours(Cours cours) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_COURS, cours.getNom());
        values.put(KEY_FORMAT_COURS, cours.getFormat());
        values.put(KEY_MATIERE_COURS,cours.getMatiere());
        values.put(KEY_NIVEAU_COURS, cours.getNiveau_etud());
        values.put(KEY_SPECIALITE_COURS, cours.getSpecialite());

        String where = KEY_ID_COURS+" = ?";
        String[] whereArgs = {cours.getId_cours()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supCours(Cours cours) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_COURS+" = ?";
        String[] whereArgs = {cours.getId_cours()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Cours getCours(int id) {
        // Retourne le cours dont l'id est passé en paramètre

        Cours cours=new Cours();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_COURS+"="+id, null);
        if (c.moveToFirst()) {
            cours.setId_cours(c.getInt(c.getColumnIndex(KEY_ID_COURS)));
            cours.setNom(c.getString(c.getColumnIndex(KEY_NOM_COURS)));
            cours.setFormat(c.getString(c.getColumnIndex(KEY_FORMAT_COURS)));
            cours.setMatiere(c.getString(c.getColumnIndex(KEY_MATIERE_COURS)));
            cours.setNiveau_etud(c.getString(c.getColumnIndex(KEY_NIVEAU_COURS)));
            cours.setSpecialite(c.getString(c.getColumnIndex(KEY_SPECIALITE_COURS)));
            c.close();
        } else {
            return null;
        }
        return cours;
    }

    public Cours getCours(String matiere,String niveau,String spe) {
        // Retourne le cours dont l'id est passé en paramètre

        Cours cours=new Cours();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "
                + KEY_MATIERE_COURS +"=\'"+matiere+"\' AND "
                + KEY_NIVEAU_COURS+"=\'"+niveau+"\' AND "
                + KEY_SPECIALITE_COURS+"=\'"+spe+"\'", null);
        if (c.moveToFirst()) {
            cours.setId_cours(c.getInt(c.getColumnIndex(KEY_ID_COURS)));
            cours.setNom(c.getString(c.getColumnIndex(KEY_NOM_COURS)));
            cours.setFormat(c.getString(c.getColumnIndex(KEY_FORMAT_COURS)));
            cours.setMatiere(c.getString(c.getColumnIndex(KEY_MATIERE_COURS)));
            cours.setNiveau_etud(c.getString(c.getColumnIndex(KEY_NIVEAU_COURS)));
            cours.setSpecialite(c.getString(c.getColumnIndex(KEY_SPECIALITE_COURS)));
            c.close();
        } else {
            return null;
        }
        return cours;
    }

    public String[] getMatieres(String niveau, String spe){
        Cursor c = db.rawQuery("SELECT DISTINCT "+KEY_MATIERE_COURS + " FROM " + TABLE_NAME + " WHERE "+KEY_NIVEAU_COURS + "=\'"+niveau+"\' AND "+KEY_SPECIALITE_COURS+"=\'"+spe+"\'",null);
        String matieres[] = new String [c.getCount()];
        int i = 0;
        if (c.getCount() > 0){
            c.moveToFirst();
            do {
                matieres[i] = c.getString(c.getColumnIndex(KEY_MATIERE_COURS));
                i++;
            } while (c.moveToNext());
            c.close();
        }
        return matieres;
    }



    public Cursor getCoursTable() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

} // class AnimalManager

