package android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by samuel on 03/05/17.
 */

public class EvaluationManager {
    private static final String TABLE_NAME = "Evaluation";
    public static final String KEY_ID_EVALUATION = "id_evaluation";
    public static final String KEY_NOM_EVALUATION = "nom";
    public static final String KEY_NIVEAU_EVALUATION = "niveau_etud";
    public static final String KEY_SPECIALITE_EVALUATION = "specialite";

    public static final String CREATE_TABLE_EVALUATION = "CREATE TABLE "+TABLE_NAME+
            "("+
            " "+KEY_ID_EVALUATION+" INTEGER primary key,"+
            " "+KEY_NOM_EVALUATION+" TEXT,"+
            " "+KEY_NIVEAU_EVALUATION+" TEXT,"+
            " "+KEY_SPECIALITE_EVALUATION+" TEXT"+
            ");";
    private MySQLite maBaseSQLite;
    private SQLiteDatabase db;

    // Constructeur
    public EvaluationManager(Context context)
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

    public long addEvaluation(Evaluation evaluation) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_EVALUATION, evaluation.getNom());
        values.put(KEY_NIVEAU_EVALUATION, evaluation.getNiveau_etud());
        values.put(KEY_SPECIALITE_EVALUATION, evaluation.getSpecialite());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modEvaluation(Evaluation evaluation) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_EVALUATION, evaluation.getNom());
        values.put(KEY_NIVEAU_EVALUATION, evaluation.getNiveau_etud());
        values.put(KEY_SPECIALITE_EVALUATION, evaluation.getSpecialite());

        String where = KEY_ID_EVALUATION+" = ?";
        String[] whereArgs = {evaluation.getId_evaluation()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supEvaluation(Evaluation evaluation) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_EVALUATION+" = ?";
        String[] whereArgs = {evaluation.getId_evaluation()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Evaluation getEvaluation(int id) {
        // Retourne le evaluation dont l'id est passé en paramètre

        Evaluation evaluation=new Evaluation();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_EVALUATION+"="+id, null);
        if (c.moveToFirst()) {
            evaluation.setId_evaluation(c.getInt(c.getColumnIndex(KEY_ID_EVALUATION)));
            evaluation.setNom(c.getString(c.getColumnIndex(KEY_NOM_EVALUATION)));
            evaluation.setNiveau_etud(c.getString(c.getColumnIndex(KEY_NIVEAU_EVALUATION)));
            evaluation.setSpecialite(c.getString(c.getColumnIndex(KEY_SPECIALITE_EVALUATION)));
            c.close();
        } else {
            return null;
        }
        return evaluation;
    }

    public Cursor getEvaluationTable() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

} // class AnimalManager

