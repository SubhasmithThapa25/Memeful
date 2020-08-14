package com.subhasmith.assignment.infrrd.Views.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.subhasmith.assignment.infrrd.APIUtils.NetworkState;
import com.subhasmith.assignment.infrrd.Entities.Datum;
import com.subhasmith.assignment.infrrd.R;


/**
 * Created by Subhasmith
 */

public class ImGurImagesAdapter extends PagedListAdapter<Datum, RecyclerView.ViewHolder> {
    public static final int MOVIE_ITEM_VIEW_TYPE = 1;
    public static final int LOAD_ITEM_VIEW_TYPE = 0;
    private Context mContext;
    private NetworkState mNetworkState;

    public ImGurImagesAdapter(Context context) {
        super(Datum.DIFF_CALL);
        mContext = context;
    }


    @Override
    public int getItemViewType(int position) {
        return (isLoadingData() && position == getItemCount() - 1) ? LOAD_ITEM_VIEW_TYPE : MOVIE_ITEM_VIEW_TYPE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == MOVIE_ITEM_VIEW_TYPE) {
            view = inflater.inflate(R.layout.movie_item_v1, parent, false);
            return new MovieViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.load_progress_item, parent, false);
            return new ProgressViewHolder(view);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MovieViewHolder) {
            MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
            Datum datum = getItem(position);
            assert datum != null;
            movieViewHolder.bind(datum, mContext);
        }


    }

    public void setNetworkState(NetworkState networkState) {
        boolean wasLoading = isLoadingData();
        mNetworkState = networkState;
        boolean willLoad = isLoadingData();
        if (wasLoading != willLoad) {
            if (wasLoading) notifyItemRemoved(getItemCount());
            else notifyItemInserted(getItemCount());
        }
    }

    public boolean isLoadingData() {
        return (mNetworkState != null && mNetworkState != NetworkState.LOADED);
    }

    private static class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView points;
        public ImageView moviePosterImageView;

        public MovieViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            points = itemView.findViewById(R.id.points);
            moviePosterImageView = itemView.findViewById(R.id.image);
            //ratingBar = itemView.findViewById(R.id.movie_rating_bar);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Datum datum, Context context) {

            title.setText(datum.getTitle());
            points.setText(datum.getPoints() + "");

            if (datum.getImages() != null) {
                if (datum.getImages().get(0).getType().contains("image/jpeg") ||
                        datum.getImages().get(0).getType().contains("image/png")) {
                    if (datum.getImages().get(0).getLink() != null) {
                        Glide
                                .with(context)
                                .load(datum.getImages().get(0).getLink())
                                .fitCenter()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(moviePosterImageView);

                    }
                } else if (datum.getImages().get(0).getType().contains("video/mp4")) {
                    if (datum.getImages().get(0).getGifv() != null) {
                        Glide
                                .with(context)
                                .asGif()
                                .load(datum.getImages().get(0).getGifv())
                                .fitCenter()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(moviePosterImageView);

                    }
                } else if (datum.getImages().get(0).getType().contains("image/gif")) {
                    if (datum.getImages().get(0).getLink() != null) {
                        Glide
                                .with(context)
                                .asGif()
                                .load(datum.getImages().get(0).getLink())
                                .fitCenter()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(moviePosterImageView);

                    }
                }

            }
        }
    }

    private static class ProgressViewHolder extends RecyclerView.ViewHolder {

        public ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }
}
