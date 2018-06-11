package com.magicalrice.adolph.kmovie.data.local.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;

import java.util.List;

/**
 * Created by Adolph on 2018/6/11.
 */

@Dao
public interface TvListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTvList(List<BaseVideo> videos);

    @Query("SELECT * FROM basevideo WHERE page = :page AND genre = :genre AND type = :type")
    public LiveData<List<BaseVideo>> loadTvs(int genre, int page,int type);
}
