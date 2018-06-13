package com.magicalrice.adolph.kmovie.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.magicalrice.adolph.kmovie.data.datasource.MovieLocalDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.GenreResults;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.data.local.database.MovieDatabase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Adolph on 2018/6/11.
 */

public class MovieRepository {
    private Context context;
    private MovieDatabase database;
    private MovieRemoteDataSource remoteDataSource;
    private MovieLocalDataSource localDataSource;

    @Inject
    public MovieRepository(MovieRemoteDataSource remoteDataSource, MovieLocalDataSource localDataSource, MovieDatabase database, Context context) {
        this.database = database;
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

    public Observable<List<BaseVideo>> getMoviesByGenre(int genre, int page) {
        return localDataSource.getMoviesByGenre(genre, page)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(videos -> {
                    if (videos == null || videos.size() == 0) {
                        return remoteDataSource.getMoviesByGenre(genre, page);
                    }
                    return Observable.just(videos);
                });
    }

    public LiveData<List<BaseVideo>> getTvsByGenre(int genre, int page) {
        LiveData<List<BaseVideo>> base = localDataSource.getTvsByGenre(genre, page);
        if (base.getValue() == null) {
            remoteDataSource.getTvsByGenre(genre, page);
        }
        return base;
    }
}
