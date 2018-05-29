package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

/**
 * Created by Adolph on 2018/5/22.
 */

public class PersonTvCredits {
    private int id;
    private List<BaseTvPersonCredit> cast;
    private List<PersonTvCrewCredit> crew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BaseTvPersonCredit> getCast() {
        return cast;
    }

    public void setCast(List<BaseTvPersonCredit> cast) {
        this.cast = cast;
    }

    public List<PersonTvCrewCredit> getCrew() {
        return crew;
    }

    public void setCrew(List<PersonTvCrewCredit> crew) {
        this.crew = crew;
    }
}