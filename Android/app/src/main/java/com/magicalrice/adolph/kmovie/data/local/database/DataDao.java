package com.magicalrice.adolph.kmovie.data.local.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.magicalrice.adolph.kmovie.data.Movie;

import java.util.List;

/**
 * Created by Adolph on 2018/3/12.
 */
@Dao
public interface DataDao {
    @Query("SELECT * FROM movies")
    List<Movie> getMovies();
}
