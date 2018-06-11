package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.magicalrice.adolph.kmovie.data.datasource.MovieDetailRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.RoleRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.SearchRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.repository.MovieRepository;

/**
 * Created by Adolph on 2018/4/20.
 */

public class MainViewModuleFactory extends ViewModelProvider.AndroidViewModelFactory {
    private MovieRepository repository;
    private MovieDetailRemoteDataSource detailDataSource;
    private SearchRemoteDataSource searchDataSource;
    private RoleRemoteDataSource roleDataSource;
    private Application application;

    public MainViewModuleFactory(@NonNull Application application, MovieRepository repository, MovieDetailRemoteDataSource detailDataSource, SearchRemoteDataSource searchDataSource, RoleRemoteDataSource roleDataSource) {
        super(application);
        this.application = application;
        this.repository = repository;
        this.detailDataSource = detailDataSource;
        this.searchDataSource = searchDataSource;
        this.roleDataSource = roleDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainHomeViewModule.class)) {
            return (T) new MainHomeViewModule(application, repository);
        } else if (modelClass.isAssignableFrom(MovieDetailViewModule.class)) {
            return (T) new MovieDetailViewModule(application, detailDataSource);
        } else if (modelClass.isAssignableFrom(SearchViewModule.class)) {
            return (T) new SearchViewModule(application,searchDataSource);
        } else if (modelClass.isAssignableFrom(RoleViewModule.class)) {
            return (T) new RoleViewModule(application,roleDataSource);
        } else if (modelClass.isAssignableFrom(MainMeViewModule.class)) {
            return (T) new MainMeViewModule(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
