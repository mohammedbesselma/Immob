package Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mohammed.immob.R;

import java.util.ArrayList;

/**
 * Created by Mohammed on 07/05/2018.
 */

public class ImageAdapter extends BaseAdapter {

    private final Context mContext;
    ArrayList<Bitmap> bitmaps;


    // 1
    public ImageAdapter(Context context, ArrayList<Bitmap> bitmaps) {
        this.mContext = context;
        this.bitmaps=bitmaps;


    }

    @Override
    public int getCount() {
        return bitmaps.size();
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
        imageView.setImageBitmap(bitmaps.get(i));




        return view;
    }
}
