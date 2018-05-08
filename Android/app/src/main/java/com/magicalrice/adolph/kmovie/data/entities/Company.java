package com.magicalrice.adolph.kmovie.data.entities;

public class Company extends BaseCompany {
    private String description;
    private String headquarters;
    private String homepage;
    private Company parent_company;
    private MovieResultsPage movies;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Company getParent_company() {
        return parent_company;
    }

    public void setParent_company(Company parent_company) {
        this.parent_company = parent_company;
    }

    public MovieResultsPage getMovies() {
        return movies;
    }

    public void setMovies(MovieResultsPage movies) {
        this.movies = movies;
    }
}
