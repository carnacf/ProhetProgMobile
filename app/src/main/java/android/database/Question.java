package android.database;

/**
 * Created by samuel on 03/05/17.
 */

public class Question {
    private int id_question;
    private String nom;
    private String r1;
    private String r2;
    private String r3;
    private String r4;
    private int reponse;

    public Question(int id_question, String nom, String r1, String r2, String r3, String r4, int reponse) {
        this.id_question = id_question;
        this.nom = nom;
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.reponse = reponse;
    }

    public Question()
    {

    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getR1() {
        return r1;
    }

    public void setR1(String r1) {
        this.r1 = r1;
    }

    public String getR2() {
        return r2;
    }

    public void setR2(String r2) {
        this.r2 = r2;
    }

    public String getR3() {
        return r3;
    }

    public void setR3(String r3) {
        this.r3 = r3;
    }

    public String getR4() {
        return r4;
    }

    public void setR4(String r4) {
        this.r4 = r4;
    }

    public int getReponse() {
        return reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }
}
