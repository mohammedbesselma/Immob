package com.example.mohammed.immob;


import android.content.Intent;
import android.os.Bundle;
import android.os.HardwarePropertiesManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;

import Adapter.CustomListViewAdapter;
import Adapter.DiscussionsAdapter;
import AsyncTask.BackTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    public  static ListView disclist;


    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message, container, false);

        disclist=(ListView)view.findViewById(R.id.discussionlist);

        new BackTask(getActivity()).execute("getdiscussion",String.valueOf(HomeActivity.utilisateur.getID_Utilisateur()));

        disclist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, String> data = new HashMap<>();
                data = DiscussionsAdapter.messagelist.get(i);
                Intent myintent = new Intent(getActivity(),Discussion.class);
                myintent.putExtra("idsender", HomeActivity.utilisateur.getID_Utilisateur());
                myintent.putExtra("idresever", data.get("idresever"));
                getActivity().startActivity(myintent);





            }


        });
        return view;
    }

}
