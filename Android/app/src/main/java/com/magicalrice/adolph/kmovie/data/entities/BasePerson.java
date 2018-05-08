package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class BasePerson {
    private String profile_path;
    private boolean adult;
    private int id;
    private String name;
    private int popularity;
    private List<Media> known_for;

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public List<Media> getKnown_for() {
        return known_for;
    }

    public void setKnown_for(List<Media> known_for) {
        this.known_for = known_for;
    }
}
