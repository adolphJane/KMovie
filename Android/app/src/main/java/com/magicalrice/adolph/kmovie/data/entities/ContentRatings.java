package com.magicalrice.adolph.kmovie.data.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

public class ContentRatings {
    private List<ContentRating> results;
    @PrimaryKey
    @ColumnInfo(name = "content_rating_id")
    private int id;

    public List<ContentRating> getResults() {
        return results;
    }

    public void setResults(List<ContentRating> results) {
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
