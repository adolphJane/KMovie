package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class Images {
    private int id;
    private List<Image> backdrops;
    private List<Image> posters;
    private List<Image> stills;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Image> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Image> backdrops) {
        this.backdrops = backdrops;
    }

    public List<Image> getPosters() {
        return posters;
    }

    public void setPosters(List<Image> posters) {
        this.posters = posters;
    }

    public List<Image> getStills() {
        return stills;
    }

    public void setStills(List<Image> stills) {
        this.stills = stills;
    }
}
