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

public class DiscussionsAdapter extends BaseAdapter {

    Context context;
    private static LayoutInflater inflater = null;
    public static ArrayList<HashMap<String,String>> messagelist;

    public DiscussionsAdapter(Context context, ArrayList<HashMap<String, String>> messagelist) {
        this.context = context;
        this.messagelist = messagelist;
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
        view = inflater.inflate(R.layout.discussion_row, null);

        HashMap<String, String> data = new HashMap<>();
        data = messagelist.get(position);


        TextView discname=(TextView)view.findViewById(R.id.discussionname);
        TextView disclast=(TextView)view.findViewById(R.id.discussioncontenue);

        discname.setText(data.get("nom")+" "+data.get("prenom"));
        disclast.setText(data.get("last"));







        return view;
    }
}
