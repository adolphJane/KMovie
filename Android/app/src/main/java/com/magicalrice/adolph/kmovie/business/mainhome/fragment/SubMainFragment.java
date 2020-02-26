package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.ListPreloader.PreloadSizeProvider;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseDaggerFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.MainHomeActivity;
import com.magicalrice.adolph.kmovie.business.movie_detail.VideoDetailActivity;
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
    private static final int REQUEST_COUNT = 20;
    private ArrayList<BaseVideo> videoList;
    private MainVideoAdapter movieAdapter;
    private Genre genre;
    private int page = 1;
    @Inject
    MainViewModuleFactory factory;
    private int type = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
        genre = getArguments().getParcelable("gener");
        viewModule = new ViewModelProvider(getActivity(), factory).get(MainHomeViewModule.class);
        viewModule.clearCache();
        if (genre != null) {
            requestNewData();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("genre", genre);
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
        binding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(-1)) {
                    if (getActivity() != null) {
                        ((MainHomeActivity) getActivity()).hideFabButton();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
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
            genre = savedInstanceState.getParcelable("genre");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        page = 1;
    }

    private void requestNewData() {
        viewModule.getVideos(genre.getId(), page, type).subscribe(baseVideos -> {
            updateData(baseVideos);
        }, throwable -> {
            LUtils.e(throwable.getMessage());
        });
    }

    private void updateData(List<BaseVideo> videos) {
        if (binding.progressbar.getVisibility() == View.VISIBLE) {
            binding.progressbar.setVisibility(View.GONE);
        }
        page++;
        if (videos.size() > 0) {
            movieAdapter.addData(videos);
            if (page <= videos.get(0).getTotalPage()) {
                movieAdapter.loadMoreComplete();
            } else {
                movieAdapter.loadMoreEnd();
            }
        }
    }

    private void initData() {
        viewModule.shoudTop.observe(this, aBoolean -> {
            if (aBoolean && isVisible() && isResumed() && viewModule.getVideoType() == type) {
                binding.recycler.smoothScrollToPosition(0);
                viewModule.shoudTop.setValue(false);
            }
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
                VideoDetailActivity.startActivity(getActivity(), video.getId(), video.getOverview(), type);
            }
        }
    }
}
