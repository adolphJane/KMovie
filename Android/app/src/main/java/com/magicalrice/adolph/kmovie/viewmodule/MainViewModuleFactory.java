package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.magicalrice.adolph.kmovie.data.datasource.MovieDetailRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.SearchRemoteDataSource;

/**
 * Created by Adolph on 2018/4/20.
 */

public class MainViewModuleFactory extends ViewModelProvider.AndroidViewModelFactory {
    private MovieRemoteDataSource movieDataSource;
    private MovieDetailRemoteDataSource detailDataSource;
    private SearchRemoteDataSource searchDataSource;
    private Application application;

    public MainViewModuleFactory(@NonNull Application application, MovieRemoteDataSource movieSource, MovieDetailRemoteDataSource detailDataSource,SearchRemoteDataSource searchDataSource) {
        super(application);
        this.application = application;
        this.movieDataSource = movieSource;
        this.detailDataSource = detailDataSource;
        this.searchDataSource = searchDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainHomeViewModule.class)) {
            return (T) new MainHomeViewModule(application, movieDataSource);
        } else if (modelClass.isAssignableFrom(MovieDetailViewModule.class)) {
            return (T) new MovieDetailViewModule(application, detailDataSource);
        } else if (modelClass.isAssignableFrom(SearchViewModule.class)) {
            return (T) new SearchViewModule(application,searchDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
