package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.magicalrice.adolph.kmovie.data.datasource.MeLocalDatasource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieDetailRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.RoleRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.SearchRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.repository.MovieDetailRepository;
import com.magicalrice.adolph.kmovie.data.repository.MovieRepository;
import com.magicalrice.adolph.kmovie.data.repository.RoleRepository;

/**
 * Created by Adolph on 2018/4/20.
 */

public class MainViewModuleFactory extends ViewModelProvider.AndroidViewModelFactory {
    private MovieRepository movieRepository;
    private MovieDetailRepository detailRepository;
    private SearchRemoteDataSource searchDataSource;
    private RoleRepository roleRepository;
    private MeLocalDatasource meLocalDatasource;
    private Application application;

    public MainViewModuleFactory(@NonNull Application application, MovieRepository movieRepository,MovieDetailRepository detailRepository, SearchRemoteDataSource searchDataSource, RoleRepository roleRepository,MeLocalDatasource meLocalDatasource) {
        super(application);
        this.application = application;
        this.movieRepository = movieRepository;
        this.detailRepository = detailRepository;
        this.searchDataSource = searchDataSource;
        this.roleRepository = roleRepository;
        this.meLocalDatasource = meLocalDatasource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainHomeViewModule.class)) {
            return (T) new MainHomeViewModule(application, movieRepository);
        } else if (modelClass.isAssignableFrom(VideoDetailViewModule.class)) {
            return (T) new VideoDetailViewModule(application, detailRepository);
        } else if (modelClass.isAssignableFrom(SearchViewModule.class)) {
            return (T) new SearchViewModule(application,searchDataSource);
        } else if (modelClass.isAssignableFrom(RoleViewModule.class)) {
            return (T) new RoleViewModule(application,roleRepository);
        } else if (modelClass.isAssignableFrom(MainMeViewModule.class)) {
            return (T) new MainMeViewModule(application,meLocalDatasource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
