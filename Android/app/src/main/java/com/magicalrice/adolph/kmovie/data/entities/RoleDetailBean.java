package com.magicalrice.adolph.kmovie.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Adolph on 2018/5/21.
 */

@Entity(tableName = "role_detail_bean")
public class RoleDetailBean {
    @PrimaryKey
    private long roleId;
    private PersonImages personImages;
    private PersonMovieCredits movieCredits;
    private PersonTvCredits tvCredits;
    private Person person;
    private boolean isLike;

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

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
