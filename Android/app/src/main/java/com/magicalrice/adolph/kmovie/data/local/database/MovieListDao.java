package com.magicalrice.adolph.kmovie.data.local.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.VideoResultsPage;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by Adolph on 2018/6/11.
 */

@Dao
public interface MovieListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMovieList(List<BaseVideo> videos);

    @Query("SELECT * FROM basevideo WHERE page = :page AND genre = :genre AND type = :type")
    public Flowable<List<BaseVideo>> loadMovies(int genre, int page, int type);
}
