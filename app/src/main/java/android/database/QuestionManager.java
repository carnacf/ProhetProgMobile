package android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by samuel on 03/05/17.
 */

public class QuestionManager {
    private static final String TABLE_NAME = "Question";
    public static final String KEY_ID_QUESTION = "id_question";
    public static final String KEY_NOM_QUESTION = "nom";
    public static final String KEY_R1_QUESTION = "r1";
    public static final String KEY_R2_QUESTION = "r2";
    public static final String KEY_R3_QUESTION = "r3";
    public static final String KEY_R4_QUESTION = "r4";
    public static final String KEY_REPONSE_QUESTION = "reponse";

    public static final String CREATE_TABLE_QUESTION = "CREATE TABLE "+TABLE_NAME+
            "("+
            " "+KEY_ID_QUESTION+" INTEGER primary key,"+
            " "+KEY_NOM_QUESTION+" TEXT,"+
            " "+KEY_R1_QUESTION+" TEXT,"+
            " "+KEY_R2_QUESTION+" TEXT,"+
            " "+KEY_R3_QUESTION+" TEXT,"+
            " "+KEY_R4_QUESTION+" TEXT,"+
            " "+KEY_REPONSE_QUESTION+" INTEGER"+
            ");";
    private MySQLite maBaseSQLite;
    private SQLiteDatabase db;

    // Constructeur
    public QuestionManager(Context context)
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

    public long addQuestion(Question question) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_QUESTION, question.getNom());
        values.put(KEY_R1_QUESTION, question.getR1());
        values.put(KEY_R2_QUESTION, question.getR2());
        values.put(KEY_R3_QUESTION, question.getR3());
        values.put(KEY_R4_QUESTION, question.getR4());
        values.put(KEY_REPONSE_QUESTION, question.getReponse());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modQuestion(Question question) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_QUESTION, question.getNom());
        values.put(KEY_R1_QUESTION, question.getR1());
        values.put(KEY_R2_QUESTION, question.getR2());
        values.put(KEY_R3_QUESTION, question.getR3());
        values.put(KEY_R4_QUESTION, question.getR4());
        values.put(KEY_REPONSE_QUESTION, question.getReponse());

        String where = KEY_ID_QUESTION+" = ?";
        String[] whereArgs = {question.getId_question()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supQuestion(Question question) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_QUESTION+" = ?";
        String[] whereArgs = {question.getId_question()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Question getQuestion(int id) {
        // Retourne le question dont l'id est passé en paramètre

        Question question=new Question();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_QUESTION+"="+id, null);
        if (c.moveToFirst()) {
            question.setId_question(c.getInt(c.getColumnIndex(KEY_ID_QUESTION)));
            question.setNom(c.getString(c.getColumnIndex(KEY_NOM_QUESTION)));
            question.setR1(c.getString(c.getColumnIndex(KEY_R1_QUESTION)));
            question.setR2(c.getString(c.getColumnIndex(KEY_R2_QUESTION)));
            question.setR3(c.getString(c.getColumnIndex(KEY_R3_QUESTION)));
            question.setR4(c.getString(c.getColumnIndex(KEY_R4_QUESTION)));
            question.setReponse(c.getInt(c.getColumnIndex(KEY_REPONSE_QUESTION)));
            c.close();
        } else {
            return null;
        }
        return question;
    }

    public Cursor getQuestionTable() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

} // class AnimalManager

