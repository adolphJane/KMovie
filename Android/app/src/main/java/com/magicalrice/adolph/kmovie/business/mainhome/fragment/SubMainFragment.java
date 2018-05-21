package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.bumptech.glide.ListPreloader.PreloadSizeProvider;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseDaggerFragment;
import com.magicalrice.adolph.kmovie.base.BaseFragment;
import com.magicalrice.adolph.kmovie.business.movie_detail.MovieDetailActivity;
import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.Genre;
import com.magicalrice.adolph.kmovie.databinding.FragmentSubMainHomeBinding;
import com.magicalrice.adolph.kmovie.utils.LUtils;
import com.magicalrice.adolph.kmovie.viewmodule.MainHomeViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.widget.adapter.MainVideoAdapter;
import com.magicalrice.adolph.kmovie.widget.view.MagicalLoadMoreView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/5/2.
 */

public class SubMainFragment extends BaseDaggerFragment<FragmentSubMainHomeBinding> implements BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    private MainHomeViewModule viewModule;
    private int totalPage = 0;
    private static final int REQUEST_COUNT = 20;
    private ArrayList<BaseVideo> videoList;
    private MainVideoAdapter movieAdapter;
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
        if (genre != null && savedInstanceState == null) {
            requestNewData();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (videoList != null && videoList.size() > 0) {
            outState.putParcelableArrayList("videoList", videoList);
        }
        outState.putParcelable("genre", genre);
        outState.putInt("page", page);
        outState.putInt("totalPage", totalPage);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_sub_main_home;
    }

    @Override
    public void createView(View view) {
        PreloadSizeProvider sizeProvider = new ViewPreloadSizeProvider();
        RecyclerViewPreloader preloader = null;
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        videoList = new ArrayList<>();
        movieAdapter = new MainVideoAdapter(R.layout.item_movie_layout, videoList);
        movieAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        movieAdapter.setEnableLoadMore(true);
        movieAdapter.setLoadMoreView(new MagicalLoadMoreView());
        movieAdapter.setOnItemClickListener(this);
        binding.recycler.setAdapter(movieAdapter);
        movieAdapter.setOnLoadMoreListener(this, binding.recycler);
        binding.recycler.setLayoutManager(manager);
        preloader = new RecyclerViewPreloader(this, movieAdapter, sizeProvider, 10);
        binding.recycler.scrollToPosition(0);
        if (preloader != null) {
            binding.recycler.addOnScrollListener(preloader);
        }
        binding.progressbar.setVisibility(View.VISIBLE);
        initData();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            List<BaseVideo> temp = savedInstanceState.getParcelableArrayList("videoList");
            if (temp != null && temp.size() > 0) {
                movieAdapter.addData(temp);
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
            viewModule.getMovies(genre.getId(), page);
        } else if (type == 2) {
            viewModule.getTvs(genre.getId(), page);
        }
    }

    private void initData() {
        viewModule.videoData.observe(this, videoResultsPage -> {
            if (binding.progressbar.getVisibility() == View.VISIBLE) {
                binding.progressbar.setVisibility(View.GONE);
            }
            page++;
            totalPage = videoResultsPage.getTotal_pages();
            List<BaseVideo> temp = videoResultsPage.getResults();
            if (temp.size() > 0) {
                movieAdapter.addData(temp);
            }
            if (page <= totalPage) {
                movieAdapter.loadMoreComplete();
            } else {
                movieAdapter.loadMoreEnd();
            }
            LUtils.e("page%d,totalPage%d", page, totalPage);
        });
    }

    @Override
    public void onLoadMoreRequested() {
        requestNewData();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (position >= 0 && position < videoList.size()) {
            BaseVideo video = videoList.get(position);
            if (video != null) {
                MovieDetailActivity.startActivity(getActivity(), video.getId(), video.getOverview(), type);
            }
        }
    }
}
