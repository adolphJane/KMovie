package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class Collection extends BaseCollection {
    private List<BaseMovie> parts;
    private String overview;
    private Images images;

    public List<BaseMovie> getParts() {
        return parts;
    }

    public void setParts(List<BaseMovie> parts) {
        this.parts = parts;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}
