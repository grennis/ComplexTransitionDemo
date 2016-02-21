package com.innodroid.complextransitiondemo.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.innodroid.complextransitiondemo.ImageSource;
import com.innodroid.complextransitiondemo.R;
import com.squareup.picasso.Picasso;

public class ImagePagerAdapter extends PagerAdapter {
    protected LayoutInflater inflater;
    protected Context context;

    public ImagePagerAdapter(Context context) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ImageSource.ImageCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_pager, container, false);

        view.setTag(R.id.index, position);
        ImageView image = (ImageView) view.findViewById(R.id.pager_image);
        Picasso.with(context).load(ImageSource.getImageAtPosition(position)).placeholder(R.mipmap.ic_launcher).into(image);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public View getCurrentView(ViewPager pager) {
        for (int i=0; i<pager.getChildCount(); i++) {
            if (pager.getChildAt(i).getTag(R.id.index) == pager.getCurrentItem()) {
                return pager.getChildAt(i);
            }
        }

        return null;
    }
}



