package com.magicalrice.adolph.kmovie.business.movie_detail;

/**
 * Created by Adolph on 2018/5/17.
 */

public interface MovieDetailEvent {
    public void switchBaseInfo(int position);
    public void switchMovieInfo(int position);
}
