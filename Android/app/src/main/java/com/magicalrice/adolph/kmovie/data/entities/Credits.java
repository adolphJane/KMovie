package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class Credits {
    private int id;
    private List<CastMember> cast;
    private List<CrewMember> crew;
    private List<CastMember> guest_stars;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CastMember> getCast() {
        return cast;
    }

    public void setCast(List<CastMember> cast) {
        this.cast = cast;
    }

    public List<CrewMember> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewMember> crew) {
        this.crew = crew;
    }

    public List<CastMember> getGuest_stars() {
        return guest_stars;
    }

    public void setGuest_stars(List<CastMember> guest_stars) {
        this.guest_stars = guest_stars;
    }
}
