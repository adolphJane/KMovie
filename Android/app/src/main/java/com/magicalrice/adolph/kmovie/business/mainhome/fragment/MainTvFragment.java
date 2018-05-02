package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseFragment;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;
import com.magicalrice.adolph.kmovie.databinding.FragmentMainHomeTvBinding;
import com.magicalrice.adolph.kmovie.viewmodule.MainTvViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.viewmodule.TvViewModule;
import com.magicalrice.adolph.kmovie.widget.adapter.MainTvAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/5/2.
 */

public class MainTvFragment extends BaseFragment<FragmentMainHomeTvBinding> {
    private MainTvViewModule viewModule;
    private MainTvAdapter adapter;
    private List<BaseTvShow> tvList;
    @Inject
    MainViewModuleFactory factory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModule = ViewModelProviders.of(this,factory).get(MainTvViewModule.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main_home_tv;
    }

    @Override
    public void createView(View view) {
        if (tvList == null) {
            tvList = new ArrayList<>();
            adapter = new MainTvAdapter(getContext(),tvList);
            binding.setAdapter(adapter);
        }
    }
}
