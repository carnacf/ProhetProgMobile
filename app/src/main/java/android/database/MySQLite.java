package android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by samuel on 03/05/17.
 */

public class MySQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static MySQLite sInstance;

    public static synchronized MySQLite getInstance(Context context) {
        if (sInstance == null) { sInstance = new MySQLite(context); }
        return sInstance;
    }

    private MySQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Création de la base de données
        // on exécute ici les requêtes de création des tables
        sqLiteDatabase.execSQL(EtudiantManager.CREATE_TABLE_ETUDIANT);
        sqLiteDatabase.execSQL(CoursManager.CREATE_TABLE_COURS);
        sqLiteDatabase.execSQL(EvaluationManager.CREATE_TABLE_EVALUATION);
        sqLiteDatabase.execSQL(QuestionManager.CREATE_TABLE_QUESTION);
        sqLiteDatabase.execSQL(QuestionnaireManager.CREATE_TABLE_QUESTIONNAIRE);
        sqLiteDatabase.execSQL(EvalEtudManager.CREATE_TABLE_EVALETUD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // Mise à jour de la base de données
        // méthode appelée sur incrémentation de DATABASE_VERSION
        // on peut faire ce qu'on veut ici, comme recréer la base :
        onCreate(sqLiteDatabase);
    }

} // class MySQLite
