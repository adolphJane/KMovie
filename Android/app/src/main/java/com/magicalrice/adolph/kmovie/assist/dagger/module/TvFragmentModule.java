package com.magicalrice.adolph.kmovie.assist.dagger.module;

import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.TvRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Adolph on 2018/4/20.
 */

@Module
public class TvFragmentModule {
    @Provides
    public TvRemoteDataSource provideTvData(Tmdb tmdb) {
        return new TvRemoteDataSource(tmdb);
    }
}
