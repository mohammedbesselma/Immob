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
import com.example.mohammed.immob.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import AsyncTask.BackTask;

/**
 * Created by Mohammed on 05/06/2018.
 */

public class ReservationListAdapter  extends BaseAdapter {

    private Context mContext;
    public static ArrayList<HashMap<String, String>> reservationlist;
    private static LayoutInflater inflater = null;
    String i;


    public ReservationListAdapter(Context context, ArrayList<HashMap<String, String>> data){

        mContext = context;
        reservationlist = data;
        this.i=i;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return reservationlist.size();
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



        view = inflater.inflate(R.layout.reservationrow, null);

        TextView nomprenom = (TextView) view.findViewById(R.id.nomprenom);
        TextView date = (TextView) view.findViewById(R.id.datereservation);

        Button confirmer = (Button)view.findViewById(R.id.confirmer);
        Button annuler = (Button)view.findViewById(R.id.annuler);

        HashMap<String, String> reservation = new HashMap<>();

        reservation = reservationlist.get(position);



        nomprenom.setText(reservation.get("nom")+" "+reservation.get("prenom"));
        date.setText(reservation.get("date"));














        return view;
    }
}
