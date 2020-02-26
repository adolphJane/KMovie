package com.magicalrice.adolph.kmovie.data.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.MovieDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.RoleDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.TvShowDetailBean;

/**
 * Created by Adolph on 2018/3/6.
 */

@Database(entities = {BaseVideo.class, MovieDetailBean.class, TvShowDetailBean.class, RoleDetailBean.class},version = 1)
@TypeConverters(DatabaseConverters.class)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract VideoListDao videoListDao();
    public abstract MovieDetailDao movieDetailDao();
    public abstract TvDetailDao tvDetailDao();
    public abstract RoleDetailDao roleDetailDao();
}