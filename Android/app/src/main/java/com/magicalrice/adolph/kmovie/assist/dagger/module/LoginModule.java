package com.magicalrice.adolph.kmovie.assist.dagger.module;

import com.magicalrice.adolph.kmovie.data.datasource.LoginRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Adolph on 2018/4/3.
 */

@Module
public class LoginModule {

    @Provides
    public LoginRemoteDataSource provideLoginData(Tmdb tmdb) {
        return new LoginRemoteDataSource(tmdb);
    }
}
