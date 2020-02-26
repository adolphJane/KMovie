package com.magicalrice.adolph.kmovie.data.datasource;

import com.magicalrice.adolph.kmovie.data.entities.Credits;
import com.magicalrice.adolph.kmovie.data.entities.Images;
import com.magicalrice.adolph.kmovie.data.entities.Keywords;
import com.magicalrice.adolph.kmovie.data.entities.Movie;
import com.magicalrice.adolph.kmovie.data.entities.MovieDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.RatingObject;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDatesResults;
import com.magicalrice.adolph.kmovie.data.entities.Status;
import com.magicalrice.adolph.kmovie.data.entities.TvShow;
import com.magicalrice.adolph.kmovie.data.entities.TvShowDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.data.enumerations.AuthenticationType;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function6;
import io.reactivex.functions.Function7;

/**
 * Created by Adolph on 2018/5/7.
 */

public class MovieDetailRemoteDataSource {
    Tmdb tmdb;

    @Inject
    public MovieDetailRemoteDataSource(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    public Flowable<Movie> getMovieSummary(long movieId) {
        return tmdb.moviesService().summary(movieId, "zh", null).compose(RxUtils.io_main_f());
    }

    public Flowable<TvShow> getTvSummary(long tvId) {
        return tmdb.tvService().tv(tvId, "zh", null).compose(RxUtils.io_main_f());
    }

    public Flowable<Credits> getMovieCredits(long movieId) {
        return tmdb.moviesService().credits(movieId).compose(RxUtils.io_main_f());
    }

    public Flowable<Credits> getTvCredits(long tvId) {
        return tmdb.tvService().credits(tvId, "zh").compose(RxUtils.io_main_f());
    }

    public Flowable<ReleaseDatesResults> getMovieReleaseDate(long movieId) {
        return tmdb.moviesService().releaseDates(movieId).compose(RxUtils.io_main_f());
    }

    public Flowable<Keywords> getMovieKeywords(long movieId) {
        return tmdb.moviesService().keywords(movieId).compose(RxUtils.io_main_f());
    }

    public Flowable<Keywords> getTvKeywords(long tvId) {
        return tmdb.tvService().keywords(tvId).compose(RxUtils.io_main_f());
    }

    public Flowable<Images> getMovieImages(long movieId) {
        return tmdb.moviesService().images(movieId, "zh").compose(RxUtils.io_main_f());
    }

    public Flowable<Images> getTvImages(long tvId) {
        return tmdb.tvService().images(tvId, "zh").compose(RxUtils.io_main_f());
    }

    public Flowable<MovieResultsPage> getMovieSimilar(long movieId, int page) {
        return tmdb.moviesService().similar(movieId, page, "zh").compose(RxUtils.io_main_f());
    }

    public Flowable<TvShowResultsPage> getTvSimilar(long tvId, int page) {
        return tmdb.tvService().similar(tvId, page, "zh").compose(RxUtils.io_main_f());
    }

    public Flowable<MovieResultsPage> getMovieRecommendations(long movieId, int page) {
        return tmdb.moviesService().recommendations(movieId, page, "zh").compose(RxUtils.io_main_f());
    }

    public Flowable<TvShowResultsPage> getTvRecommendations(long tvId, int page) {
        return tmdb.tvService().recommendations(tvId, page, "zh").compose(RxUtils.io_main_f());
    }

    public Observable<Status> postMovieAddRating(int movieId, double rating) {
        RatingObject object = new RatingObject();
        object.setValue(rating);
        return tmdb.moviesService().addRating(movieId, AuthenticationType.ACCOUNT, object).compose(RxUtils.io_main_o());
    }

    public Observable<Status> deleteMovieRating(int movieId) {
        return tmdb.moviesService().deleteRating(movieId, AuthenticationType.ACCOUNT).compose(RxUtils.io_main_o());
    }

    public Observable<Status> postTvAddRating(int tvId, double rating) {
        RatingObject object = new RatingObject();
        object.setValue(rating);
        return tmdb.tvService().addRating(tvId, AuthenticationType.ACCOUNT, object).compose(RxUtils.io_main_o());
    }

    public Observable<Status> deleteTvRating(int tvId) {
        return tmdb.tvService().deleteRating(tvId, AuthenticationType.ACCOUNT).compose(RxUtils.io_main_o());
    }

    public Flowable<MovieDetailBean> getMovieDetail(long movieId) {
        return Flowable.zip(getMovieSummary(movieId),
                getMovieCredits(movieId),
                getMovieImages(movieId),
                getMovieRecommendations(movieId, 1),
                getMovieReleaseDate(movieId),
                getMovieKeywords(movieId),
                getMovieSimilar(movieId, 1),
                new Function7<Movie, Credits, Images, MovieResultsPage, ReleaseDatesResults, Keywords, MovieResultsPage, MovieDetailBean>() {
                    @Override
                    public MovieDetailBean apply(Movie movie, Credits credits, Images images, MovieResultsPage movieResultsPage, ReleaseDatesResults releaseDatesResults, Keywords keywords, MovieResultsPage movieResultsPage2) throws Exception {
                        MovieDetailBean bean = new MovieDetailBean();
                        bean.setCredits(credits);
                        bean.setDatesResults(releaseDatesResults);
                        bean.setImages(images);
                        bean.setKeywords(keywords);
                        bean.setMovie(movie);
                        bean.setRecommendationResult(movieResultsPage);
                        bean.setSimilarResult(movieResultsPage2);
                        bean.setSourceType(2);
                        return bean;
                    }
                });
    }

    public Flowable<TvShowDetailBean> getTvDetail(long tvId) {
        return Flowable.zip(getTvSummary(tvId),
                getTvCredits(tvId),
                getTvKeywords(tvId),
                getTvRecommendations(tvId, 1),
                getTvSimilar(tvId, 1),
                getTvImages(tvId),
                new Function6<TvShow, Credits, Keywords, TvShowResultsPage, TvShowResultsPage, Images, TvShowDetailBean>() {

                    @Override
                    public TvShowDetailBean apply(TvShow tvShow, Credits credits, Keywords keywords, TvShowResultsPage tvShowResultsPage, TvShowResultsPage tvShowResultsPage2, Images images) throws Exception {
                        TvShowDetailBean bean = new TvShowDetailBean();
                        bean.setTvShow(tvShow);
                        bean.setCredits(credits);
                        bean.setKeywords(keywords);
                        bean.setTvRecommendation(tvShowResultsPage);
                        bean.setTvSimilar(tvShowResultsPage2);
                        bean.setImages(images);
                        return bean;
                    }
                });

//                .subscribe(tvShowDetailBean -> {
//                }, throwable -> {
//                    if (throwable instanceof HttpException) {
//                        ResponseBody body = ((HttpException) throwable).response().errorBody();
//                        try {
//                            LUtils.e(body.toString());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    } else if (throwable instanceof IllegalStateException || throwable instanceof JsonSyntaxException) {
//                        throwable.printStackTrace();
//                        LUtils.e("ErrorMessage:%s,ErrorCause%s", throwable.getMessage(), throwable.getCause().toString());
//                    }
//                }
//        );
    }
}
