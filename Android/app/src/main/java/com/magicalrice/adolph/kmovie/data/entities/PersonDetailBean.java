package com.magicalrice.adolph.kmovie.data.entities;

import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Adolph on 2018/5/21.
 */

public class PersonDetailBean {
    private PersonImages personImages;
    private PersonMovieCredits movieCredits;
    private PersonTvCredits tvCredits;
    private Person person;

    public PersonImages getPersonImages() {
        return personImages;
    }

    public void setPersonImages(PersonImages personImages) {
        this.personImages = personImages;
    }

    public PersonMovieCredits getMovieCredits() {
        return movieCredits;
    }

    public void setMovieCredits(PersonMovieCredits movieCredits) {
        this.movieCredits = movieCredits;
    }

    public PersonTvCredits getTvCredits() {
        return tvCredits;
    }

    public void setTvCredits(PersonTvCredits tvCredits) {
        this.tvCredits = tvCredits;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
