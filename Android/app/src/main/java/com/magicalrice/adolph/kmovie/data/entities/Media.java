package com.magicalrice.adolph.kmovie.data.entities;


import com.magicalrice.adolph.kmovie.data.enumerations.MediaType;

public class Media {
    private BaseTvShow tvShow;
    private BaseMovie movie;
    private BasePerson person;
    private MediaType media_type;

    public BaseTvShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(BaseTvShow tvShow) {
        this.tvShow = tvShow;
    }

    public BaseMovie getMovie() {
        return movie;
    }

    public void setMovie(BaseMovie movie) {
        this.movie = movie;
    }

    public BasePerson getPerson() {
        return person;
    }

    public void setPerson(BasePerson person) {
        this.person = person;
    }

    public MediaType getMedia_type() {
        return media_type;
    }

    public void setMedia_type(MediaType media_type) {
        this.media_type = media_type;
    }
}
