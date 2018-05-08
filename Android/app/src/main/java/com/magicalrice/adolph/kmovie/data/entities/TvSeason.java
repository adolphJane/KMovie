package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class TvSeason extends BaseTvSeason {
    private String _id;
    private List<TvEpisode> episodes;
    private String name;
    private String overview;
    private Credits credits;
    private Images images;
    private Videos videos;
    private TvSeasonExternalIds external_ids;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<TvEpisode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<TvEpisode> episodes) {
        this.episodes = episodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }

    public TvSeasonExternalIds getExternal_ids() {
        return external_ids;
    }

    public void setExternal_ids(TvSeasonExternalIds external_ids) {
        this.external_ids = external_ids;
    }
}
