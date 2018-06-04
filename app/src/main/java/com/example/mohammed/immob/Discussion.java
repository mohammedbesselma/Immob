package com.example.mohammed.immob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.HashMap;

import AppClasse.Utilisateur;
import AsyncTask.BackTask;

import static java.lang.Thread.sleep;

public class Discussion extends AppCompatActivity {
    public  static ListView messageliste;

    String idresever;
    public static  int idutilisateur;
    Button sendmessage;
    public  static EditText messagetosend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        messageliste=(ListView)findViewById(R.id.messagelistview);
        messagetosend=(EditText)findViewById(R.id.messagetosend);
        sendmessage=(Button)findViewById(R.id.sendmessage);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){

            idresever = (String) bundle.get("idresever");
             idutilisateur = (int) bundle.get("idsender");

        }

        new BackTask(getApplicationContext()).execute("getmessage",String.valueOf(idutilisateur),idresever);



        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new BackTask(getApplicationContext()).execute("sendmessage",String.valueOf(idutilisateur),idresever,messagetosend.getText().toString());

                new BackTask(getApplicationContext()).execute("getmessage",String.valueOf(idutilisateur),idresever);

            }
        });


    }
}
