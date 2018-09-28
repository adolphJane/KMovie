package com.magicalrice.adolph.kmovie.data.datasource;

import android.arch.persistence.room.EmptyResultSetException;

import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.local.database.MovieDatabase;
import com.magicalrice.adolph.kmovie.utils.LUtils;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by Adolph on 2018/6/11.
 */

public class MovieLocalDataSource {
    private MovieDatabase database;

    @Inject
    public MovieLocalDataSource(MovieDatabase database) {
        this.database = database;
    }

    public Flowable<List<BaseVideo>> getMoviesByGenre(int genre, int page) {
        return Flowable.create(emitter ->
                database.videoListDao().loadVideos(genre, page, 1).subscribe(baseVideos -> {
                    if (baseVideos.size() > 0) {
                        emitter.onNext(baseVideos);
                    } else {
                        emitter.onComplete();
                    }
                }, throwable -> {
                    if (throwable instanceof EmptyResultSetException) {
                        emitter.onComplete();
                    } else {
                        emitter.onError(throwable);
                    }
                }), BackpressureStrategy.LATEST
        );
    }

    public Flowable<List<BaseVideo>> getTvsByGenre(int genre, int page) {
        return Flowable.create(emitter ->
                database.videoListDao().loadVideos(genre, page, 2).subscribe(baseVideos -> {
                    if (baseVideos == null) {
                        emitter.onError(new NullPointerException());
                    } else {
                        if (baseVideos.size() > 0) {
                            emitter.onNext(baseVideos);
                        } else {
                            emitter.onComplete();
                        }
                    }
                }), BackpressureStrategy.LATEST
        );
    }

    public void clearCache() {
        Observable.create(emitter ->
                {
                    database.videoListDao().clearMovies();
                    emitter.onComplete();
                }
        ).compose(RxUtils.io_main_o());
    }
}
