package com.magicalrice.adolph.kmovie.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Adolph on 2018/3/12.
 */
@Entity(tableName = "movies")
public final class Movie {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entryid")
    private final String mId;

    public Movie(@NonNull String id) {
        this.mId = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return mId.equals(movie.mId);
    }

    @NonNull
    public String getmId() {
        return mId;
    }
}
