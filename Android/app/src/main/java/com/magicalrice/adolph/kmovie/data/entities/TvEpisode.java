package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class TvEpisode extends BaseTvEpisode {
    private List<CrewMember> crew;
    private List<CastMember> guest_stars;
    private Images images;
    private TvExternalIds external_ids;
    private Credits credits;
    private Videos videos;

    public List<CrewMember> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewMember> crew) {
        this.crew = crew;
    }

    public List<CastMember> getGuest_stars() {
        return guest_stars;
    }

    public void setGuest_stars(List<CastMember> guest_stars) {
        this.guest_stars = guest_stars;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public TvExternalIds getExternal_ids() {
        return external_ids;
    }

    public void setExternal_ids(TvExternalIds external_ids) {
        this.external_ids = external_ids;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }
}
