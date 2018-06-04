package Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mohammed.immob.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mohammed on 19/05/2018.
 */

public class GridViewAdapter extends BaseAdapter {


    private final Context mContext;
    ArrayList<String> link;


    // 1
    public GridViewAdapter(Context context) {
        this.mContext = context;



    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.image_row, null);


        }

        final ImageView imageView = (ImageView)view.findViewById(R.id.imagetoup);
        Picasso.with(mContext).load("http://192.168.1.30/android_upload/uploads/1.JPG").into(imageView);




        return view;
    }
}

