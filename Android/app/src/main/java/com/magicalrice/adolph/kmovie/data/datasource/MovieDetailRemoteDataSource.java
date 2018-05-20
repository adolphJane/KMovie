package com.magicalrice.adolph.kmovie.data.datasource;

import com.magicalrice.adolph.kmovie.data.entities.Credits;
import com.magicalrice.adolph.kmovie.data.entities.Images;
import com.magicalrice.adolph.kmovie.data.entities.Keywords;
import com.magicalrice.adolph.kmovie.data.entities.Movie;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.RatingObject;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDatesResults;
import com.magicalrice.adolph.kmovie.data.entities.Status;
import com.magicalrice.adolph.kmovie.data.entities.TvShow;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.data.enumerations.AuthenticationType;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Adolph on 2018/5/7.
 */

public class MovieDetailRemoteDataSource {
    Tmdb tmdb;

    @Inject
    public MovieDetailRemoteDataSource(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    public Observable<Movie> getMovieSummary(int movieId) {
        return tmdb.moviesService().summary(movieId,"zh",null).compose(RxUtils.io_main());
    }

    public Observable<TvShow> getTvSummary(int tvId) {
        return tmdb.tvService().tv(tvId,"zh",null).compose(RxUtils.io_main());
    }

    public Observable<Credits> getMovieCredits(int movieId) {
        return tmdb.moviesService().credits(movieId).compose(RxUtils.io_main());
    }

    public Observable<Credits> getTvCredits(int tvId) {
        return tmdb.tvService().credits(tvId,"zh").compose(RxUtils.io_main());
    }

    public Observable<ReleaseDatesResults> getMovieReleaseDate(int movieId) {
        return tmdb.moviesService().releaseDates(movieId).compose(RxUtils.io_main());
    }

    public Observable<Keywords> getMovieKeywords(int movieId) {
        return tmdb.moviesService().keywords(movieId).compose(RxUtils.io_main());
    }

    public Observable<Keywords> getTvKeywords(int tvId) {
        return tmdb.tvService().keywords(tvId).compose(RxUtils.io_main());
    }

    public Observable<Images> getMovieImages(int movieId) {
        return tmdb.moviesService().images(movieId,"zh").compose(RxUtils.io_main());
    }

    public Observable<Images> getTvImages(int tvId) {
        return tmdb.tvService().images(tvId,"zh").compose(RxUtils.io_main());
    }

    public Observable<MovieResultsPage> getMovieSimilar(int movieId,int page) {
        return tmdb.moviesService().similar(movieId,page,"zh").compose(RxUtils.io_main());
    }

    public Observable<TvShowResultsPage> getTvSimilar(int tvId,int page) {
        return tmdb.tvService().similar(tvId,page,"zh").compose(RxUtils.io_main());
    }

    public Observable<MovieResultsPage> getMovieRecommendations(int movieId,int page) {
        return tmdb.moviesService().recommendations(movieId,page,"zh").compose(RxUtils.io_main());
    }

    public Observable<TvShowResultsPage> getTvRecommendations(int tvId,int page) {
        return tmdb.tvService().recommendations(tvId,page,"zh").compose(RxUtils.io_main());
    }

    public Observable<Status> postMovieAddRating(int movieId,double rating) {
        RatingObject object = new RatingObject();
        object.setValue(rating);
        return tmdb.moviesService().addRating(movieId, AuthenticationType.ACCOUNT,object).compose(RxUtils.io_main());
    }

    public Observable<Status> deleteMovieRating(int movieId) {
        return tmdb.moviesService().deleteRating(movieId, AuthenticationType.ACCOUNT).compose(RxUtils.io_main());
    }

    public Observable<Status> postTvAddRating(int tvId,double rating) {
        RatingObject object = new RatingObject();
        object.setValue(rating);
        return tmdb.tvService().addRating(tvId, AuthenticationType.ACCOUNT,object).compose(RxUtils.io_main());
    }

    public Observable<Status> deleteTvRating(int tvId) {
        return tmdb.tvService().deleteRating(tvId, AuthenticationType.ACCOUNT).compose(RxUtils.io_main());
    }
}
