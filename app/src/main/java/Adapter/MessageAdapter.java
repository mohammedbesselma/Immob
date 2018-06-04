package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mohammed.immob.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mohammed on 01/06/2018.
 */

public class MessageAdapter extends BaseAdapter {

    Context context;
    private static LayoutInflater inflater = null;
    int myid;
    ArrayList<HashMap<String,String>> messagelist;

    public MessageAdapter(Context context,ArrayList<HashMap<String,String>> messagelist,int myid){
        this.context=context;
        this.messagelist=messagelist;
        this.myid=myid;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }
    @Override
    public int getCount() {
        return messagelist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        view = inflater.inflate(R.layout.messagelist_row, null);

        HashMap<String, String> message = new HashMap<>();
        message = messagelist.get(position);


        TextView mymessage=(TextView)view.findViewById(R.id.mymessagetext);
        TextView yourmessage=(TextView)view.findViewById(R.id.yourmessagetext);
        LinearLayout mylayout=(LinearLayout)view.findViewById(R.id.mymessagelayout);
        LinearLayout yourlayout=(LinearLayout)view.findViewById(R.id.yourmessagelayout);

        if (String.valueOf(myid).equals(message.get("idsender"))){

            mymessage.setText(message.get("contenue"));
            yourlayout.setVisibility(View.GONE);


        }else {

            yourmessage.setText(message.get("contenue"));
            mylayout.setVisibility(View.GONE);



        }




        return view;
    }
}
