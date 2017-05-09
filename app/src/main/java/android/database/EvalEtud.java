package android.database;

/**
 * Created by samuel on 03/05/17.
 */

public class EvalEtud {
    private int id_evalEtud;
    private int id_evaluation;
    private int id_etudiant;
    private int eval_passe;
    private int note;

    public EvalEtud(int id_evalEtud, int id_evaluation, int id_etudiant, int eval_passe, int note) {
        this.id_evalEtud = id_evalEtud;
        this.id_evaluation = id_evaluation;
        this.id_etudiant = id_etudiant;
        this.eval_passe = eval_passe;
        this.note = note;
    }

    public EvalEtud() {
    }

    public int getId_evalEtud() {
        return id_evalEtud;
    }

    public void setId_evalEtud(int id_evalEtud) {
        this.id_evalEtud = id_evalEtud;
    }

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

    public int getEval_passe() {
        return eval_passe;
    }

    public void setEval_passe(int eval_passe) {
        this.eval_passe = eval_passe;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
