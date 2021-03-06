package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseDaggerFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.MainHomeActivity;
import com.magicalrice.adolph.kmovie.databinding.FragmentMainHomeBinding;
import com.magicalrice.adolph.kmovie.viewmodule.MainHomeViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.widget.adapter.MainHomeAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

/**
 * Created by Adolph on 2018/5/2.
 */

public class MainHomeFragment extends BaseDaggerFragment<FragmentMainHomeBinding> {
    private MainHomeViewModule viewModule;
    private MainHomeAdapter adapter;
    private List<String> genreList = new ArrayList<>();
    private int type = 1;
    @Inject
    MainViewModuleFactory factory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
        viewModule = new ViewModelProvider(getActivity(), factory).get(MainHomeViewModule.class);
        if (type == 1) {
            viewModule.getMovieGenre();
        } else if (type == 2) {
            viewModule.getTvGenre();
        }
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            ((MainHomeActivity) getActivity()).updateTag(binding.viewPager, genreList, binding.viewPager.getCurrentItem());
            viewModule.setVideoType(type);
        }
    }

    private void initView() {
        adapter = new MainHomeAdapter(getChildFragmentManager());
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(4);
    }

    private void initData() {
        viewModule.genreData.observe(this, genreResults -> {
            if (genreResults.getType() == type) {
                adapter.addDatas(genreResults.getGenres(), type);
                adapter.notifyDataSetChanged();
                genreList.clear();
                genreList.addAll(StreamSupport.stream(genreResults.getGenres()).map(genre -> genre.getName()).collect(Collectors.toList()));
                ((MainHomeActivity) getActivity()).updateTag(binding.viewPager, genreList, -1);
            }
        });
    }
}
