package com.innodroid.complextransitiondemo.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;

import com.innodroid.complextransitiondemo.R;
import com.innodroid.complextransitiondemo.adapters.ImagePagerAdapter;

import java.util.List;
import java.util.Map;

public class PagerActivity extends AppCompatActivity {
    public static final String EXTRA_POSITION = "position";

    private ViewPager pager;
    private ImagePagerAdapter adapter;

    public static int SelectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.postponeEnterTransition(this);
        ActivityCompat.setEnterSharedElementCallback(this, EnterTransitionCallback);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        adapter = new ImagePagerAdapter(this);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        int position = SelectedIndex = getIntent().getIntExtra(EXTRA_POSITION, 0);
        pager.setCurrentItem(position);
        pager.setOnPageChangeListener(PageChangeListener);
        pager.getViewTreeObserver().addOnGlobalLayoutListener(PagerLayoutListener);
    }

    private ViewPager.OnPageChangeListener PageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            SelectedIndex = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    private ViewTreeObserver.OnGlobalLayoutListener PagerLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            pager.getViewTreeObserver().removeOnGlobalLayoutListener(PagerLayoutListener);
            ActivityCompat.startPostponedEnterTransition(PagerActivity.this);
        }
    };

    private final SharedElementCallback EnterTransitionCallback = new SharedElementCallback() {
        @SuppressLint("NewApi")
        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            View view = null;

            if (pager.getChildCount() > 0) {
                view = adapter.getCurrentView(pager);
                view = view.findViewById(R.id.pager_image);
            }

            if (view != null) {
                sharedElements.put(names.get(0), view);
            }
        }
    };
}
