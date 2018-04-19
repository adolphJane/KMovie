package com.magicalrice.adolph.kmovie.data.datasource;

import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MovieRemoteDataSource {
    Tmdb tmdb;

    @Inject
    public MovieRemoteDataSource(Tmdb tmdb){
        this.tmdb = tmdb;
    }

    public Observable<MovieResultsPage> getPopularMovie() {
        return tmdb.moviesService().popular(1,"zh")
                .compose(RxUtils.io_main());
    }
}