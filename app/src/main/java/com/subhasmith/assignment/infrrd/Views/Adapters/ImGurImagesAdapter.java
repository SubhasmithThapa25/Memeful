package com.subhasmith.assignment.infrrd.Views.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.klinker.android.simple_videoview.SimpleVideoView;
import com.subhasmith.assignment.infrrd.APIUtils.NetworkState;
import com.subhasmith.assignment.infrrd.Entities.Datum;
import com.subhasmith.assignment.infrrd.R;


/**
 * Created by Subhasmith
 */

public class ImGurImagesAdapter extends PagedListAdapter<Datum, RecyclerView.ViewHolder> {
    public static final int IMAGE_ITEM_VIEW_TYPE = 1;
    public static final int LOAD_ITEM_VIEW_TYPE = 0;
    public static final int VIDEO_ITEM_VIEW_TYPE = 2;
    private Context mContext;
    private NetworkState mNetworkState;

    public ImGurImagesAdapter(Context context) {
        super(Datum.DIFF_CALL);
        mContext = context;
    }


    @Override
    public int getItemViewType(int position) {
        Datum datum = getItem(position);
        if (isLoadingData() && position == getItemCount() - 1) {
            return LOAD_ITEM_VIEW_TYPE;
        }else {
            assert datum != null;



            if (datum.getImages() != null) {
                if (datum.getImages() !=null && datum.getImages().get(0).getType() != null && datum.getImages().get(0).getType().contains("image/jpeg") ||
                        datum.getImages().get(0).getType().contains("image/png") ||
                        datum.getImages().get(0).getType().contains("image/gif")) {
                    return IMAGE_ITEM_VIEW_TYPE;
                }else {
                    return VIDEO_ITEM_VIEW_TYPE;
                }
            }else {
                return IMAGE_ITEM_VIEW_TYPE;

            }

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == IMAGE_ITEM_VIEW_TYPE) {
            view = inflater.inflate(R.layout.list_item, parent, false);
            return new ImageViewHolder(view);
        }else if (viewType == VIDEO_ITEM_VIEW_TYPE){
            view = inflater.inflate(R.layout.list_video, parent, false);
            return new VideoViewHolder(view);
        }
        else {
            view = inflater.inflate(R.layout.load_progress_item, parent, false);
            return new ProgressViewHolder(view);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ImageViewHolder) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            Datum datum = getItem(position);
            assert datum != null;
            imageViewHolder.bind(datum, mContext);
        }else if (holder instanceof VideoViewHolder) {
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            Datum datum = getItem(position);
            assert datum != null;
            videoViewHolder.bind(datum, mContext);
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

    private static  class VideoViewHolder extends RecyclerView.ViewHolder {

        private SimpleVideoView videoView;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_view);
        }
        public void bind(Datum datum, Context context){
            videoView.setErrorTracker(new SimpleVideoView.VideoPlaybackErrorTracker() {
                @Override
                public void onPlaybackError(Exception e) {
                    e.printStackTrace();
                }
            });
            if (datum.getImages().get(0).getWidth() != null && datum.getImages().get(0).getHeight() != null){
                videoView.setLayoutParams(new LinearLayoutCompat.LayoutParams(datum.getImages().get(0).getWidth(),datum.getImages().get(0).getHeight()));
            }
            videoView.start(datum.getImages().get(0).getLink());
            videoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (videoView.isPlaying())
                        videoView.pause();
                    else
                        videoView.play();
                }
            });
        }
    }
    private static class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView points;
        public ImageView imagePosterImageView;
        public LinearLayoutCompat llImage;

        public ImageViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            points = itemView.findViewById(R.id.points);
            imagePosterImageView = itemView.findViewById(R.id.image);
            llImage = itemView.findViewById(R.id.llImage);
            //ratingBar = itemView.findViewById(R.id.movie_rating_bar);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Datum datum, Context context) {

            title.setText(datum.getTitle());
            points.setText(datum.getPoints() + "");

            if (datum.getImages() != null) {
                if (datum.getImages().get(0).getWidth() != null && datum.getImages().get(0).getHeight() != null){
                    imagePosterImageView.setLayoutParams(new RelativeLayout.LayoutParams(datum.getImages().get(0).getWidth(),datum.getImages().get(0).getHeight()));
                }
                if (datum.getImages().get(0).getType().contains("image/jpeg") ||
                        datum.getImages().get(0).getType().contains("image/png")) {
                    if (datum.getImages().get(0).getLink() != null) {
                        Glide
                                .with(context)
                                .load(datum.getImages().get(0).getLink())
                                //.fitCenter()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(imagePosterImageView);

                    }
                } else if (datum.getImages().get(0).getType().contains("video/mp4")) {
                    if (datum.getImages().get(0).getGifv() != null) {
                        Glide
                                .with(context)
                                .asGif()
                                .load(datum.getImages().get(0).getGifv())
                                //.fitCenter()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(imagePosterImageView);

                    }
                } else if (datum.getImages().get(0).getType().contains("image/gif")) {
                    if (datum.getImages().get(0).getLink() != null) {
                        Glide
                                .with(context)
                                .asGif()
                                .load(datum.getImages().get(0).getLink())
                                //.fitCenter()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder)
                                .transition(DrawableTransitionOptions.withCrossFade())
                                .into(imagePosterImageView);

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
