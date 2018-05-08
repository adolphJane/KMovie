package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class PersonCredits {
    private int id;
    private List<PersonCastCredit> cast;
    private List<PersonCrewCredit> crew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PersonCastCredit> getCast() {
        return cast;
    }

    public void setCast(List<PersonCastCredit> cast) {
        this.cast = cast;
    }

    public List<PersonCrewCredit> getCrew() {
        return crew;
    }

    public void setCrew(List<PersonCrewCredit> crew) {
        this.crew = crew;
    }
}
