package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.ListPreloader.PreloadSizeProvider;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseFragment;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;
import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.Genre;
import com.magicalrice.adolph.kmovie.data.entities.VideoResultsPage;
import com.magicalrice.adolph.kmovie.databinding.FragmentSubMainHomeBinding;
import com.magicalrice.adolph.kmovie.utils.LUtils;
import com.magicalrice.adolph.kmovie.viewmodule.MainHomeViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.widget.adapter.MainMovieAdapter;
import com.magicalrice.adolph.kmovie.widget.adapter.MainTvAdapter;
import com.magicalrice.adolph.kmovie.widget.recyclerview.EndlessRecyclerOnScrollListener;
import com.magicalrice.adolph.kmovie.widget.recyclerview.LoadingFooter;
import com.magicalrice.adolph.kmovie.widget.recyclerview.MagicalRecyclerAdapter;

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
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_sub_main_home;
    }

    @Override
    public void createView(View view) {
        PreloadSizeProvider sizeProvider = new ViewPreloadSizeProvider();
        RecyclerViewPreloader preloader = null;
        binding.recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        videoList = new ArrayList<>();
        movieAdapter = new MainMovieAdapter(getActivity(), videoList);
        binding.recycler.setAdapter(movieAdapter);
        preloader = new RecyclerViewPreloader(this, movieAdapter, sizeProvider, 10);
        if (preloader != null) {
            binding.recycler.addOnScrollListener(preloader);
        }

        viewModule.videoData.observe(this, videoResultsPage -> {
            if (binding.progressbar.getVisibility() == View.VISIBLE) {
                binding.progressbar.setVisibility(View.GONE);
            }
            List<BaseVideo> temp = videoResultsPage.getResults();
            videoList.addAll(temp);
            movieAdapter.notifyItemRangeInserted(videoList.size() - temp.size(), temp.size());
            LUtils.e("线程" + (Looper.myLooper() == Looper.getMainLooper()));
        });
        binding.progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            List<BaseVideo> temp = savedInstanceState.getParcelableArrayList("videoList");
            if (temp != null && temp.size() > 0) {
                videoList.addAll(temp);
                movieAdapter.notifyDataSetChanged();
                if (binding.progressbar.getVisibility() == View.VISIBLE) {
                    binding.progressbar.setVisibility(View.GONE);
                }
            }
        }
    }

    private void requestNewData() {

    }

    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {

        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
//
//            if (type == 1 && movieAdapter != null) {
//                @LoadingFooter.State int state = movieAdapter.getFooterViewState();
//                if (state == LoadingFooter.LOADING) {
//                    Log.d("@Cundong", "the state is Loading, just wait..");
//                    return;
//                }
//
//                if (page < totalPage) {
//                    // loading more
//                    movieAdapter.setFooterViewState(REQUEST_COUNT, LoadingFooter.LOADING, null);
//                    requestNewData();
//                } else {
//                    //the end
//                    movieAdapter.setFooterViewState(REQUEST_COUNT, LoadingFooter.THE_END, null);
//                }
//            } else if (type == 2 && tvAdapter != null) {
//                @LoadingFooter.State int state = tvAdapter.getFooterViewState();
//                if(state == LoadingFooter.LOADING) {
//                    Log.d("@Cundong", "the state is Loading, just wait..");
//                    return;
//                }
//
//                if (page < totalPage) {
//                    // loading more
//                    tvAdapter.setFooterViewState(REQUEST_COUNT, LoadingFooter.LOADING, null);
//                    requestNewData();
//                } else {
//                    //the end
//                    movieAdapter.setFooterViewState(REQUEST_COUNT, LoadingFooter.THE_END, null);
//                }
//            }

        }
    };
}
