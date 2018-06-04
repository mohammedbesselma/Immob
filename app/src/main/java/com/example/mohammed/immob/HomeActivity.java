package com.example.mohammed.immob;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListView;

import Adapter.CustomListViewAdapter;
import AppClasse.Utilisateur;

public class HomeActivity extends AppCompatActivity {




    private BottomNavigationView mMainnav;
    private FrameLayout mMainframe;
    private ListView listView;
    private CustomListViewAdapter customListViewAdapter;
    private Homefragment homefragment;
    private MessageFragment messageFragment;
    private ProfilFragment profilFragment;
    public static Utilisateur utilisateur;
    public static android.support.v4.app.FragmentTransaction fragmentTransaction1;




    Toolbar toolbar;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);





        mMainframe=(FrameLayout)findViewById(R.id.main_frame);
        mMainnav = (BottomNavigationView)findViewById(R.id.main_nav);
        homefragment = new Homefragment();

        messageFragment = new MessageFragment();
        profilFragment = new ProfilFragment();


        utilisateur = new Utilisateur();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){

            utilisateur=(Utilisateur)bundle.get("Utilisateur");

        }
        fragmentTransaction1=getSupportFragmentManager().beginTransaction();



        fragmentTransaction1.replace(R.id.main_frame,homefragment);
        fragmentTransaction1.commit();


        mMainnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                android.support.v4.app.FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();


                switch (item.getItemId()){

                    case R.id.home_item :


                        fragmentTransaction.replace(R.id.main_frame,homefragment);
                        fragmentTransaction.commit();


                        return true;






                    case R.id.message_item :


                        fragmentTransaction.replace(R.id.main_frame,messageFragment);
                        fragmentTransaction.commit();

                        return true;

                    case R.id.profil_item :


                        fragmentTransaction.replace(R.id.main_frame,profilFragment);
                        fragmentTransaction.commit();



                        return true;


                    default: return false;
                }


            }
        });







    }

    @Override
    protected void onResume() {


        super.onResume();
    }
}
