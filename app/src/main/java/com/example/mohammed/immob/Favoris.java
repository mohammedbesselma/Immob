package com.example.mohammed.immob;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

import Adapter.CustomListViewAdapter;
import Adapter.FavorisAdapter;
import AsyncTask.BackTask;

public class Favoris extends AppCompatActivity {
public  static ListView favorislist;
int idutilisateur;
public  static Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);
ctx=Favoris.this;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){


            idutilisateur= (int) bundle.get("idutilisateur");

        }

favorislist=(ListView)findViewById(R.id.favorislist1);
        favorislist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent myintent = new Intent(getApplicationContext(),ImmobilierDetais.class);
                myintent.putExtra("detaisimmob", FavorisAdapter.Immobilierlist.get(i));
                myintent.putExtra("idutilisateur", idutilisateur);
                startActivity(myintent);





            }


        });

new BackTask(getApplicationContext()).execute("getfavoris",String.valueOf(HomeActivity.utilisateur.getID_Utilisateur()));



    }
}
