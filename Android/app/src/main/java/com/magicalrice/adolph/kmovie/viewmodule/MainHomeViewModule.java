package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.GenreResults;

public class MainHomeViewModule extends AndroidViewModel{
    private MovieRemoteDataSource dataSource;
    private Application context;
    public MutableLiveData<GenreResults> genreData = new MutableLiveData<>();
    public MainHomeViewModule(@NonNull Application application,MovieRemoteDataSource dataSource) {
        super(application);
        this.context = application;
        this.dataSource = dataSource;
    }

    public void getMovieGenre() {
        dataSource.getMovieGenre().subscribe(genreResults -> {
             genreData.setValue(genreResults);
        },throwable -> Toast.makeText(context,throwable.getMessage() + "error",Toast.LENGTH_SHORT).show());
    }
}
