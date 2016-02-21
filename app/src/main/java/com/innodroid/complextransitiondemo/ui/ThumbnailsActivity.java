package com.innodroid.complextransitiondemo.ui;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.innodroid.complextransitiondemo.R;
import com.innodroid.complextransitiondemo.adapters.ThumbnailRecyclerAdapter;

import java.util.List;
import java.util.Map;

public class ThumbnailsActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private ThumbnailRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.setExitSharedElementCallback(this, ExitTransitionCallback);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnails);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        adapter = new ThumbnailRecyclerAdapter(this);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private final SharedElementCallback ExitTransitionCallback = new SharedElementCallback() {
        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            if (PagerActivity.SelectedIndex < 0) {
                // When transitioning out, use the view already specified in makeSceneTransition
            } else {
                // When transitioning back in, use the thumbnail at index the user had swiped to in the pager activity
                sharedElements.put(names.get(0), adapter.getViewAtIndex(recycler, PagerActivity.SelectedIndex));
                PagerActivity.SelectedIndex = -1;
            }
        }
    };
}
