package android.database;

/**
 * Created by samuel on 03/05/17.
 */

public class Questionnaire {
    private int id_questionnaire;
    private int id_evaluation;
    private int id_question;

    public Questionnaire(int id_evaluation, int id_question)
    {
        this.id_evaluation = id_evaluation;
        this.id_question = id_question;
    }

    public Questionnaire()
    {

    }

    public int getId_questionnaire() {
        return id_questionnaire;
    }

    public void setId_questionnaire(int id_questionnaire) {
        this.id_questionnaire = id_questionnaire;
    }

    public int getId_evaluation() {
        return id_evaluation;
    }

    public void setId_evaluation(int id_evaluation) {
        this.id_evaluation = id_evaluation;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }
}
