package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class MovieViewModule extends AndroidViewModel{
    private MovieRemoteDataSource dataSource;
    private Application context;
    public MutableLiveData<Integer> movieTotal = new MutableLiveData<>();
    public MutableLiveData<Integer> moviePageTotal = new MutableLiveData<>();
    public MutableLiveData<List<BaseMovie>> movieList = new MutableLiveData<>();

    public MovieViewModule(@NonNull Application application, MovieRemoteDataSource dataSource) {
        super(application);
        this.context = application;
        this.dataSource = dataSource;
    }

    public void getPopularMovie(int page) {
        dataSource.getPopularMovie(page).subscribe(movieResultsPage -> {
            movieTotal.setValue(movieResultsPage.getTotal_results());
            moviePageTotal.setValue(movieResultsPage.getTotal_pages());
            movieList.setValue(movieResultsPage.getResults());
        },throwable -> Toast.makeText(context,throwable.getMessage() + "error",Toast.LENGTH_SHORT).show());
    }
}
