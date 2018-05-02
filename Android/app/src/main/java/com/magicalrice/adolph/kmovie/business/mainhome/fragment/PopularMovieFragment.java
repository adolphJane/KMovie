package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseFragment;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.databinding.FragmentMainHomeMovieBinding;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.viewmodule.MovieViewModule;
import com.magicalrice.adolph.kmovie.widget.adapter.MainMovieAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PopularMovieFragment extends BaseFragment<FragmentMainHomeMovieBinding>{
    private MovieViewModule viewModule;
    private MainMovieAdapter adapter;
    private List<BaseMovie> movieList;
    @Inject
    MainViewModuleFactory factory;

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
        if (movieList == null) {
            movieList = new ArrayList<>();
            adapter = new MainMovieAdapter(getContext(),movieList);
            binding.setAdapter(adapter);
        }
        viewModule.getPopularMovie(1);
        showMovie();
    }

    public void showMovie() {
        viewModule.movieList.observe(this,baseMovies -> {
                movieList.addAll(baseMovies);
                adapter.notifyItemRangeInserted(movieList.size() - baseMovies.size(),baseMovies.size());
        });
    }
}
