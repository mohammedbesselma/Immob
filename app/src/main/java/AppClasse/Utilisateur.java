package AppClasse;

import android.content.Context;

import java.io.Serializable;

import AsyncTask.BackTask;
import AsyncTask.LoginRegisterTask;
import AsyncTask.RegisterTask;

/**
 * Created by Mohammed on 09/05/2018.
 */

public class Utilisateur implements Serializable {

    private int ID_Utilisateur ;
    private String Nom_Utilisateur,Prenom_Utilisateur,Email_Utilisateur,Motdepasse_Utilisateur;




    public void Connecter(String email,String motdepasse , Context ctx){

        new LoginRegisterTask(ctx).execute("login",email,motdepasse);



    }

    public  void Inscrir(String nom,String prenom,String email,String motdepasse,Context ctx){


        new RegisterTask(ctx).execute("register",nom,prenom,email,motdepasse);

    }

    public void setID_Utilisateur(int ID_Utilisateur) {
        this.ID_Utilisateur = ID_Utilisateur;
    }

    public void setNom_Utilisateur(String nom_Utilisateur) {
        Nom_Utilisateur = nom_Utilisateur;
    }

    public void setPrenom_Utilisateur(String prenom_Utilisateur) {
        Prenom_Utilisateur = prenom_Utilisateur;
    }

    public void setEmail_Utilisateur(String email_Utilisateur) {
        Email_Utilisateur = email_Utilisateur;
    }

    public void setMotdepasse_Utilisateur(String motdepasse_Utilisateur) {
        Motdepasse_Utilisateur = motdepasse_Utilisateur;
    }

    public int getID_Utilisateur() {

        return ID_Utilisateur;
    }

    public String getNom_Utilisateur() {
        return Nom_Utilisateur;
    }

    public String getPrenom_Utilisateur() {
        return Prenom_Utilisateur;
    }

    public String getEmail_Utilisateur() {
        return Email_Utilisateur;
    }

    public String getMotdepasse_Utilisateur() {
        return Motdepasse_Utilisateur;
    }
}
