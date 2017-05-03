package android.database;

/**
 * Created by samuel on 03/05/17.
 */

public class EvalEtud {
    private int id_evaluation;
    private int id_etudiant;
    private boolean eval_Passe;
    private int note;

    public int getId_evaluation() {
        return id_evaluation;
    }

    public void setId_evaluation(int id_evaluation) {
        this.id_evaluation = id_evaluation;
    }

    public int getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public boolean isEval_Passe() {
        return eval_Passe;
    }

    public void setEval_Passe(boolean eval_Passe) {
        this.eval_Passe = eval_Passe;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
