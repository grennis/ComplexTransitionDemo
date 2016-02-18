package com.innodroid.complextransitiondemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.innodroid.complextransitiondemo.R;
import com.innodroid.complextransitiondemo.adapters.ThumbnailRecyclerAdapter;

public class ThumbnailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnails);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setAdapter(new ThumbnailRecyclerAdapter(this));
        recycler.setLayoutManager(new GridLayoutManager(this, 3));
    }
}
