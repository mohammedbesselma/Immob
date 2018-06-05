package com.example.mohammed.immob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.HashMap;

import AsyncTask.BackTask;

public class Reservation extends AppCompatActivity {
public  static ListView reservationlist;
public static String idimmob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){

            idimmob= (String) bundle.get("idimmob");


        }
        reservationlist=(ListView)findViewById(R.id.listreservation);


        new BackTask(getApplication()).execute("reservation",idimmob);
    }
}
