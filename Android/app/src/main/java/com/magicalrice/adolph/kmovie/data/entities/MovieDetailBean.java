package com.magicalrice.adolph.kmovie.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Adolph on 2018/5/7.
 */

@Entity(tableName = "movie_detail")
public class MovieDetailBean {
    @PrimaryKey
    private long movieDetailId;
    private Movie movie;
    private Credits credits;
    private Images images;
    private ReleaseDatesResults datesResults;
    private Keywords keywords;
    private MovieResultsPage recommendationResult,similarResult;
    private boolean isLike;
    private int sourceType = 0;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public ReleaseDatesResults getDatesResults() {
        return datesResults;
    }

    public void setDatesResults(ReleaseDatesResults datesResults) {
        this.datesResults = datesResults;
    }

    public Keywords getKeywords() {
        return keywords;
    }

    public void setKeywords(Keywords keywords) {
        this.keywords = keywords;
    }

    public MovieResultsPage getRecommendationResult() {
        return recommendationResult;
    }

    public void setRecommendationResult(MovieResultsPage recommendationResult) {
        this.recommendationResult = recommendationResult;
    }

    public MovieResultsPage getSimilarResult() {
        return similarResult;
    }

    public void setSimilarResult(MovieResultsPage similarResult) {
        this.similarResult = similarResult;
    }

    public long getMovieDetailId() {
        return movieDetailId;
    }

    public void setMovieDetailId(long movieDetailId) {
        this.movieDetailId = movieDetailId;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }
}
