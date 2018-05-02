package com.magicalrice.adolph.kmovie.data.datasource;

import com.magicalrice.adolph.kmovie.data.entities.GenreResults;
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

    public Observable<MovieResultsPage> getPopularMovie(int page) {
        return tmdb.moviesService().popular(page,"zh")
                .compose(RxUtils.io_main());
    }

    public Observable<GenreResults> getMovieGenre() {
        return tmdb.genreService().movie("zh")
                .compose(RxUtils.io_main());
    }
}