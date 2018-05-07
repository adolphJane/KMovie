package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.magicalrice.adolph.kmovie.data.datasource.MovieDetailRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;

/**
 * Created by Adolph on 2018/4/20.
 */

public class MainViewModuleFactory extends ViewModelProvider.AndroidViewModelFactory {
    private MovieRemoteDataSource movieDataSource;
    private MovieDetailRemoteDataSource detailDataSource;
    private Application application;

    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link AndroidViewModel}
     */
    public MainViewModuleFactory(@NonNull Application application, MovieRemoteDataSource movieSource, MovieDetailRemoteDataSource detailDataSource) {
        super(application);
        this.application = application;
        this.movieDataSource = movieSource;
        this.detailDataSource = detailDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainHomeViewModule.class)) {
            return (T) new MainHomeViewModule(application, movieDataSource);
        } else if (modelClass.isAssignableFrom(MovieDetailViewModule.class)) {
            return (T) new MovieDetailViewModule(application, detailDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
