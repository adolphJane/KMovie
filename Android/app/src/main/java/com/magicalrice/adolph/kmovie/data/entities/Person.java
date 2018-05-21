package com.magicalrice.adolph.kmovie.data.entities;

import com.google.gson.JsonPrimitive;

import java.util.Date;
import java.util.List;

public class Person extends BasePerson {

    private List<JsonPrimitive> also_known_as;
    private String biography;
    private Date birthday;
    private Date deathday;
    private int gender;
    private String homepage;
    private String imdb_id;
    private String place_of_birth;

    private PersonExternalIds external_ids;
    private PersonCredits combined_credits;
    private PersonCredits movie_credits;
    private PersonCredits tv_credits;
    private PersonImages images;
    private TaggedImagesResultsPage tagged_images;
    private Changes changes;

    public void setAlso_known_as(List<JsonPrimitive> also_known_as) {
        this.also_known_as = also_known_as;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setDeathday(Date deathday) {
        this.deathday = deathday;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public void setExternal_ids(PersonExternalIds external_ids) {
        this.external_ids = external_ids;
    }

    public void setCombined_credits(PersonCredits combined_credits) {
        this.combined_credits = combined_credits;
    }

    public void setMovie_credits(PersonCredits movie_credits) {
        this.movie_credits = movie_credits;
    }

    public void setTv_credits(PersonCredits tv_credits) {
        this.tv_credits = tv_credits;
    }

    public void setImages(PersonImages images) {
        this.images = images;
    }

    public void setTagged_images(TaggedImagesResultsPage tagged_images) {
        this.tagged_images = tagged_images;
    }

    public void setChanges(Changes changes) {
        this.changes = changes;
    }

    public List<JsonPrimitive> getAlso_known_as() {
        return also_known_as;
    }

    public String getBiography() {
        return biography;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Date getDeathday() {
        return deathday;
    }

    public int getGender() {
        return gender;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public PersonExternalIds getExternal_ids() {
        return external_ids;
    }

    public PersonCredits getCombined_credits() {
        return combined_credits;
    }

    public PersonCredits getMovie_credits() {
        return movie_credits;
    }

    public PersonCredits getTv_credits() {
        return tv_credits;
    }

    public PersonImages getImages() {
        return images;
    }

    public TaggedImagesResultsPage getTagged_images() {
        return tagged_images;
    }

    public Changes getChanges() {
        return changes;
    }
}
