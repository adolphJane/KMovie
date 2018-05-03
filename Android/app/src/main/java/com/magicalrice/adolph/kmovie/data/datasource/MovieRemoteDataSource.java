package com.magicalrice.adolph.kmovie.data.datasource;

import com.magicalrice.adolph.kmovie.data.entities.DiscoverFilter;
import com.magicalrice.adolph.kmovie.data.entities.GenreResults;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.data.enumerations.SortBy;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MovieRemoteDataSource {
    Tmdb tmdb;

    @Inject
    public MovieRemoteDataSource(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    public Observable<MovieResultsPage> getPopularMovie(int page) {
        return tmdb.moviesService().popular(page, "zh")
                .compose(RxUtils.io_main());
    }

    public Observable<GenreResults> getMovieGenre() {
        return tmdb.genreService().movie("zh")
                .compose(RxUtils.io_main());
    }

    public Observable<GenreResults> getTvGenre() {
        return tmdb.genreService().tv("zh")
                .compose(RxUtils.io_main());
    }

    public Observable<MovieResultsPage> getMoviesByGenre(DiscoverFilter genre, int page) {
        return tmdb.discoverService().discoverMovieWithGenre("zh",SortBy.VOTE_AVERAGE_ASC,true,true,page,genre)
                .compose(RxUtils.io_main());
    }

    public Observable<TvShowResultsPage> getTvsByGenre(DiscoverFilter genre, int page) {
        return tmdb.discoverService().discoverTvWithGenre("zh", SortBy.VOTE_AVERAGE_ASC, page,genre)
                .compose(RxUtils.io_main());
    }
}