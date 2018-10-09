package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class GenreResults {
    private List<Genre> genres;
    private int type;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Genre genre : genres) {
            builder.append(genre.toString());
        }
        return builder.toString();
    }
}
