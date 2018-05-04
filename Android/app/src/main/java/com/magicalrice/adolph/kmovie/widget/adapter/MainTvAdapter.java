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
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.widget.recyclerview.MagicalRecyclerAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by Adolph on 2018/4/20.
 */

public class MainTvAdapter extends MagicalRecyclerAdapter<BaseTvShow> implements ListPreloader.PreloadModelProvider{
    private List<BaseTvShow> tvList;
    private LayoutInflater mInflater;
    private Context context;

    public MainTvAdapter(Context context, List<BaseTvShow> tvList) {
        super(tvList);
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.tvList = tvList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopularTvHolder(mInflater.inflate(R.layout.item_movie_layout, parent, false));
    }

    @Override
    public void onBindBaseViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseTvShow bean = tvList.get(position);
        PopularTvHolder baseHolder = (PopularTvHolder) holder;
        if (bean != null) {
            String imgPath = "";
            if (bean.getPoster_path() != null) {
                imgPath = bean.getPoster_path();
            } else {
                if (bean.getBackdrop_path() != null) {
                    imgPath = bean.getBackdrop_path();
                }
            }
            if (!TextUtils.isEmpty(imgPath)) {
                GlideApp.with(context)
                        .load(ApiConstants.TMDB_IMAGE_PATH + "/w400" + imgPath)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(baseHolder.imgMovie);
            }
            baseHolder.tvRelease.setText(bean.getFirst_air_date());
            baseHolder.tvTitle.setText(bean.getName());
        }
    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    @NonNull
    @Override
    public List getPreloadItems(int position) {
        String url = "";
        if (tvList != null && tvList.size() > 0) {
            url = tvList.get(position).getPoster_path();
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

    class PopularTvHolder extends RecyclerView.ViewHolder {
        ImageView imgMovie;
        TextView tvTitle, tvRelease;

        public PopularTvHolder(View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.img_video);
            tvTitle = itemView.findViewById(R.id.tv_video_title);
            tvRelease = itemView.findViewById(R.id.tv_release_date);
        }
    }
}
