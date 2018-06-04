package com.example.mohammed.immob;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import AppClasse.Utilisateur;

public class RegisterActivity extends AppCompatActivity {
    EditText Nom , Prenom,Email,Motpass;
    Button register;
    public static Utilisateur utilisateur;
    public static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
ctx=getApplicationContext();
        Nom= (EditText)findViewById(R.id.nom);
        Prenom= (EditText)findViewById(R.id.prenom);
        Email= (EditText)findViewById(R.id.email);
        Motpass= (EditText)findViewById(R.id.motpass);
        register=(Button)findViewById(R.id.registerb);

        utilisateur=new Utilisateur();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( TextUtils.isEmpty(Nom.getText())){


                    Nom.setError( "Chaump nom est obligatoir!" );

                }else if (TextUtils.isEmpty(Prenom.getText())){

                    Prenom.setError( "Chaump prenom est obligatoir!" );

                }else if (TextUtils.isEmpty(Email.getText())){

                    Email.setError( "Chaump Email est obligatoir!" );

                }else if (TextUtils.isEmpty(Motpass.getText())){

                    Motpass.setError( "Chaump Motdepasse est obligatoir!" );

                }else{
                    new Utilisateur().Inscrir(Nom.getText().toString(),Prenom.getText().toString(),Email.getText().toString(),Motpass.getText().toString(),getApplicationContext());
                }
            }
        });




    }


}
