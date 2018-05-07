package com.magicalrice.adolph.kmovie.business.movie_detail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.data.entities.MovieDetailBean;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.databinding.ActivityMovieDetailBinding;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.viewmodule.MovieDetailViewModule;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/5/7.
 */

public class MovieDetailActivity extends BaseActivity<ActivityMovieDetailBinding> {
    private MovieDetailViewModule viewModule;
    private int type;
    private int movieId;
    @Inject
    MainViewModuleFactory factory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getIntExtra("type", -1);
        movieId = getIntent().getIntExtra("movieId", -1);
        if (type == -1 || movieId == -1) {
            this.finish();
            return;
        }
        viewModule = ViewModelProviders.of(this, factory).get(MovieDetailViewModule.class);
        initData();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_movie_detail;
    }

    private void initData() {
        viewModule.getMovieDetail(movieId);
        viewModule.movieDetailBean.observe(this, movieDetailBean -> {
            updateView(movieDetailBean);
        });
    }

    private void updateView(MovieDetailBean bean) {
        GlideApp.with(this)
                .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + bean.getMovie().getPoster_path())
                .placeholder(R.drawable.item_img_placeholder)
                .error(R.drawable.item_img_error)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgPoster);
        binding.tvMovieTitle.setText(bean.getMovie().getTitle());
        binding.tvMovieDirector.setText(flipTextColor("导演:"+bean.getMovie().getPopularity()));
        binding.tvMovieRegion.setText(flipTextColor("国家/地区:" + bean.getMovie().getRelease_date()));
    }

    public static void startActivity(FragmentActivity activity, int movieId, int type) {
        Intent intent = new Intent(activity, MovieDetailActivity.class);
        intent.putExtra("movieId", movieId);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    private CharSequence flipTextColor(String content) {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        if (content.contains(":")) {
            SpannableString ss = new SpannableString(content);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(this,R.color.yellow_1));
            int end = content.indexOf(":");
            ss.setSpan(colorSpan,0,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ss;
        }
        return null;
    }
}
