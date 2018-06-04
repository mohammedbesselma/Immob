package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mohammed.immob.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Mohammed on 19/05/2018.
 */

public class SliderAdapter extends PagerAdapter {

    private Context context;
    ArrayList<String> imagelink;
    LayoutInflater layoutInflater1;

    public SliderAdapter(Context context, ArrayList<String> imagelink) {
        this.context = context;
        layoutInflater1 = LayoutInflater.from(context);
        this.imagelink=imagelink;


    }

    @Override
    public int getCount() {
        return imagelink.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }


    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = layoutInflater1.inflate(R.layout.slider_layout, view, false);

        assert imageLayout != null;
        final ImageView imageView2 = (ImageView) imageLayout
                .findViewById(R.id.image1234);

        Picasso.with(context).load(Constans.getIPadress()+"/android_upload/uploads/"+imagelink.get(position)).into(imageView2);




        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
