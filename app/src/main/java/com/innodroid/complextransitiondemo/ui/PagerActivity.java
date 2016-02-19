package com.innodroid.complextransitiondemo.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.innodroid.complextransitiondemo.R;
import com.innodroid.complextransitiondemo.adapters.ImagePagerAdapter;

public class PagerActivity extends AppCompatActivity {
    public static final String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new ImagePagerAdapter(this));
        pager.setCurrentItem(getIntent().getIntExtra(EXTRA_POSITION, 0));
    }
}
