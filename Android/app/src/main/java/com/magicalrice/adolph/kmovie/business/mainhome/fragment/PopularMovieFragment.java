package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseFragment;
import com.magicalrice.adolph.kmovie.databinding.FragmentMainHomeMovieBinding;
import com.magicalrice.adolph.kmovie.viewmodule.MovieViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.ViewModuleFactory;

import javax.inject.Inject;

public class PopularMovieFragment extends BaseFragment<FragmentMainHomeMovieBinding>{
    private MovieViewModule viewModule;
    @Inject
    ViewModuleFactory factory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModule = ViewModelProviders.of(this,factory).get(MovieViewModule.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main_home_movie;
    }

    @Override
    public void createView(View view) {
        viewModule.getPopularMovie();
    }
}
