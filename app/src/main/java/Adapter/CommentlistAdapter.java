package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammed.immob.HomeActivity;
import com.example.mohammed.immob.ImmobilierDetais;
import com.example.mohammed.immob.R;

import java.util.ArrayList;
import java.util.HashMap;

import AsyncTask.BackTask;

/**
 * Created by Mohammed on 23/05/2018.
 */

public class CommentlistAdapter extends BaseAdapter {

    Context context;
    ArrayList<HashMap<String,String>> commentlist;
    HashMap<String, String> commentid = new HashMap<>();
    int position1;



    private static LayoutInflater inflater = null;
    public CommentlistAdapter(Context context,ArrayList<HashMap<String,String>> commentlist) {

        this.commentlist=commentlist;
        this.context=context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return commentlist.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;



        view = inflater.inflate(R.layout.commentrow, null);

        HashMap<String, String> comment = new HashMap<>();

        comment = commentlist.get(position);
        commentid = commentlist.get(position);
        position1=position;
        ImageView deletecomment= (ImageView)view.findViewById(R.id.deletecomment);
        TextView nomprenom=(TextView)view.findViewById(R.id.commentnomprenom);
        TextView dateheur=(TextView)view.findViewById(R.id.dateheurcomment);
        TextView contenue=(TextView)view.findViewById(R.id.commentcontenue);


        nomprenom.setText(comment.get("nom")+" "+comment.get("prenom"));
        dateheur.setText(comment.get("heur")+" "+comment.get("date"));
        contenue.setText(comment.get("contenue"));

        if (comment.get("idutilisateur").equals(String.valueOf(HomeActivity.utilisateur.getID_Utilisateur()))){

            deletecomment.setVisibility(View.VISIBLE);
        }else {
            deletecomment.setVisibility(View.GONE);
        }
        deletecomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentid = commentlist.get(position1);

                new BackTask(context).execute("deletecomment",commentid.get("idcomment"));
                new BackTask(context).execute("getcommentlist", ImmobilierDetais.immob.get("idimmob"));




            }
        });

        return view;
    }
}
