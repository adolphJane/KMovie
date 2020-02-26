package com.magicalrice.adolph.kmovie.data.local.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;

import java.util.List;

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
