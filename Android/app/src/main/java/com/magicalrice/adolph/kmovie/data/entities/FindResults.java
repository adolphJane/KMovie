package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class FindResults {
    private List<BaseMovie> movie_results;
    private List<BasePerson> person_results;
    private List<BaseTvShow> tv_results;
    private List<BaseTvSeason> tv_season_results;
    private List<BaseTvEpisode> tv_episode_results;

    public List<BaseMovie> getMovie_results() {
        return movie_results;
    }

    public void setMovie_results(List<BaseMovie> movie_results) {
        this.movie_results = movie_results;
    }

    public List<BasePerson> getPerson_results() {
        return person_results;
    }

    public void setPerson_results(List<BasePerson> person_results) {
        this.person_results = person_results;
    }

    public List<BaseTvShow> getTv_results() {
        return tv_results;
    }

    public void setTv_results(List<BaseTvShow> tv_results) {
        this.tv_results = tv_results;
    }

    public List<BaseTvSeason> getTv_season_results() {
        return tv_season_results;
    }

    public void setTv_season_results(List<BaseTvSeason> tv_season_results) {
        this.tv_season_results = tv_season_results;
    }

    public List<BaseTvEpisode> getTv_episode_results() {
        return tv_episode_results;
    }

    public void setTv_episode_results(List<BaseTvEpisode> tv_episode_results) {
        this.tv_episode_results = tv_episode_results;
    }
}
