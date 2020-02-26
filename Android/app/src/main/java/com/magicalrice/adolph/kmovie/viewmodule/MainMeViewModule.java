package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.magicalrice.adolph.kmovie.data.datasource.MeLocalDatasource;
import com.magicalrice.adolph.kmovie.data.entities.MovieDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.RoleDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.TvShowDetailBean;
import com.magicalrice.adolph.kmovie.data.repository.MovieRepository;

import java.util.List;


/**
 * Created by Adolph on 2018/5/24.
 */

public class MainMeViewModule extends AndroidViewModel {
    private MeLocalDatasource datasource;

    public MainMeViewModule(@NonNull Application application, MeLocalDatasource datasource) {
        super(application);
        this.datasource = datasource;
    }

    public LiveData<List<MovieDetailBean>> getLoveMovie() {
        return datasource.getLoveMovie();
    }

    public LiveData<List<TvShowDetailBean>> getLoveTv() {
        return datasource.getLoveTv();
    }

    public LiveData<List<RoleDetailBean>> getLoveRole() {
        return datasource.getLoveRole();
    }
}
