package com.magicalrice.adolph.kmovie.assist.dagger.module;

import android.content.Context;

import com.magicalrice.adolph.kmovie.data.datasource.MovieLocalDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.local.database.MovieDatabase;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.data.repository.MovieRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MainHomeModule {
    @Provides
    MovieRemoteDataSource provideMovieRemoteDataSource(Context context, Tmdb tmdb, MovieDatabase database) {
        return new MovieRemoteDataSource(context,tmdb,database);
    }

    @Provides
    MovieLocalDataSource provideMovieLocalDataSource(MovieDatabase database) {
        return new MovieLocalDataSource(database);
    }

    @Provides
    MovieRepository provideMovieRepository(MovieRemoteDataSource remoteDataSource,MovieLocalDataSource localDataSource,Context context) {
        return new MovieRepository(remoteDataSource,localDataSource,context);
    }
}