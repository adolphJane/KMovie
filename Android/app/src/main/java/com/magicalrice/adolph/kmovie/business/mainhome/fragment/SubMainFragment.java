package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseFragment;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;
import com.magicalrice.adolph.kmovie.data.entities.Genre;
import com.magicalrice.adolph.kmovie.databinding.FragmentSubMainHomeBinding;
import com.magicalrice.adolph.kmovie.utils.LUtils;
import com.magicalrice.adolph.kmovie.viewmodule.MainHomeViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.widget.adapter.MainMovieAdapter;
import com.magicalrice.adolph.kmovie.widget.adapter.MainTvAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/5/2.
 */

public class SubMainFragment extends BaseFragment<FragmentSubMainHomeBinding> {
    private MainHomeViewModule viewModule;
    private int totalPage = 0;
    private ArrayList<BaseTvShow> tvList;
    private ArrayList<BaseMovie> movieList;
    private MainMovieAdapter movieAdapter;
    private MainTvAdapter tvAdapter;
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
            if (type == 1) {
                movieList = new ArrayList<>();
                movieAdapter = new MainMovieAdapter(getActivity(), movieList);
                if (savedInstanceState == null) {
                    viewModule.getMovies(genre.getId(), page);
                    viewModule.movieData.observe(this, movieResultsPage -> {
                        movieList.addAll(movieResultsPage.getResults());
                        movieAdapter.notifyItemRangeInserted(movieList.size() - movieResultsPage.getResults().size(), movieResultsPage.getResults().size());
                    });
                }
            } else if (type == 2) {
                tvList = new ArrayList<>();
                tvAdapter = new MainTvAdapter(getActivity(), tvList);
                viewModule.getTvs(genre.getId(), page);
                viewModule.tvData.observe(this, tvShowResultsPage -> {
                    tvList.addAll(tvShowResultsPage.getResults());
                    tvAdapter.notifyItemRangeInserted(tvList.size() - tvShowResultsPage.getResults().size(), tvShowResultsPage.getResults().size());
                });
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (movieList != null && movieList.size() > 0) {
            outState.putParcelableArrayList("movieList", movieList);
        }
        if (tvList != null && tvList.size() > 0) {
            outState.putParcelableArrayList("tvList", tvList);
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_sub_main_home;
    }

    @Override
    public void createView(View view) {
        if (type == 1 && movieAdapter != null) {
            binding.setAdapter(movieAdapter);
        } else if (type == 2 && tvAdapter != null) {
            binding.setAdapter(tvAdapter);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            if (type == 1) {
                List<BaseMovie> temp = savedInstanceState.getParcelableArrayList("movieList");
                if (temp != null && temp.size() > 0) {
                    movieList.addAll(temp);
                    movieAdapter.notifyDataSetChanged();
                }
            } else if (type == 2) {
                List<BaseTvShow> temp = savedInstanceState.getParcelableArrayList("tvList");
                if (temp != null && temp.size() > 0) {
                    tvList.addAll(temp);
                    tvAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
