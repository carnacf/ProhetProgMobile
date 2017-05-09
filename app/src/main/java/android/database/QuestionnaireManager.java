package android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by samuel on 03/05/17.
 */

public class QuestionnaireManager {
    private static final String TABLE_NAME = "Questionnaire";
    public static final String KEY_ID_QUESTIONNAIRE = "id_questionnaire";
    public static final String KEY_ID_EVALUATION_QUESTIONNAIRE = "id_evaluation";
    public static final String KEY_ID_QUESTION_QUESTIONNAIRE = "id_question";

    public static final String CREATE_TABLE_QUESTIONNAIRE = "CREATE TABLE "+TABLE_NAME+
            "("+
            " "+KEY_ID_QUESTIONNAIRE+" INTEGER primary key,"+
            " "+KEY_ID_EVALUATION_QUESTIONNAIRE+" INTEGER,"+
            " "+KEY_ID_QUESTION_QUESTIONNAIRE+" INTEGER"+
            ");";
    private MySQLite maBaseSQLite;
    private SQLiteDatabase db;

    // Constructeur
    public QuestionnaireManager(Context context)
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

    public long addQuestionnaire(Questionnaire questionnaire) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_ID_EVALUATION_QUESTIONNAIRE, questionnaire.getId_evaluation());
        values.put(KEY_ID_QUESTION_QUESTIONNAIRE, questionnaire.getId_question());

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return db.insert(TABLE_NAME,null,values);
    }

    public int modQuestionnaire(Questionnaire questionnaire) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_ID_EVALUATION_QUESTIONNAIRE, questionnaire.getId_evaluation());
        values.put(KEY_ID_QUESTION_QUESTIONNAIRE, questionnaire.getId_question());

        String where = KEY_ID_QUESTIONNAIRE+" = ?";
        String[] whereArgs = {questionnaire.getId_questionnaire()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int supQuestionnaire(Questionnaire questionnaire) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_ID_QUESTIONNAIRE+" = ?";
        String[] whereArgs = {questionnaire.getId_questionnaire()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Questionnaire getQuestionnaire(int id) {
        // Retourne le questionnaire dont l'id est passé en paramètre

        Questionnaire questionnaire=new Questionnaire();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_QUESTIONNAIRE+"="+id, null);
        if (c.moveToFirst()) {
            questionnaire.setId_questionnaire(c.getInt(c.getColumnIndex(KEY_ID_QUESTIONNAIRE)));
            questionnaire.setId_evaluation(c.getInt(c.getColumnIndex(KEY_ID_EVALUATION_QUESTIONNAIRE)));
            questionnaire.setId_question(c.getInt(c.getColumnIndex(KEY_ID_QUESTION_QUESTIONNAIRE)));
            c.close();
        } else {
            return null;
        }
        return questionnaire;
    }

    public Cursor getQuestionnaireTable() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }

} // class AnimalManager

