package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class CreditMedia {
    private int id;
    private String name;
    private String original_name;
    private String character;
    //Provides Not data at the moment. API Related bug.
    //public List<JsonPrimitive> episodes;
    private List<TvSeason> seasons;

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

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public List<TvSeason> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<TvSeason> seasons) {
        this.seasons = seasons;
    }
}
