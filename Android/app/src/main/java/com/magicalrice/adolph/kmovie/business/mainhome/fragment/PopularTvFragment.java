package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseFragment;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;
import com.magicalrice.adolph.kmovie.databinding.FragmentMainHomeTvBinding;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.viewmodule.TvViewModule;
import com.magicalrice.adolph.kmovie.widget.adapter.PopularMovieAdapter;
import com.magicalrice.adolph.kmovie.widget.adapter.PopularTvAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/4/19.
 */

public class PopularTvFragment extends BaseFragment<FragmentMainHomeTvBinding> {
    private TvViewModule viewModule;
    private PopularTvAdapter adapter;
    private List<BaseTvShow> tvList;
    @Inject
    MainViewModuleFactory factory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModule = ViewModelProviders.of(this,factory).get(TvViewModule.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main_home_tv;
    }

    @Override
    public void createView(View view) {
        if (tvList == null) {
            tvList = new ArrayList<>();
            adapter = new PopularTvAdapter(getContext(),tvList);
            binding.setAdapter(adapter);
        }
        viewModule.getPopularTv(1);
        showMovie();
    }

    public void showMovie() {
        viewModule.tvList.observe(this,baseTvShows -> {
            tvList.addAll(baseTvShows);
            adapter.notifyItemRangeInserted(tvList.size() - baseTvShows.size(),baseTvShows.size());
        });
    }
}
