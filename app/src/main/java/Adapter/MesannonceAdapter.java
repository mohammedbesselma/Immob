package Adapter;
import android.app.Dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammed.immob.HomeActivity;
import com.example.mohammed.immob.ImmobilierDetais;
import com.example.mohammed.immob.MesannonceActivity;
import com.example.mohammed.immob.R;

import java.util.ArrayList;
import java.util.HashMap;

import AsyncTask.BackTask;

/**
 * Created by Mohammed on 03/06/2018.
 */

public class MesannonceAdapter extends BaseAdapter {

    Context context;
    ArrayList<HashMap<String,String>> mesannoncelist;

    int position1;



    private static LayoutInflater inflater = null;
    public MesannonceAdapter(Context context,ArrayList<HashMap<String,String>> commentlist) {

        this.mesannoncelist=commentlist;
        this.context=context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mesannoncelist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {

        View view = convertView;

        view = inflater.inflate(R.layout.mesannonce_row, null);

        HashMap<String, String> annonce = mesannoncelist.get(i);
        TextView type=(TextView)view.findViewById(R.id.type);
        TextView ville=(TextView)view.findViewById(R.id.ville);
        TextView prix=(TextView)view.findViewById(R.id.prix);
        TextView discr=(TextView)view.findViewById(R.id.discriptoion);
        ImageView delete =(ImageView) view.findViewById(R.id.delete);



        type.setText(annonce.get("type"));
        ville.setText(annonce.get("ville"));
        prix.setText(annonce.get("prix"));
        discr.setText(annonce.get("discription"));




        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(MesannonceActivity.ctx);
                dialog.setContentView(R.layout.deleteannoncedialog);

                Button cancel = (Button) dialog.findViewById(R.id.cancel);
                Button ok = (Button) dialog.findViewById(R.id.ok);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }});

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new BackTask(context).execute("deleteannonce",mesannoncelist.get(i).get("idimmob"));
                        new BackTask(context).execute("getmesannonce",String.valueOf(HomeActivity.utilisateur.getID_Utilisateur()));
                        dialog.dismiss();

                    }
                });



                dialog.show();




            }
        });










        return view;
    }
}


