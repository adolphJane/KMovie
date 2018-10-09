package com.magicalrice.adolph.kmovie.data.local.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by Adolph on 2018/6/11.
 */

@Dao
public interface VideoListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVideoList(List<BaseVideo> videos);

    @Query("SELECT * FROM basevideo WHERE page = :page AND genre = :genre AND type = :type")
    Single<List<BaseVideo>> loadVideos(int genre, int page, int type);

    @Query("DELETE FROM basevideo")
    void clearMovies();
}
