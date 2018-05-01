package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.TvRemoteDataSource;

/**
 * Created by Adolph on 2018/4/20.
 */

public class MainViewModuleFactory extends ViewModelProvider.AndroidViewModelFactory {
    private MovieRemoteDataSource movieDataSource;
    private TvRemoteDataSource tvDataSource;
    private Application application;
    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link AndroidViewModel}
     */
    public MainViewModuleFactory(@NonNull Application application, MovieRemoteDataSource movieSource,TvRemoteDataSource tvSource) {
        super(application);
        this.application = application;
        this.movieDataSource = movieSource;
        this.tvDataSource = tvSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModule.class)) {
            return (T) new MovieViewModule(application,movieDataSource);
        } else if (modelClass.isAssignableFrom(TvViewModule.class)) {
            return (T) new TvViewModule(application,tvDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
