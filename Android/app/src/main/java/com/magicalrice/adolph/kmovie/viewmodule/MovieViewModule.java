package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;

import io.reactivex.Observable;

public class MovieViewModule extends AndroidViewModel{
    private MovieRemoteDataSource dataSource;
    private Application context;

    public MovieViewModule(@NonNull Application application, MovieRemoteDataSource dataSource) {
        super(application);
        this.context = application;
        this.dataSource = dataSource;
    }

    public Observable<MovieResultsPage> getPopularMovie() {
        dataSource.getPopularMovie().subscribe();
    }
}
