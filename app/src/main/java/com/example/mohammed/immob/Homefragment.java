package com.example.mohammed.immob;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;


import Adapter.CustomListViewAdapter;
import AsyncTask.BackTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class Homefragment extends Fragment {

    public static ListView listView;
    private CustomListViewAdapter customListViewAdapter;
    public static Context context;
    public static ProgressBar progressBar;
    Button botton;
    int i=0;

    Button search;












    public Homefragment() {



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_homefragment, container, false);
        listView=(ListView) view.findViewById(R.id.immoblist);
        botton=(Button)view.findViewById(R.id.ajouter);
        botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myintent = new Intent(getActivity(),Ajouterimmob.class);
                myintent.putExtra("Utilisateur", HomeActivity.utilisateur);
                getActivity().startActivity(myintent);


            }
        });

        search=(Button)view.findViewById(R.id.rechercher);


            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (i==0){
                        final Dialog dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.search_dialog);
                        dialog.setTitle("Rechercher");
                        Button cancel = (Button) dialog.findViewById(R.id.cancel);
                        Button ok = (Button) dialog.findViewById(R.id.ok);
                        final EditText type = (EditText)dialog.findViewById(R.id.name);
                        final EditText ville = (EditText)dialog.findViewById(R.id.ville);
                        final EditText date = (EditText)dialog.findViewById(R.id.date);

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }});
                        dialog.show();
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                new BackTask(getActivity()).execute("search",type.getText().toString(),ville.getText().toString(),date.getText().toString());
                                search.setBackgroundResource(R.drawable.ic_clear_black_24dp);
                                dialog.dismiss();
                                i=1;
                            }
                        });


                    }else {
                        BackTask backTask = new BackTask(getActivity());
                        backTask.execute("newimmob");
                        search.setBackgroundResource(R.drawable.search);
                        i=0;
                    }

                }
            });









        BackTask backTask = new BackTask(getActivity());
        backTask.execute("newimmob");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent myintent = new Intent(getActivity(),ImmobilierDetais.class);
                myintent.putExtra("detaisimmob", CustomListViewAdapter.Immobilierlist.get(i));
                myintent.putExtra("idutilisateur", HomeActivity.utilisateur.getID_Utilisateur());

                startActivity(myintent);





            }


        });




        return view;
    }


}
