package com.magicalrice.adolph.kmovie.assist.dagger.module;

import com.magicalrice.adolph.kmovie.data.datasource.MovieDetailRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Adolph on 2018/5/7.
 */

@Module
public class VideoDetailModule {
    @Provides
    MovieDetailRemoteDataSource provideMovieDetailRemoteDataSource(Tmdb tmdb) {
        return new MovieDetailRemoteDataSource(tmdb);
    }
}
