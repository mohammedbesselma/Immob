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

public class CustomListViewAdapter extends BaseAdapter {

    private Context mContext;
   public static ArrayList<HashMap<String, String>> Immobilierlist;
    private static LayoutInflater inflater = null;
    String i;


    public CustomListViewAdapter(Context context, ArrayList<HashMap<String, String>> data,String i){

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



            view = inflater.inflate(R.layout.immob_row, null);

            TextView type = (TextView) view.findViewById(R.id.type);
            TextView ville = (TextView) view.findViewById(R.id.immobville);
            TextView prix = (TextView) view.findViewById(R.id.immobprix);
            final ImageView image = (ImageView) view.findViewById(R.id.immobimage);
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.etat);
        Button deletefavoris = (Button)view.findViewById(R.id.deletefromfavoris);

            HashMap<String, String> immob = new HashMap<>();

            immob = Immobilierlist.get(position);



            type.setText(immob.get("type"));
            ville.setText(immob.get("ville"));
            prix.setText(immob.get("prix")+" DA/NUIT");
            ratingBar.setRating(Float.parseFloat(immob.get("etat")));


            Picasso.with(mContext).load(Constans.getIPadress()+"/android_upload/uploads/"+immob.get("url")).into(image);

            if (i.equals("1")){
                deletefavoris.setVisibility(View.GONE);
            }
            deletefavoris.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new BackTask(mContext).execute("deletefavoris",Immobilierlist.get(position).get("idimmob"), String.valueOf(HomeActivity.utilisateur.getID_Utilisateur()));
                }
            });







        return view;
    }
}
