package com.magicalrice.adolph.kmovie.data.repository;

import android.content.Context;

import com.magicalrice.adolph.kmovie.data.datasource.MovieLocalDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.GenreResults;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import java.util.List;
import java.util.NoSuchElementException;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by Adolph on 2018/6/11.
 */

public class MovieRepository {
    private Context context;
    private MovieRemoteDataSource remoteDataSource;
    private MovieLocalDataSource localDataSource;

    @Inject
    public MovieRepository(MovieRemoteDataSource remoteDataSource, MovieLocalDataSource localDataSource, Context context) {
        this.context = context;
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public Observable<MovieResultsPage> getPopularMovie(int page) {
        return remoteDataSource.getPopularMovie(page);
    }

    public Observable<TvShowResultsPage> getPopularTv(int page) {
        return remoteDataSource.getPopularTv(page);
    }

    public Observable<GenreResults> getMovieGenre() {
        return remoteDataSource.getMovieGenre();
    }

    public Observable<GenreResults> getTvGenre() {
        return remoteDataSource.getTvGenre();
    }

    public Flowable<List<BaseVideo>> getVideosByGenre(int genre,int page,int type) {
        if (type == 1) {
            return Flowable.concat(localDataSource.getMoviesByGenre(genre, page),remoteDataSource.getMoviesByGenre(genre, page))
                    .switchIfEmpty(s -> {
                        s.onError(new NoSuchElementException());
                    }).compose(RxUtils.io_main_f());
        } else {
            return Flowable.concat(localDataSource.getTvsByGenre(genre, page),remoteDataSource.getTvsByGenre(genre, page))
                    .switchIfEmpty(s -> {
                        s.onError(new NoSuchElementException());
                    }).compose(RxUtils.io_main_f());
        }
    }

    public void clearCache() {
        localDataSource.clearCache();
    }
}
