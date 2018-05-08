package com.magicalrice.adolph.kmovie.data.entities;

import com.magicalrice.adolph.kmovie.data.enumerations.MediaType;

public class FavoriteMedia {
    private MediaType media_type;
    private int media_id;
    private boolean favorite;

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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
