package com.example.mohammed.immob;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import AsyncTask.BackTask;

public class MesannonceActivity extends AppCompatActivity {
    public static ListView listView ;
   public static Context ctx;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mesannonce);
ctx=MesannonceActivity.this;


        listView=(ListView)findViewById(R.id.mesannoncelist);

        new BackTask(getApplicationContext()).execute("getmesannonce",String.valueOf(HomeActivity.utilisateur.getID_Utilisateur()));



    }
}
