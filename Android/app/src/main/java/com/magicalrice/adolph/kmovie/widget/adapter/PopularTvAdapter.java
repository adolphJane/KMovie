package com.magicalrice.adolph.kmovie.widget.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;

import java.util.List;

/**
 * Created by Adolph on 2018/4/20.
 */

public class PopularTvAdapter extends RecyclerView.Adapter<PopularTvAdapter.PopularTvHolder> {
    private List<BaseTvShow> tvList;
    private Context context;

    public PopularTvAdapter(Context context,List<BaseTvShow> tvList) {
        this.context = context;
        this.tvList = tvList;
    }

    @NonNull
    @Override
    public PopularTvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_layout, parent, false);
        PopularTvHolder holder = new PopularTvHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularTvHolder holder, int position) {
        BaseTvShow bean = tvList.get(position);
        if (bean != null) {
            Glide.with(context)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "/w400" + bean.getPoster_path())
                    .into(holder.imgMovie);
            holder.tvRelease.setText(bean.getFirst_air_date());
            holder.tvTitle.setText(bean.getName());
        }
    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    class PopularTvHolder extends RecyclerView.ViewHolder {
        ImageView imgMovie;
        TextView tvTitle, tvRelease;

        public PopularTvHolder(View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.img_movie);
            tvTitle = itemView.findViewById(R.id.tv_movie_title);
            tvRelease = itemView.findViewById(R.id.tv_release_date);
        }
    }
}
