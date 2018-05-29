package com.magicalrice.adolph.kmovie.business.movie_role;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.google.gson.JsonPrimitive;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.business.other.TextDisplayBoxFragment;
import com.magicalrice.adolph.kmovie.data.entities.BaseMoviePersonCredit;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvPersonCredit;
import com.magicalrice.adolph.kmovie.data.entities.Image;
import com.magicalrice.adolph.kmovie.data.entities.Person;
import com.magicalrice.adolph.kmovie.data.entities.PersonDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.PersonImages;
import com.magicalrice.adolph.kmovie.data.entities.PersonMovieCredits;
import com.magicalrice.adolph.kmovie.data.entities.PersonTvCredits;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.databinding.ActivityMovieRoleBinding;
import com.magicalrice.adolph.kmovie.utils.ChineseEnglishUtil;
import com.magicalrice.adolph.kmovie.utils.Utils;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.viewmodule.RoleViewModule;
import com.magicalrice.adolph.kmovie.widget.adapter.PhotoAdapter;
import com.magicalrice.adolph.kmovie.widget.adapter.SearchResultsAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/5/8.
 */

public class VideoRoleActivity extends BaseActivity<ActivityMovieRoleBinding> implements VideoRoleEvent,NestedScrollView.OnScrollChangeListener {
    private RoleViewModule viewModule;
    private PhotoAdapter photoAdapter;
    private SearchResultsAdapter<BaseMoviePersonCredit> movieAdapter;
    private SearchResultsAdapter<BaseTvPersonCredit> tvAdapter;
    private int personId;
    private String title, overview;
    @Inject
    MainViewModuleFactory factory;

    public static void startActivity(FragmentActivity activity, int personId) {
        Intent intent = new Intent(activity, VideoRoleActivity.class);
        intent.putExtra("personId", personId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personId = getIntent().getIntExtra("personId", -1);
        if (personId == -1) {
            return;
        }
        viewModule = ViewModelProviders.of(this, factory).get(RoleViewModule.class);
        initData();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_movie_role;
    }

    private void initData() {
        binding.setSelectItem(1);
        binding.setEvent(this);
        binding.setIsLoading(true);
        viewModule.getStarSummary(personId);
        viewModule.personData.observe(this, personDetailBean -> {
            updateView(personDetailBean);
        });
    }

    private void updateView(PersonDetailBean bean) {
        if (bean == null) {
            return;
        }
        binding.setIsLoading(false);
        binding.scrollView.setOnScrollChangeListener(this);
        PersonMovieCredits movieCredits = bean.getMovieCredits();
        PersonTvCredits tvCredits = bean.getTvCredits();
        Person person = bean.getPerson();
        PersonImages images = bean.getPersonImages();
        ListPreloader.PreloadSizeProvider sizeProvider = new ViewPreloadSizeProvider();
        RecyclerViewPreloader preloaderMovie = null;
        RecyclerViewPreloader preloaderTv = null;
        if (person != null) {
            GlideApp.with(this)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + person.getProfile_path())
                    .placeholder(R.drawable.item_img_placeholder)
                    .error(R.drawable.item_img_error)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.imgPoster);
            binding.tvStarTitle.setText(Utils.flipTextColor(this, "演员名: " + person.getName(), ":", R.color.yellow_1));
            title = person.getName();
            overview = person.getBiography();
            getChiName(person.getAlso_known_as());
            binding.tvStarGender.setText(Utils.flipTextColor(this, "性别: " + (person.getGender() == 2 ? "男" : "女"), ":", R.color.yellow_1));
            binding.tvStarBirthday.setText(Utils.flipTextColor(this, "出生日期: " + (person.getBirthday() == null ? "----" : "\n" + Utils.getDateTime("yyyy年MM月dd日", person.getBirthday())), ":", R.color.yellow_1));
            binding.tvStarDeath.setText(Utils.flipTextColor(this, "死亡日期: " + (person.getDeathday() == null ? "----" : "\n" + Utils.getDateTime("yyyy年MM月dd日", person.getBirthday())), ":", R.color.yellow_1));
            binding.tvRoleBiography.setText(TextUtils.isEmpty(person.getBiography()) ? "无" : person.getBiography());
        }

        if (images != null) {
            List<Image> photoImages = images.getProfiles();
            photoAdapter = new PhotoAdapter(R.layout.item_movie_photo, photoImages);
            binding.ryPhotos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            binding.ryPhotos.setAdapter(photoAdapter);
        }

        binding.ryVideos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.ryVideos.setNestedScrollingEnabled(false);
        if (movieCredits != null) {
            List<BaseMoviePersonCredit> movieList = movieCredits.getCast();
            if (movieList != null) {
                movieAdapter = new SearchResultsAdapter<>(R.layout.item_search_layout, movieList, 7);
                movieAdapter.setEmptyView(R.layout.item_empty_view, binding.rlRelatedMovies);
                binding.ryVideos.setAdapter(movieAdapter);
                preloaderMovie = new RecyclerViewPreloader(this, movieAdapter, sizeProvider, 10);
                if (preloaderMovie != null) {
                    binding.ryVideos.addOnScrollListener(preloaderMovie);
                }
            }
        }

        if (tvCredits != null) {
            List<BaseTvPersonCredit> tvList = tvCredits.getCast();
            if (tvList != null) {
                tvAdapter = new SearchResultsAdapter<>(R.layout.item_search_layout, tvList, 8);
                tvAdapter.setEmptyView(R.layout.item_empty_view, binding.rlRelatedMovies);
                preloaderTv = new RecyclerViewPreloader(this, tvAdapter, sizeProvider, 10);
                if (preloaderTv != null) {
                    binding.ryVideos.addOnScrollListener(preloaderTv);
                }
            }
        }
    }

    private void getChiName(List<JsonPrimitive> knownAs) {
        if (knownAs != null) {
            for (JsonPrimitive primitive : knownAs) {
                if (ChineseEnglishUtil.isChinese(primitive.getAsString())) {
                    binding.tvTranslatedName.setText(Utils.flipTextColor(this, "译名:" + primitive.getAsString(), ":", R.color.yellow_1));
                    return;
                }
            }
            binding.tvTranslatedName.setText(Utils.flipTextColor(this, "译名:无", ":", R.color.yellow_1));
        }
    }

    @Override
    public void selectItem(int position) {
        binding.setSelectItem(position);
        if (position == 1) {
            binding.ryVideos.setAdapter(movieAdapter);
        } else if (position == 2) {
            binding.ryVideos.setAdapter(tvAdapter);
        }
    }

    @Override
    public void openTextBox() {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(overview)) {
            return;
        }
        TextDisplayBoxFragment.showFragment(this, title, overview);
    }

    @Override
    public void scrollToTop() {
        binding.scrollView.setSmoothScrollingEnabled(true);
        binding.scrollView.smoothScrollTo(0,0);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (v.getScrollY() == 0) {
            binding.floatBtn.hide(new FloatingActionButton.OnVisibilityChangedListener() {
                @Override
                public void onHidden(FloatingActionButton fab) {
                    super.onHidden(fab);
                    fab.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}
