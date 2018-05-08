package com.magicalrice.adolph.kmovie.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Keywords {

    private int id;

    @SerializedName(value = "keywords", alternate = {"results"})
    private List<BaseKeyword> keywords;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BaseKeyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<BaseKeyword> keywords) {
        this.keywords = keywords;
    }
}
