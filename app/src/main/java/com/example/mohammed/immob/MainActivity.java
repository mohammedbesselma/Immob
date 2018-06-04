package com.example.mohammed.immob;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

     public  static  Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        Intent myintent = new Intent(MainActivity.this,LoginActivity.class);
        MainActivity.this.startActivity(myintent);


    }
}
