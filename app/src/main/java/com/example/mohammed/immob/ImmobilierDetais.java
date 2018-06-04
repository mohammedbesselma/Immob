package com.example.mohammed.immob;


import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightGridView;
import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.HashMap;
import Adapter.GridViewAdapter;
import Adapter.SliderAdapter;
import AsyncTask.BackTask;

public class ImmobilierDetais extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    GridViewAdapter gridViewAdapter;

    ImageView imageView4;
    public static HashMap<String, String> immob;
    public  static  TextView type,ville,prix,discription,nomprenom,email;
    RatingBar detaisrating;
    Button commenter,reserver;
    public  static Button ajouteraufavori;
    EditText contenue;
    public  static ExpandableHeightListView commentlist;
    public  static ViewPager myviewpager;
    int idutilisateur;
    ImageView sendmessage;
    public  static ExpandableHeightGridView reservationgrid;
    Calendar calendar ;
    DatePickerDialog datePickerDialog ;
    int Year, Month, Day ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immobilier_detais);


        immob = new HashMap<>();


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){

            immob= (HashMap<String, String>) bundle.get("detaisimmob");
            idutilisateur= (int) bundle.get("idutilisateur");

        }
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);







        myviewpager =(ViewPager)findViewById(R.id.imageviewpager);
        type=(TextView)findViewById(R.id.detaistype);
        ville=(TextView)findViewById(R.id.detaisville);
        discription=(TextView)findViewById(R.id.detaisdisc);
        prix=(TextView)findViewById(R.id.detaisprix1);
        nomprenom=(TextView)findViewById(R.id.nomproprietaire);
        email=(TextView)findViewById(R.id.contactproprietaire);
        sendmessage=(ImageView)findViewById(R.id.sendmessageto);

        reservationgrid=(ExpandableHeightGridView)findViewById(R.id.reservationgridview);
        detaisrating = (RatingBar)findViewById(R.id.detaisrating12);
        commenter = (Button)findViewById(R.id.buttoncomment);
        contenue=(EditText)findViewById(R.id.commentcontenue);
        commentlist=(ExpandableHeightListView)findViewById(R.id.commentlistview);
        ajouteraufavori=(Button)findViewById(R.id.ajouteraufavoris);
        reserver=(Button)findViewById(R.id.reserverimmob);
        commentlist.setExpanded(true);


        new BackTask(getApplicationContext()).execute("getreservation",immob.get("idimmob"),String.valueOf(idutilisateur));


        if (immob.get("favoris").equals("1")){

            ajouteraufavori.setVisibility(View.GONE);

        }

        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myintent = new Intent(getApplicationContext(),Discussion.class);
                myintent.putExtra("idresever", immob.get("idutilisateur"));
                myintent.putExtra("idsender", idutilisateur);
                startActivity(myintent);

            }
        });
        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                datePickerDialog = DatePickerDialog.newInstance(ImmobilierDetais.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setAccentColor(Color.parseColor("#AD1457"));
                datePickerDialog.setTitle("Réservez votre immobilier");
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");

            }
        });



        ajouteraufavori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new BackTask(getApplicationContext()).execute("favoris",String.valueOf(idutilisateur),immob.get("idimmob"));
            }
        });

        new BackTask(getApplicationContext()).execute("get",immob.get("idutilisateur"));

        new BackTask(getApplicationContext()).execute("geturl",immob.get("idimmob"));
        imageView4=(ImageView)findViewById(R.id.photoproprietaire);

        new BackTask(getApplicationContext()).execute("getcommentlist",immob.get("idimmob"));

        type.setText(immob.get("type"));
        ville.setText(immob.get("ville"));
        prix.setText(immob.get("prix")+"DA/NUIT");
        discription.setText(immob.get("discription"));
        detaisrating.setRating(Float.parseFloat(immob.get("etat")));

        commenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new BackTask(getApplicationContext()).execute("commenter",contenue.getText().toString(),immob.get("idimmob"),String.valueOf(idutilisateur));

                new BackTask(getApplicationContext()).execute("getcommentlist",immob.get("idimmob"));
            }
        });



    }


    @Override
    public void onDateSet(DatePickerDialog view, int Year, int Month, int Day) {

        String date = Year+"-"+Month+"-"+Day;


        new BackTask(getApplicationContext()).execute("addreservation",immob.get("idimmob"),String.valueOf(idutilisateur),date);

        new BackTask(getApplicationContext()).execute("getreservation",immob.get("idimmob"),String.valueOf(idutilisateur));


        Toast.makeText(ImmobilierDetais.this, date, Toast.LENGTH_LONG).show();
    }
}
