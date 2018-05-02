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
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;

import java.util.List;

/**
 * Created by Adolph on 2018/4/20.
 */

public class MainMovieAdapter extends RecyclerView.Adapter<MainMovieAdapter.PopularMovieHolder> {
    private List<BaseMovie> movieList;
    private Context context;

    public MainMovieAdapter(Context context, List<BaseMovie> movies) {
        this.context = context;
        this.movieList = movies;
    }

    @NonNull
    @Override
    public PopularMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_layout, parent, false);
        PopularMovieHolder holder = new PopularMovieHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMovieHolder holder, int position) {
        BaseMovie bean = movieList.get(position);
        if (bean != null) {
            Glide.with(context)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "/w400" + bean.getPoster_path())
                    .into(holder.imgMovie);
            holder.tvRelease.setText(bean.getRelease_date());
            holder.tvTitle.setText(bean.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class PopularMovieHolder extends RecyclerView.ViewHolder {
        ImageView imgMovie;
        TextView tvTitle, tvRelease;

        public PopularMovieHolder(View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.img_movie);
            tvTitle = itemView.findViewById(R.id.tv_movie_title);
            tvRelease = itemView.findViewById(R.id.tv_release_date);
        }
    }
}
