package com.innodroid.complextransitiondemo.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.innodroid.complextransitiondemo.ImageSource;
import com.innodroid.complextransitiondemo.R;
import com.innodroid.complextransitiondemo.ui.PagerActivity;
import com.squareup.picasso.Picasso;

public class ThumbnailRecyclerAdapter extends RecyclerView.Adapter<ThumbnailRecyclerAdapter.ViewHolder> {
    private Context context;

    private static final String TRANSITION_NAME = "transition";

    public ThumbnailRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_thumbnail, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(ImageSource.getThumbnailAtPosition(position)).placeholder(R.mipmap.ic_launcher).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return ImageSource.ImageCount;
    }

    private void showPagerActivity(View view, int position) {
        Intent intent = new Intent(context, PagerActivity.class);
        intent.putExtra(PagerActivity.EXTRA_POSITION, position);

        Activity activity = (Activity)context;
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, TRANSITION_NAME);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    public View getViewAtIndex(RecyclerView recycler, int index) {
        if (index >= 0) {
            for (int i=0; i<recycler.getChildCount(); i++) {
                View child = recycler.getChildAt(i);

                int pos = recycler.getChildAdapterPosition(child);
                if (pos == index) {
                    return child;
                }
            }
        }

        // There is no view for this index - it is offscreen
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.thumbnail_image);

            itemView.setOnClickListener(ClickItemListener);
        }

        private View.OnClickListener ClickItemListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPagerActivity(v, getLayoutPosition());
            }
        };
    }
}
