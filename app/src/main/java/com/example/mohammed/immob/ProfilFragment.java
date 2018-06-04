package com.example.mohammed.immob;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    TextView nomprenom,email;
    Button tomes,tomesfa;
    Button conecter1;


    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        nomprenom=(TextView)view.findViewById(R.id.profilnomprenom);
        email=(TextView)view.findViewById(R.id.profilemail);
        tomes=(Button)view.findViewById(R.id.tomesannonce);
        tomesfa=(Button)view.findViewById(R.id.tomesfavoris);

        nomprenom.setText(HomeActivity.utilisateur.getNom_Utilisateur()+" "+HomeActivity.utilisateur.getPrenom_Utilisateur());
        email.setText(HomeActivity.utilisateur.getEmail_Utilisateur());
        conecter1=(Button)view.findViewById(R.id.deconecter);
        conecter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getActivity(),LoginActivity.class);
                getActivity().startActivity(myintent);
            }
        });
        tomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getActivity(),MesannonceActivity.class);

                getActivity().startActivity(myintent);

            }
        });
        tomesfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getActivity(),Favoris.class);
                myintent.putExtra("idutilisateur", HomeActivity.utilisateur.getID_Utilisateur());
                getActivity().startActivity(myintent);

            }
        });
        return view;
    }

}
