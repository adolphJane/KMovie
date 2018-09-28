package com.magicalrice.adolph.kmovie.data.local.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.magicalrice.adolph.kmovie.data.entities.TvShowDetailBean;

import java.util.List;

import io.reactivex.Flowable;
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
