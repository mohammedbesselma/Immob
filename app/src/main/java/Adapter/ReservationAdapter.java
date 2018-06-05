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
 * Created by Mohammed on 02/06/2018.
 */

public class ReservationAdapter extends BaseAdapter {

    private Context mContext;
    public static ArrayList<HashMap<String, String>> reservationlist;
    private static LayoutInflater inflater = null;
    HashMap<String, String> immob;

    public ReservationAdapter(Context context, ArrayList<HashMap<String, String>> data){

        mContext = context;
        reservationlist = data;
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



        view = inflater.inflate(R.layout.reservatios_row, null);

        TextView textView=(TextView)view.findViewById(R.id.datereservation1);
        TextView etat = (TextView)view.findViewById(R.id.etatreservation);
        Button button = (Button)view.findViewById(R.id.cancelreservation);


        immob = new HashMap<>();

        immob = reservationlist.get(position);

        textView.setText(immob.get("date"));




            etat.setText(immob.get("validation"));







        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new BackTask(mContext).execute("deletereservation",reservationlist.get(position).get("idreservation"));
                new BackTask(mContext).execute("getreservation",reservationlist.get(position).get("idimmob"),String.valueOf(HomeActivity.utilisateur.getID_Utilisateur()));

            }
        });

        return view;
    }
}

