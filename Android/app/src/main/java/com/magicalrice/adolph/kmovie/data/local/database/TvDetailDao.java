package com.magicalrice.adolph.kmovie.data.local.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.magicalrice.adolph.kmovie.data.entities.TvShowDetailBean;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface TvDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvDetail(TvShowDetailBean tvShowDetailBean);

    @Query("SELECT * FROM tvshow_detail WHERE tvId = :tvId")
    Single<TvShowDetailBean> loadTvDetail(long tvId);

    @Query("SELECT * FROM tvshow_detail WHERE isLike = :isLike")
    LiveData<List<TvShowDetailBean>> loadLoveTv(boolean isLike);

    @Update
    void updateTvDetail(TvShowDetailBean tvShowDetailBean);
}
