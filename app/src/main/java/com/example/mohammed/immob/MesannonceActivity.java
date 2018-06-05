package com.example.mohammed.immob;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import Adapter.CustomListViewAdapter;
import Adapter.MesannonceAdapter;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent myintent = new Intent(getApplicationContext(),Reservation.class);
                myintent.putExtra("idimmob", MesannonceAdapter.mesannoncelist.get(i).get("idimmob"));

                startActivity(myintent);





            }


        });

    }
}
