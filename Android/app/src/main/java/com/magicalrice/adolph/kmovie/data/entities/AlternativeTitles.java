package com.magicalrice.adolph.kmovie.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class AlternativeTitles {
    @SerializedName(value = "titles", alternate = {"results"})
    private List<AlternativeTitle> titles;
    private int id;

    public List<AlternativeTitle> getTitles() {
        return titles;
    }

    public void setTitles(List<AlternativeTitle> titles) {
        this.titles = titles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
