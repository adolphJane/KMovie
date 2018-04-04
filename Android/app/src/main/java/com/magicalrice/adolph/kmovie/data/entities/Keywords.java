package com.magicalrice.adolph.kmovie.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Keywords {

    public Integer id;

    @SerializedName(value = "keywords", alternate = {"results"})
    public List<BaseKeyword> keywords;

}
