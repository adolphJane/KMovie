package com.magicalrice.adolph.kmovie.data.datasource;

import android.arch.lifecycle.LiveData;

import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.DiscoverFilter;
import com.magicalrice.adolph.kmovie.data.entities.VideoResultsPage;
import com.magicalrice.adolph.kmovie.data.local.database.MovieDatabase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

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
        return database.movieListDao().loadMovies(genre,page,1);
    }

    public LiveData<List<BaseVideo>> getTvsByGenre(int genre, int page) {
        return database.tvListDao().loadTvs(genre,page,2);
    }
}
