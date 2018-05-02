package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.MainHomeActivity;
import com.magicalrice.adolph.kmovie.databinding.FragmentMainHomeBinding;
import com.magicalrice.adolph.kmovie.viewmodule.MainHomeViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.widget.adapter.MainHomeAdapter;
import com.magicalrice.adolph.kmovie.widget.scrolltag.onScrollSelectTagListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

/**
 * Created by Adolph on 2018/5/2.
 */

public class MainHomeFragment extends BaseFragment<FragmentMainHomeBinding> {
    private MainHomeViewModule viewModule;
    private MainHomeAdapter adapter;
    private List<String> genreList = new ArrayList<>();
    @Inject
    MainViewModuleFactory factory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModule = ViewModelProviders.of(this,factory).get(MainHomeViewModule.class);
        viewModule.getMovieGenre();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main_home;
    }

    @Override
    public void createView(View view) {
        initView();
        initData();
    }

    private void initView() {
        adapter = new MainHomeAdapter(getFragmentManager());
    }

    private void initData() {
        viewModule.genreData.observe(this, genreResults -> {
            adapter.addDatas(genreResults.getGenres());
            genreList.addAll(StreamSupport.stream(genreResults.getGenres()).map(genre -> genre.getName()).collect(Collectors.toList()));
            ((MainHomeActivity)getActivity()).updateTag(binding.viewPager,genreList,-1);
        });
    }
}
