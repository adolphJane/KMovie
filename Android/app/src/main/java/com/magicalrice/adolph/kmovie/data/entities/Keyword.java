package com.magicalrice.adolph.kmovie.data.entities;

public class Keyword extends BaseKeyword {
    private MovieResultsPage movies;

    public MovieResultsPage getMovies() {
        return movies;
    }

    public void setMovies(MovieResultsPage movies) {
        this.movies = movies;
    }
}
