package com.magicalrice.adolph.kmovie.business.movie_detail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.data.entities.BaseKeyword;
import com.magicalrice.adolph.kmovie.data.entities.CastMember;
import com.magicalrice.adolph.kmovie.data.entities.Credits;
import com.magicalrice.adolph.kmovie.data.entities.CrewMember;
import com.magicalrice.adolph.kmovie.data.entities.Genre;
import com.magicalrice.adolph.kmovie.data.entities.Image;
import com.magicalrice.adolph.kmovie.data.entities.Images;
import com.magicalrice.adolph.kmovie.data.entities.Keywords;
import com.magicalrice.adolph.kmovie.data.entities.Movie;
import com.magicalrice.adolph.kmovie.data.entities.MovieDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDatesResult;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDatesResults;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.data.repository.CountryDataSource;
import com.magicalrice.adolph.kmovie.databinding.ActivityMovieDetailBinding;
import com.magicalrice.adolph.kmovie.utils.ScreenUtils;
import com.magicalrice.adolph.kmovie.utils.Utils;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.viewmodule.MovieDetailViewModule;
import com.magicalrice.adolph.kmovie.widget.adapter.MoviePhotoAdapter;
import com.magicalrice.adolph.kmovie.widget.adapter.ReleaseDateAdapter;
import com.magicalrice.adolph.kmovie.widget.adapter.StarAdapter;

import java.util.List;

import javax.inject.Inject;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

/**
 * Created by Adolph on 2018/5/7.
 */

