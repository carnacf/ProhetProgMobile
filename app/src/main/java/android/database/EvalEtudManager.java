package android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by samuel on 03/05/17.
 */

public class EvalEtudManager {
    private static final String TABLE_NAME = "EvalEtud";
    public static final String KEY_ID_EVALETUD = "id_evalEtud";
    public static final String KEY_ID_EVALUATION_EVALETUD = "id_evaluation";
    public static final String KEY_ID_ETUDIANT_EVALETUD = "id_etudiant";
    public static final String KEY_EVAL_PASSE_EVALETUD = "eval_passe";
    public static final String KEY_NOTE_EVALETUD = "note";

    public static final String CREATE_TABLE_EVALETUD = "CREATE TABLE "+TABLE_NAME+
            "("+
            " "+KEY_ID_EVALETUD+" INTEGER primary key,"+
            " "+KEY_ID_EVALUATION_EVALETUD+" INTEGER,"+
            " "+KEY_ID_ETUDIANT_EVALETUD+" INTEGER,"+
            " "+KEY_EVAL_PASSE_EVALETUD+" BOOLEAN,"+
            " "+KEY_NOTE_EVALETUD+" INTEGER"+
            ");";
    private MySQLite maBaseSQLite;
    private SQLiteDatabase db;

    // Constructeur
    public EvalEtudManager(Context context)
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

    public long addEvalEtud(EvalEtud evalEtud) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_ID_EVALUATION_EVALETUD, evalEtud.getId_evaluation());
        values.put(KEY_ID_ETUDIANT_EVALETUD, evalEtud.getId_etudiant());
        values.put(KEY_EVAL_PASSE_EVALETUD, evalEtud.getEval_passe());
        values.put(KEY_NOTE_EVALETUD, evalEtud.getNote());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modEvalEtud(EvalEtud evalEtud) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_ID_EVALUATION_EVALETUD, evalEtud.getId_evaluation());
        values.put(KEY_ID_ETUDIANT_EVALETUD, evalEtud.getId_etudiant());
        values.put(KEY_EVAL_PASSE_EVALETUD, evalEtud.getEval_passe());
        values.put(KEY_NOTE_EVALETUD, evalEtud.getNote());

        String where = KEY_ID_EVALETUD+" = ?";
        String[] whereArgs = {evalEtud.getId_evalEtud()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supEvalEtud(EvalEtud evalEtud) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_EVALETUD+" = ?";
        String[] whereArgs = {evalEtud.getId_evalEtud()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public EvalEtud getEvalEtud(int id) {
        // Retourne le evalEtud dont l'id est passé en paramètre

        EvalEtud evalEtud=new EvalEtud();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_EVALETUD+"="+id, null);
        if (c.moveToFirst()) {
            evalEtud.setId_evalEtud(c.getInt(c.getColumnIndex(KEY_ID_EVALETUD)));
            evalEtud.setId_evaluation(c.getInt(c.getColumnIndex(KEY_ID_EVALUATION_EVALETUD)));
            evalEtud.setId_etudiant(c.getInt(c.getColumnIndex(KEY_ID_EVALUATION_EVALETUD)));
            evalEtud.setEval_passe(c.getInt(c.getColumnIndex(KEY_EVAL_PASSE_EVALETUD)));
            evalEtud.setId_etudiant(c.getInt(c.getColumnIndex(KEY_NOTE_EVALETUD)));
            c.close();
        } else {
            return null;
        }
        return evalEtud;
    }

    public EvalEtud getEvalEtud(int eval,int etud) {
        // Retourne le evalEtud dont l'id est passé en paramètre



        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_EVALUATION_EVALETUD+"="+eval+
                " AND "+ KEY_ID_ETUDIANT_EVALETUD +"="+etud, null);

        EvalEtud  evalEtud=new EvalEtud();
        if (c.moveToFirst()){
            c.moveToFirst();
            evalEtud = new EvalEtud(c.getInt(c.getColumnIndex(KEY_ID_EVALETUD)),
                        c.getInt(c.getColumnIndex(KEY_ID_EVALUATION_EVALETUD)),
                        c.getInt(c.getColumnIndex(KEY_ID_ETUDIANT_EVALETUD)),
                        c.getInt(c.getColumnIndex(KEY_EVAL_PASSE_EVALETUD)),
                        c.getInt(c.getColumnIndex(KEY_NOTE_EVALETUD)));
            c.close();
        }else {
            return null;
        }
        return evalEtud;
    }

    public Cursor getEvalEtudTable() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

} // class AnimalManager

