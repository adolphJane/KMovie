package com.magicalrice.adolph.kmovie.data.entities;

import okhttp3.MediaType;

public class WatchlistMedia {

    private MediaType media_type;
    private int media_id;
    private boolean watchlist;

    public MediaType getMedia_type() {
        return media_type;
    }

    public void setMedia_type(MediaType media_type) {
        this.media_type = media_type;
    }

    public int getMedia_id() {
        return media_id;
    }

    public void setMedia_id(int media_id) {
        this.media_id = media_id;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }
}
