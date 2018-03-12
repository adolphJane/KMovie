package com.magicalrice.adolph.kmovie.data.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.magicalrice.adolph.kmovie.data.Movie;

/**
 * Created by Adolph on 2018/3/12.
 */

@Database(entities = {Movie.class},version = 1)
public abstract class MovieDatabase extends RoomDatabase{

}
