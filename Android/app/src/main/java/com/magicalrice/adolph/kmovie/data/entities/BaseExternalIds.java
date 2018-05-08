package com.magicalrice.adolph.kmovie.data.entities;

public abstract class BaseExternalIds {
    private String freebase_id;
    private String freebase_mid;
    private int id;
    private int tvrage_id;

    public String getFreebase_id() {
        return freebase_id;
    }

    public void setFreebase_id(String freebase_id) {
        this.freebase_id = freebase_id;
    }

    public String getFreebase_mid() {
        return freebase_mid;
    }

    public void setFreebase_mid(String freebase_mid) {
        this.freebase_mid = freebase_mid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTvrage_id() {
        return tvrage_id;
    }

    public void setTvrage_id(int tvrage_id) {
        this.tvrage_id = tvrage_id;
    }
}
