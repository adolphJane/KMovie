package com.magicalrice.adolph.kmovie.data.entities;

/**
 * Created by Adolph on 2018/4/4.
 */
public class BaseAccountStates {
    private int id;
    private boolean rated;
    private RatingObject rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }

    public RatingObject getRating() {
        return rating;
    }

    public void setRating(RatingObject rating) {
        this.rating = rating;
    }
}
