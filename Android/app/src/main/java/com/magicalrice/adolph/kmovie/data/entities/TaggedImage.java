package com.magicalrice.adolph.kmovie.data.entities;

public class TaggedImage extends Image {

    private String id;
    private String media_type;
    private TmdbDate release_date;
    private Media media;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public TmdbDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(TmdbDate release_date) {
        this.release_date = release_date;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
}