package com.magicalrice.adolph.kmovie.data.entities;

import androidx.annotation.FloatRange;

/**
 * Created by Adolph on 2018/4/4.
 */
public class RatingObject {
    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(@FloatRange(from = 0.5,to = 10.0) double value) {
        this.value = value;
    }
}
