package com.magicalrice.adolph.kmovie.data.local.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.magicalrice.adolph.kmovie.data.entities.MovieDetailBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface MovieDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovieDetail(MovieDetailBean movieDetailBean);

    @Query("SELECT * FROM movie_detail WHERE movieDetailId = :movieId")
    Single<MovieDetailBean> loadMovieDetail(long movieId);

    @Query("SELECT * FROM movie_detail WHERE isLike = :isLike")
    LiveData<List<MovieDetailBean>> loadLoveMovie(boolean isLike);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovieDetail(MovieDetailBean movieDetailBean);
}
