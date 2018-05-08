package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.gson.JsonSyntaxException;
import com.magicalrice.adolph.kmovie.data.datasource.MovieDetailRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.Credits;
import com.magicalrice.adolph.kmovie.data.entities.Images;
import com.magicalrice.adolph.kmovie.data.entities.Keywords;
import com.magicalrice.adolph.kmovie.data.entities.Movie;
import com.magicalrice.adolph.kmovie.data.entities.MovieDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDatesResults;
import com.magicalrice.adolph.kmovie.utils.LUtils;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Function7;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by Adolph on 2018/5/7.
 */

public class MovieDetailViewModule extends AndroidViewModel {
    private MovieDetailRemoteDataSource dataSource;
    public MutableLiveData<MovieDetailBean> movieDetailBean = new MutableLiveData<>();

    public MovieDetailViewModule(@NonNull Application application, MovieDetailRemoteDataSource dataSource) {
        super(application);
        this.dataSource = dataSource;
    }

    public void getMovieDetail(int movieId) {
        Observable.zip(dataSource.getMovieSummary(movieId),
                dataSource.getMovieCredits(movieId),
                dataSource.getMovieImages(movieId),
                dataSource.getMovieRecommendations(movieId, 1),
                dataSource.getMovieReleaseDate(movieId),
                dataSource.getMovieKeywords(movieId),
                dataSource.getMovieSimilar(movieId, 1),
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
                        return bean;
                    }
                }).subscribe(movieDetailBeanObservable -> {
                    movieDetailBean.setValue(movieDetailBeanObservable);
                }, throwable -> {
                    if (throwable instanceof HttpException) {
                        ResponseBody body = ((HttpException) throwable).response().errorBody();
                        try {
                            LUtils.e(body.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (throwable instanceof IllegalStateException || throwable instanceof JsonSyntaxException) {
                        throwable.printStackTrace();
                        LUtils.e("ErrorMessage:%s,ErrorCause%s", throwable.getMessage(), throwable.getCause().toString());
                    }
                }
        );
    }
}
