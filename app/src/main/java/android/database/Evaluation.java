package android.database;

/**
 * Created by samuel on 03/05/17.
 */

public class Evaluation {
    private int id_evaluation;
    private String nom;
    private String niveau_etud;
    private String specialite;

    public Evaluation(int id_evaluation, String nom, String niveau_etud, String specialite)
    {
        this.id_evaluation = id_evaluation;
        this.nom = nom;
        this.niveau_etud = niveau_etud;
        this.specialite = specialite;
    }

    public Evaluation() {

    }

    public int getId_evaluation() {
        return id_evaluation;
    }

    public void setId_evaluation(int id_evaluation) {
        this.id_evaluation = id_evaluation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNiveau_etud() {
        return niveau_etud;
    }

    public void setNiveau_etud(String niveau_etud) {
        this.niveau_etud = niveau_etud;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
