package com.magicalrice.adolph.kmovie.data.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;

/**
 * Created by Adolph on 2018/3/6.
 */

@Database(entities = {BaseVideo.class},version = 1)
public abstract class MovieDatabase extends RoomDatabase{
    public abstract MovieListDao movieListDao();
    public abstract TvListDao tvListDao();
}