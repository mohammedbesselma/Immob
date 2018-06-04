package com.example.mohammed.immob;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import AppClasse.Utilisateur;

public class LoginActivity extends AppCompatActivity {
    EditText user,pass;
    public static ProgressBar progressBar;
    LinearLayout linearLayout ;
    public  static TextView messagelogin;
    TextView textView ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user=(EditText)findViewById(R.id.emaillog);
        pass=(EditText)findViewById(R.id.motpasslog);

        progressBar=(ProgressBar)findViewById(R.id.prog);
        progressBar.setVisibility(View.GONE);

        messagelogin=(TextView)findViewById(R.id.loginmessage);
        textView=(TextView)findViewById(R.id.lorem);


        user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    textView.setVisibility(View.GONE);
                }
            }
        });






    }

  public void  log(View view){

      new Utilisateur().Connecter(user.getText().toString(),pass.getText().toString(),this);
      progressBar.setVisibility(View.VISIBLE);


  }

  public void toregitre(View view){

      Intent myintent = new Intent(LoginActivity.this,RegisterActivity.class);
      LoginActivity.this.startActivity(myintent);
  }
}
