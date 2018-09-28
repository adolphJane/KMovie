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
import com.magicalrice.adolph.kmovie.data.entities.TvShow;
import com.magicalrice.adolph.kmovie.data.entities.TvShowDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.data.repository.MovieDetailRepository;
import com.magicalrice.adolph.kmovie.utils.LUtils;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function6;
import io.reactivex.functions.Function7;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by Adolph on 2018/5/7.
 */

public class VideoDetailViewModule extends AndroidViewModel {
    private MovieDetailRepository repository;
    public MutableLiveData<MovieDetailBean> movieDetailLiveData = new MutableLiveData<>();
    public MutableLiveData<TvShowDetailBean> tvDetailLiveData = new MutableLiveData<>();

    public VideoDetailViewModule(@NonNull Application application, MovieDetailRepository repository) {
        super(application);
        this.repository = repository;
    }

    public void getMovieDetail(long movieId) {
        repository.getMovieDetail(movieId).subscribe(movieDetailBean -> {
            movieDetailLiveData.setValue(movieDetailBean);
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
            } else {
                throwable.printStackTrace();
            }
        });
    }

    public void getTVDetail(long tvId) {
        repository.getTvDetail(tvId).subscribe(tvShowDetailBean -> tvDetailLiveData.setValue(tvShowDetailBean), throwable -> {
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
            } else {
                throwable.printStackTrace();
            }
        });
    }

    public void likeMovie(MovieDetailBean bean) {
        repository.likeMovie(bean);
    }

    public void likeTv(TvShowDetailBean bean) {
        repository.likeTv(bean);
    }
}
