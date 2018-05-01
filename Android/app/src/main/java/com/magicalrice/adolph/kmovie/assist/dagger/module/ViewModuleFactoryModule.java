package com.magicalrice.adolph.kmovie.assist.dagger.module;

import android.app.Application;
import android.content.Context;

import com.magicalrice.adolph.kmovie.data.datasource.LoginRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.TvRemoteDataSource;
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
        LoginViewModuleFactory factory = new LoginViewModuleFactory((Application) context,loginSource);
        return factory;
    }

    @Provides
    @Singleton
    public MainViewModuleFactory provideMainFactory(Context context, MovieRemoteDataSource movieSource, TvRemoteDataSource tvSource) {
        MainViewModuleFactory factory = new MainViewModuleFactory((Application) context,movieSource,tvSource);
        return factory;
    }
}
