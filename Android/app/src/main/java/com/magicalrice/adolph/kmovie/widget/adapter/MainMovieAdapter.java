package com.magicalrice.adolph.kmovie.widget.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.widget.recyclerview.MagicalRecyclerAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by Adolph on 2018/4/20.
 */

public class MainMovieAdapter extends MagicalRecyclerAdapter<BaseVideo> implements ListPreloader.PreloadModelProvider {
    private List<BaseVideo> videoList;
    private LayoutInflater mInflater;
    private Context context;

    public MainMovieAdapter(Context context, List<BaseVideo> videos) {
        super(videos);
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.videoList = videos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopularMovieHolder(mInflater.inflate(R.layout.item_movie_layout, parent, false));
    }

    @Override
    public void onBindBaseViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseVideo bean = videoList.get(position);
        PopularMovieHolder baseHolder = (PopularMovieHolder) holder;
        if (bean != null) {
            GlideApp.with(context)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "/w400" + bean.getPoster_path())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(baseHolder.imgVideo);
            baseHolder.tvRelease.setText(bean.getRelease_date());
            baseHolder.tvTitle.setText(bean.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    @NonNull
    @Override
    public List getPreloadItems(int position) {
        String url = "";
        if (videoList != null && videoList.size() > 0) {
            url = videoList.get(position).getPoster_path();
        }
        if (TextUtils.isEmpty(url)) {
            return Collections.emptyList();
        }
        return Collections.singletonList(url);
    }

    @Nullable
    @Override
    public RequestBuilder<?> getPreloadRequestBuilder(@NonNull Object item) {
        return GlideApp.with(context)
                .load(item);
    }

    class PopularMovieHolder extends RecyclerView.ViewHolder {
        ImageView imgVideo;
        TextView tvTitle, tvRelease;
        ViewGroup viewWrapper;

        public PopularMovieHolder(View itemView) {
            super(itemView);
            viewWrapper = itemView.findViewById(R.id.view_wrapper);
            imgVideo = itemView.findViewById(R.id.img_video);
            tvTitle = itemView.findViewById(R.id.tv_video_title);
            tvRelease = itemView.findViewById(R.id.tv_release_date);
        }
    }
}
