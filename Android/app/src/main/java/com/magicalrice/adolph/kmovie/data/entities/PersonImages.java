package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class PersonImages {

    private int id;
    private List<Image> profiles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Image> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Image> profiles) {
        this.profiles = profiles;
    }
}
