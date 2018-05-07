package com.magicalrice.adolph.kmovie.assist.dagger.module;

import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;

import dagger.Module;
import dagger.Provides;

@Module
public class MainHomeModule {
    @Provides
    MovieRemoteDataSource provideMovieRemoteDataSource(Tmdb tmdb) {
        return new MovieRemoteDataSource(tmdb);
    }
}