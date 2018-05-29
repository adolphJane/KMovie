package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class PersonMovieCredits {
    private int id;
    private List<BaseMoviePersonCredit> cast;
    private List<PersonMovieCrewCredit> crew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BaseMoviePersonCredit> getCast() {
        return cast;
    }

    public void setCast(List<BaseMoviePersonCredit> cast) {
        this.cast = cast;
    }

    public List<PersonMovieCrewCredit> getCrew() {
        return crew;
    }

    public void setCrew(List<PersonMovieCrewCredit> crew) {
        this.crew = crew;
    }
}
