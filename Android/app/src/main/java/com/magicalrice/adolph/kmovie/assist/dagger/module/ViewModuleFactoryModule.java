package com.magicalrice.adolph.kmovie.assist.dagger.module;

import android.app.Application;
import android.content.Context;

import com.magicalrice.adolph.kmovie.data.datasource.LoginRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.MeLocalDatasource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieDetailRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.RoleRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.SearchRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.repository.MovieDetailRepository;
import com.magicalrice.adolph.kmovie.data.repository.MovieRepository;
import com.magicalrice.adolph.kmovie.data.repository.RoleRepository;
import com.magicalrice.adolph.kmovie.viewmodule.LoginViewModuleFactory;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Adolph on 2018/4/20.
 */
@Module
public class ViewModuleFactoryModule {
    @Provides
    @Singleton
    public LoginViewModuleFactory provideLoginFactory(Context context, LoginRemoteDataSource loginSource) {
        LoginViewModuleFactory factory = new LoginViewModuleFactory((Application) context, loginSource);
        return factory;
    }

    @Provides
    @Singleton
    public MainViewModuleFactory provideMainFactory(Context context, MovieRepository movieRepository, MovieDetailRepository detailRepository, SearchRemoteDataSource searchSource, RoleRepository roleRepository, MeLocalDatasource meLocalDatasource) {
        MainViewModuleFactory factory = new MainViewModuleFactory((Application) context, movieRepository, detailRepository, searchSource,roleRepository,meLocalDatasource);
        return factory;
    }
}
