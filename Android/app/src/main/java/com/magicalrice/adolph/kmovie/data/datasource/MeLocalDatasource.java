package com.magicalrice.adolph.kmovie.data.datasource;

import android.arch.lifecycle.LiveData;

import com.magicalrice.adolph.kmovie.data.entities.MovieDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.RoleDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.TvShowDetailBean;
import com.magicalrice.adolph.kmovie.data.local.database.MovieDatabase;

import java.util.List;

import javax.inject.Inject;

public class MeLocalDatasource {
    private MovieDatabase database;

    @Inject
    public MeLocalDatasource(MovieDatabase database) {
        this.database = database;
    }

    public LiveData<List<MovieDetailBean>> getLoveMovie() {
        return database.movieDetailDao().loadLoveMovie(true);
    }

    public LiveData<List<TvShowDetailBean>> getLoveTv() {
        return database.tvDetailDao().loadLoveTv(true);
    }

    public LiveData<List<RoleDetailBean>> getLoveRole() {
        return database.roleDetailDao().loadLoveRole(true);
    }
}
