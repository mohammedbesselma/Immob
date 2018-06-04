package AppClasse;

import android.content.Context;

import java.util.ArrayList;

import AsyncTask.BackTask;

/**
 * Created by Mohammed on 14/05/2018.
 */

public class BienImmobilier {


    private int ID_Immobilier;
    float Etat;
    private String Type,Ville,Discription;
     private float Prix;



    public void AjouterImmobilier(Context ctx, String ID_Utilisateur, ArrayList<String>URLP){


        BackTask ajouter=new BackTask(ctx);
        ajouter.execute("AjouterImmobilier",Type,Ville,String.valueOf(Etat),String.valueOf(Prix),Discription,ID_Utilisateur);







}



    public void SuprimerImmobilier(){




}

public void ModifierImmobilier(){




}


    public void setID_Immobilier(int ID_Immobilier) {
        this.ID_Immobilier = ID_Immobilier;
    }

    public void setEtat(float etat) {
        Etat = etat;
    }



    public void setType(String type) {
        Type = type;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public void setPrix(float prix) {
        Prix = prix;
    }

    public int getID_Immobilier() {

        return ID_Immobilier;
    }

    public float getEtat() {
        return Etat;
    }


    public String getType() {
        return Type;
    }

    public String getVille() {
        return Ville;
    }

    public String getDiscription() {
        return Discription;
    }

    public float getPrix() {
        return Prix;
    }



}
