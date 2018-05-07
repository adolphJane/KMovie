package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.bumptech.glide.ListPreloader.PreloadSizeProvider;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseFragment;
import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.Genre;
import com.magicalrice.adolph.kmovie.databinding.FragmentSubMainHomeBinding;
import com.magicalrice.adolph.kmovie.utils.LUtils;
import com.magicalrice.adolph.kmovie.viewmodule.MainHomeViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.widget.adapter.MainMovieAdapter;
import com.magicalrice.adolph.kmovie.widget.recyclerview.EndlessRecyclerOnScrollListener;
import com.magicalrice.adolph.kmovie.widget.recyclerview.HeaderSpanSizeLookup;
import com.magicalrice.adolph.kmovie.widget.recyclerview.LoadingFooter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/5/2.
 */

public class SubMainFragment extends BaseFragment<FragmentSubMainHomeBinding> {
    private MainHomeViewModule viewModule;
    private int totalPage = 0;
    private static final int REQUEST_COUNT = 20;
    private ArrayList<BaseVideo> videoList;
    private MainMovieAdapter movieAdapter;
    private Genre genre;
    private int page = 1;
    @Inject
    MainViewModuleFactory factory;
    private int type = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
        genre = getArguments().getParcelable("gener");
        viewModule = ViewModelProviders.of(this, factory).get(MainHomeViewModule.class);
        if (genre != null) {
            if (type == 1 && savedInstanceState == null) {
                viewModule.getMovies(genre.getId(), page);
            } else if (type == 2 && savedInstanceState == null) {
                viewModule.getTvs(genre.getId(), page);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (videoList != null && videoList.size() > 0) {
            outState.putParcelableArrayList("videoList", videoList);
        }
        outState.putParcelable("genre",genre);
        outState.putInt("page",page);
        outState.putInt("totalPage",totalPage);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_sub_main_home;
    }

    @Override
    public void createView(View view) {
        PreloadSizeProvider sizeProvider = new ViewPreloadSizeProvider();
        RecyclerViewPreloader preloader = null;
        videoList = new ArrayList<>();
        movieAdapter = new MainMovieAdapter(getActivity(), videoList);
        binding.recycler.setAdapter(movieAdapter);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        manager.setSpanSizeLookup(new HeaderSpanSizeLookup(movieAdapter,2));
        binding.recycler.setLayoutManager(manager);
        preloader = new RecyclerViewPreloader(this, movieAdapter, sizeProvider, 10);
        movieAdapter.setFooterViewState(REQUEST_COUNT,LoadingFooter.NORMAL,null);
        binding.recycler.scrollToPosition(0);
        if (preloader != null) {
            binding.recycler.addOnScrollListener(preloader);
            binding.recycler.addOnScrollListener(mOnScrollListener);
        }

        viewModule.videoData.observe(this, videoResultsPage -> {
            if (binding.progressbar.getVisibility() == View.VISIBLE) {
                binding.progressbar.setVisibility(View.GONE);
            }
            totalPage = videoResultsPage.getTotal_pages();
            List<BaseVideo> temp = videoResultsPage.getResults();
            movieAdapter.setFooterViewState(LoadingFooter.NORMAL);
            movieAdapter.addAll(temp);
        });
        binding.progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            List<BaseVideo> temp = savedInstanceState.getParcelableArrayList("videoList");
            if (temp != null && temp.size() > 0) {
                movieAdapter.addAll(temp);
                if (binding.progressbar.getVisibility() == View.VISIBLE) {
                    binding.progressbar.setVisibility(View.GONE);
                }
            }
            page = savedInstanceState.getInt("page");
            genre = savedInstanceState.getParcelable("genre");
            totalPage = savedInstanceState.getInt("totalPage");
        }
    }

    private void requestNewData() {
        if (type == 1) {
            viewModule.getMovies(genre.getId(),page);
        } else if (type == 2) {
            viewModule.getTvs(genre.getId(),page);
        }
    }

    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {

        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);

            @LoadingFooter.State int state = movieAdapter.getFooterViewState();
            if (state == LoadingFooter.LOADING) {
                return;
            }

            if (page < totalPage) {
                // loading more
                movieAdapter.setFooterViewState(REQUEST_COUNT, LoadingFooter.LOADING, null);
                page++;
                requestNewData();
            } else {
                //the end
                movieAdapter.setFooterViewState(REQUEST_COUNT, LoadingFooter.THE_END, null);
            }
        }
    };
}
