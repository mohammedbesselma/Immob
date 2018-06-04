package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammed.immob.Favoris;
import com.example.mohammed.immob.HomeActivity;
import com.example.mohammed.immob.ImmobilierDetais;
import com.example.mohammed.immob.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import AsyncTask.BackTask;

/**
 * Created by Mohammed on 01/05/2018.
 */

public class FavorisAdapter extends BaseAdapter {

    private Context mContext;
    public static ArrayList<HashMap<String, String>> Immobilierlist;
    private static LayoutInflater inflater = null;
    String i;


    public FavorisAdapter(Context context, ArrayList<HashMap<String, String>> data){

        mContext = context;
        Immobilierlist = data;
        this.i=i;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return Immobilierlist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;



        view = inflater.inflate(R.layout.favorisrow, null);

        TextView type = (TextView) view.findViewById(R.id.type);
        TextView ville = (TextView) view.findViewById(R.id.immobville);
        TextView prix = (TextView) view.findViewById(R.id.immobprix);
        ImageView image = (ImageView) view.findViewById(R.id.immobimage);
        ImageView deletefavoris2 = (ImageView) view.findViewById(R.id.deletefromfavoris1);

        HashMap<String, String> immob = new HashMap<>();

        immob = Immobilierlist.get(position);

        deletefavoris2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new BackTask(mContext).execute("deletefavoris",Immobilierlist.get(position).get("idimmob"), String.valueOf(HomeActivity.utilisateur.getID_Utilisateur()));
                new BackTask(mContext).execute("getfavoris",String.valueOf(HomeActivity.utilisateur.getID_Utilisateur()));

            }
        });



        type.setText(immob.get("type"));
        ville.setText(immob.get("ville"));
        prix.setText(immob.get("prix")+" DA/NUIT");



        Picasso.with(mContext).load(Constans.getIPadress()+"/android_upload/uploads/"+immob.get("url")).into(image);









        return view;
    }
}
