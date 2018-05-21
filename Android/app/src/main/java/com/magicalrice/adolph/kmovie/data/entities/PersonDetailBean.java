package com.magicalrice.adolph.kmovie.data.entities;

/**
 * Created by Adolph on 2018/5/21.
 */

public class PersonDetailBean {
    private PersonImages personImages;
    private PersonCredits movieCredits,tvCredits;
    private Person person;

    public PersonImages getPersonImages() {
        return personImages;
    }

    public void setPersonImages(PersonImages personImages) {
        this.personImages = personImages;
    }

    public PersonCredits getMovieCredits() {
        return movieCredits;
    }

    public void setMovieCredits(PersonCredits movieCredits) {
        this.movieCredits = movieCredits;
    }

    public PersonCredits getTvCredits() {
        return tvCredits;
    }

    public void setTvCredits(PersonCredits tvCredits) {
        this.tvCredits = tvCredits;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
