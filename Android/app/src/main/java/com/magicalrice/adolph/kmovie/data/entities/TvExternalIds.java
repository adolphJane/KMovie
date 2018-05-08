package com.magicalrice.adolph.kmovie.data.entities;

public class TvExternalIds extends BaseExternalIds {
    private String imdb_id;
    private int tvdb_id;

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public int getTvdb_id() {
        return tvdb_id;
    }

    public void setTvdb_id(int tvdb_id) {
        this.tvdb_id = tvdb_id;
    }
}
