package com.magicalrice.adolph.kmovie.data.entities;

/**
 * Created by Adolph on 2018/5/21.
 */

public class TvShowDetailBean {
    private TvShow tvShow;
    private Credits credits;
    private Keywords keywords;
    private TvShowResultsPage tvRecommendation,tvSimilar;
    private Images images;

    public TvShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TvShow tvShow) {
        this.tvShow = tvShow;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public Keywords getKeywords() {
        return keywords;
    }

    public void setKeywords(Keywords keywords) {
        this.keywords = keywords;
    }

    public TvShowResultsPage getTvRecommendation() {
        return tvRecommendation;
    }

    public void setTvRecommendation(TvShowResultsPage tvRecommendation) {
        this.tvRecommendation = tvRecommendation;
    }

    public TvShowResultsPage getTvSimilar() {
        return tvSimilar;
    }

    public void setTvSimilar(TvShowResultsPage tvSimilar) {
        this.tvSimilar = tvSimilar;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}