public class MovieDetailActivity extends BaseActivity<ActivityMovieDetailBinding> implements MovieDetailEvent {
    private MovieDetailViewModule viewModule;
    private ReleaseDateAdapter dateAdapter;
    private StarAdapter starAdapter;
    private MoviePhotoAdapter photoBackAdapter,photoPosterAdapter;
    private int type;
    private int movieId;
    @Inject
    MainViewModuleFactory factory;
    @Inject
    CountryDataSource countrySource;

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
        binding.setSelectOne(1);
        binding.setSelectTwo(1);
        binding.setEvent(this);
        if (type == 1) {
            viewModule.getMovieDetail(movieId);
        } else if (type == 2) {

        }
        viewModule.movieDetailBean.observe(this, movieDetailBean -> {
            updateView(movieDetailBean);
        });
    }

    private void updateView(MovieDetailBean bean) {
        if (bean == null) {
            return;
        }
        Movie movie = bean.getMovie();
        Keywords keywords = bean.getKeywords();
        ReleaseDatesResults datesResults = bean.getDatesResults();
        Credits credits = bean.getCredits();
        Images images = bean.getImages();
        binding.ryPhotos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        if (movie != null) {
            GlideApp.with(this)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + bean.getMovie().getPoster_path())
                    .placeholder(R.drawable.item_img_placeholder)
                    .error(R.drawable.item_img_error)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.imgPoster);
            binding.tvMovieTitle.setText(bean.getMovie().getTitle());
            binding.tvMovieRelease.setText(flipTextColor("发行日期:" + bean.getMovie().getRelease_date()));
            binding.tvContentContent.setText(TextUtils.isEmpty(movie.getOverview()) ? "无" : "    " + movie.getOverview());
            List<Genre> genres = movie.getGenres();
            if (genres != null && genres.size() > 0) {
                showGenreTag(StreamSupport.stream(genres).map(genre -> genre.getName()).collect(Collectors.toList()),binding.rlGenre);
            }
            ((TextView)findViewById(R.id.tv_original_title_data)).setText(TextUtils.isEmpty(movie.getOriginal_title()) ? "无" : movie.getOriginal_title());
            ((TextView)findViewById(R.id.tv_original_lanuage_data)).setText(TextUtils.isEmpty(movie.getOriginal_language()) ? "无" : movie.getOriginal_language());
            ((TextView)findViewById(R.id.tv_time_data)).setText(movie.getRuntime() == 0 ? "无" : Utils.getTime(movie.getRuntime()));
            ((TextView)findViewById(R.id.tv_budget_data)).setText(movie.getBudget() == 0 ? "无" : Utils.getRevenue(movie.getBudget()) + "");
            ((TextView)findViewById(R.id.tv_box_office_data)).setText(movie.getRevenue() == 0 ? "无" : Utils.getRevenue(movie.getRevenue()) + "");
            ((TextView)findViewById(R.id.tv_homepage_data)).setText(TextUtils.isEmpty(movie.getHomepage()) ? "无" : movie.getHomepage());
        }
        if (keywords != null) {
            List<BaseKeyword> keyList = keywords.getKeywords();
            if (keyList != null && keyList.size() > 0) {
                showGenreTag(StreamSupport.stream(keyList).map(baseKeyword -> baseKeyword.getName()).collect(Collectors.toList()),binding.rlKeyword);
            }
        }
        if (datesResults != null) {
            List<ReleaseDatesResult> dataList = datesResults.getResults();
            if (dataList != null && dataList.size() > 0) {
                dateAdapter = new ReleaseDateAdapter(R.layout.item_release_date_layout,dataList,countrySource);
                binding.ryShowTime.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
                binding.ryShowTime.setAdapter(dateAdapter);
            }
        }

        if (credits != null) {
            List<CastMember> memberList = credits.getCast();
            if (memberList != null && memberList.size() > 0) {
                starAdapter = new StarAdapter(R.layout.item_movie_star_layout,memberList);
                binding.ryPhotos.setAdapter(starAdapter);
            }
            List<CrewMember> memberList1 = credits.getCrew();
            if (memberList1 != null && memberList1.size() > 0) {
                if (memberList1.get(0).getJob().equalsIgnoreCase("Director")) {
                    binding.tvMovieDirector.setText(flipTextColor("导演:" + memberList1.get(0).getName()));
                } else {
                    binding.tvMovieDirector.setText(flipTextColor("导演:无"));
                }
            }
        }

        if (images != null) {
            List<Image> imgBackList = images.getBackdrops();
            if (imgBackList != null) {
                photoBackAdapter = new MoviePhotoAdapter(R.layout.item_movie_photo,imgBackList);
                photoBackAdapter.setEmptyView(R.layout.item_empty_view,binding.rlMovieInfo);
            }

            List<Image> imgPosterList = images.getPosters();
            if (imgPosterList != null) {
                photoPosterAdapter = new MoviePhotoAdapter(R.layout.item_movie_photo,imgPosterList);
                photoPosterAdapter.setEmptyView(R.layout.item_empty_view,binding.rlMovieInfo);
            }
        }
    }

    public static void startActivity(FragmentActivity activity, int movieId, int type) {
        Intent intent = new Intent(activity, MovieDetailActivity.class);
        intent.putExtra("movieId", movieId);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    private void showGenreTag(List<String> words, RelativeLayout parent) {
        if (words != null && words.size() > 0 && parent != null) {
            parent.post(() -> {
                int preId = 0, textViewWidth = 0, lines = 1;
                int wordTagId = 10001;
                int width = parent.getMeasuredWidth();
                for (int i = 0; i < words.size(); i++) {
                    String word = words.get(i);
                    TextView textView = new TextView(this);
                    textView.setBackgroundResource(R.drawable.shape_color_black6_20dp_corners);
                    textView.setPadding((int) ScreenUtils.dp2px(this, 10), (int) ScreenUtils.dp2px(this, 3), (int) ScreenUtils.dp2px(this, 10), (int) ScreenUtils.dp2px(this, 3));
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextColor(ContextCompat.getColor(this, R.color.white_4));
                    textView.setText(word);
                    RelativeLayout.LayoutParams tvParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    textView.measure(spec, spec);
                    if (preId > 0) {
                        //测量textview
                        if (width >= textViewWidth + textView.getMeasuredWidth() + (int) ScreenUtils.dp2px(this, 15)) {
                            tvParams.addRule(RelativeLayout.RIGHT_OF, preId);
                            tvParams.setMarginStart((int) ScreenUtils.dp2px(this, 15));
                        } else {
                            lines++;
                            textViewWidth = 0;
                        }
                    }
                    tvParams.setMargins(0, (int) ((textView.getMeasuredHeight() + ScreenUtils.dp2px(this, 5)) * (lines - 1)), 0, 0);
                    textViewWidth += textView.getMeasuredWidth() + (int) ScreenUtils.dp2px(this, 15);
                    textView.setId(wordTagId + i);
                    preId = textView.getId();
                    parent.addView(textView, tvParams);
                }
            });
        }
    }

    private CharSequence flipTextColor(String content) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        if (content.contains(":")) {
            SpannableString ss = new SpannableString(content);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.yellow_1));
            int end = content.indexOf(":");
            ss.setSpan(colorSpan, 0, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ss;
        }
        return content;
    }

    @Override
    public void switchBaseInfo(int position) {
        binding.setSelectOne(position);
        switch (position) {
            case 1:
                break;
            case 2:
                binding.ryPhotos.setAdapter(starAdapter);
                break;
            case 3:
                binding.ryPhotos.setAdapter(photoBackAdapter);
                break;
            case 4:
                binding.ryPhotos.setAdapter(photoPosterAdapter);
                break;
        }
    }

    @Override
    public void switchMovieInfo(int position) {
        binding.setSelectTwo(position);
    }

    private void showInfo() {

    }
}
