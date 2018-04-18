package com.magicalrice.adolph.kmovie.data.datasource;

import com.magicalrice.adolph.kmovie.data.remote.Tmdb;

import javax.inject.Inject;

public class MovieRemoteDataSource {
    @Inject
    Tmdb tmdb;

    @Inject
    public MovieRemoteDataSource(){}

    public void getPopularMovie() {
        tmdb.moviesService().popular()
    }
}
