package android.database;

/**
 * Created by samuel on 03/05/17.
 */

public class Etudiant {
    private int id_etudiant;
    private String nom;
    private String prenom;
    private String mdp;
    private String date_naisance;
    private String email;
    private String niveau_etud;
    private String specialite;

    public Etudiant(int id, String nom, String prenom, String mdp, String date, String email, String niveau, String spe)
    {
        this.id_etudiant=id;
        this.nom=nom;
        this.prenom=prenom;
        this.mdp=mdp;
        this.date_naisance=date;
        this.email=email;
        this.niveau_etud=niveau;
        this.specialite=spe;
    }

    public Etudiant()
    {

    }

    public int getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getDate_naisance() {
        return date_naisance;
    }

    public void setDate_naisance(String date_naisance) {
        this.date_naisance = date_naisance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
