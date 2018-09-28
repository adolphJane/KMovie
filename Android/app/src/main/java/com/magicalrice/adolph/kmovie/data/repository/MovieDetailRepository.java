package com.magicalrice.adolph.kmovie.data.repository;

import android.content.Context;

import com.magicalrice.adolph.kmovie.data.datasource.MovieDetailLocalDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieDetailRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieLocalDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.MovieDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.TvShowDetailBean;
import com.magicalrice.adolph.kmovie.data.local.database.MovieDatabase;
import com.magicalrice.adolph.kmovie.utils.LUtils;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import java.util.List;
import java.util.NoSuchElementException;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailRepository {
    private Context context;
    private MovieDetailRemoteDataSource remoteDataSource;
    private MovieDetailLocalDataSource localDataSource;

    @Inject
    public MovieDetailRepository(MovieDetailRemoteDataSource remoteDataSource, MovieDetailLocalDataSource localDataSource, Context context) {
        this.context = context;
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public Flowable<MovieDetailBean> getMovieDetail(long movieId) {
        return Flowable.concat(localDataSource.getMovieBean(movieId),remoteDataSource.getMovieDetail(movieId))
                .switchIfEmpty(s -> {
                    s.onError(new NoSuchElementException());
                }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .doOnNext(movieDetailBean -> {
                    movieDetailBean.setMovieDetailId(movieId);
                    localDataSource.insertMovie(movieDetailBean);
                }).observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<TvShowDetailBean> getTvDetail(long tvId) {
        return Flowable.concat(localDataSource.getTvBean(tvId), remoteDataSource.getTvDetail(tvId))
                .switchIfEmpty(s -> {
                    s.onError(new NoSuchElementException());
                }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .doOnNext(tvShowDetailBean -> {
                    tvShowDetailBean.setTvId(tvId);
                    localDataSource.insertTv(tvShowDetailBean);
                }).observeOn(AndroidSchedulers.mainThread());
    }

    public void likeMovie(MovieDetailBean bean) {
        localDataSource.updateMovie(bean);
    }

    public void likeTv(TvShowDetailBean bean) {
        localDataSource.updateTv(bean);
    }
}
