package com.magicalrice.adolph.kmovie.data.datasource;

import android.arch.persistence.room.EmptyResultSetException;

import com.magicalrice.adolph.kmovie.data.entities.MovieDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.TvShowDetailBean;
import com.magicalrice.adolph.kmovie.data.local.database.MovieDatabase;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class MovieDetailLocalDataSource {
    private MovieDatabase database;

    @Inject
    public MovieDetailLocalDataSource(MovieDatabase database) {
        this.database = database;
    }

    public Flowable<MovieDetailBean> getMovieBean(long movieId) {
        return Flowable.create(emitter ->
                database.movieDetailDao().loadMovieDetail(movieId).subscribe(movieDetailBean -> {
                    movieDetailBean.setSourceType(1);
                    emitter.onNext(movieDetailBean);
                }, throwable -> {
                    if (throwable instanceof EmptyResultSetException) {
                        emitter.onComplete();
                    } else {
                        emitter.onError(throwable);
                    }
                }), BackpressureStrategy.BUFFER
        );
    }

    public Flowable<TvShowDetailBean> getTvBean(long tvId) {
        return Flowable.create(emitter ->
                database.tvDetailDao().loadTvDetail(tvId).subscribe(tvShowDetailBean -> {
                    emitter.onNext(tvShowDetailBean);
                }, throwable -> {
                    if (throwable instanceof EmptyResultSetException) {
                        emitter.onComplete();
                    } else {
                        emitter.onError(throwable);
                    }
                }), BackpressureStrategy.LATEST
        );
    }

    public void updateMovie(MovieDetailBean bean) {
        Observable.create(emitter -> {
            database.movieDetailDao().updateMovieDetail(bean);
            emitter.onComplete();
        }).compose(RxUtils.io_main_o()).subscribe();
    }

    public void updateTv(TvShowDetailBean bean) {
        Observable.create(emitter -> {
            database.tvDetailDao().updateTvDetail(bean);
            emitter.onComplete();
        }).compose(RxUtils.io_main_o()).subscribe();
    }

    public void insertMovie(MovieDetailBean bean) {
        database.movieDetailDao().insertMovieDetail(bean);
    }

    public void insertTv(TvShowDetailBean bean) {
        database.tvDetailDao().insertTvDetail(bean);
    }
}
