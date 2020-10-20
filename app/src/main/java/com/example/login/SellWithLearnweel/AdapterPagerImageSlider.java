package com.example.login.SellWithLearnweel;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.login.R;

import java.util.ArrayList;

public class AdapterPagerImageSlider extends PagerAdapter {

    private ArrayList<Bitmap> bitmaps;
    private Context context;
    private LayoutInflater layoutInflater;

    public AdapterPagerImageSlider(Context context, ArrayList<Bitmap> bitmaps) {
        this.context = context;
        this.bitmaps= bitmaps;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.inflater_imageslider, container, false);

        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageBitmap(bitmaps.get(position)); //this set image from bitmap

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == o);
    }
}
