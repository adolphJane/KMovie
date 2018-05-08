package com.magicalrice.adolph.kmovie.data.entities;

import java.util.Date;
import java.util.List;

public class TvShow extends BaseTvShow {

    private List<Person> created_by;
    private List<Network> networks;
    private List<Integer> episode_run_time;
    private List<Genre> genres;
    private String homepage;
    private boolean in_production;
    private List<String> languages;
    private Date last_air_date;
    private Integer number_of_episodes;
    private Integer number_of_seasons;
    private List<BaseCompany> production_companies;
    private List<TvSeason> seasons;
    private String status;
    private String type;

    // Following are used with append_to_response
    private Images images;
    private Credits credits;
    private TvExternalIds external_ids;
    private AlternativeTitles alternative_titles;
    private Changes changes;
    private Keywords keywords;
    private TvShowResultsPage recommendations;
    private Translations translations;
    private ContentRatings content_ratings;
    private TvShowResultsPage similar;
    private Videos videos;

    public List<Person> getCreated_by() {
        return created_by;
    }

    public void setCreated_by(List<Person> created_by) {
        this.created_by = created_by;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    public List<Integer> getEpisode_run_time() {
        return episode_run_time;
    }

    public void setEpisode_run_time(List<Integer> episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public boolean isIn_production() {
        return in_production;
    }

    public void setIn_production(boolean in_production) {
        this.in_production = in_production;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Date getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(Date last_air_date) {
        this.last_air_date = last_air_date;
    }

    public Integer getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(Integer number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public Integer getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(Integer number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public List<BaseCompany> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<BaseCompany> production_companies) {
        this.production_companies = production_companies;
    }

    public List<TvSeason> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<TvSeason> seasons) {
        this.seasons = seasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public TvExternalIds getExternal_ids() {
        return external_ids;
    }

    public void setExternal_ids(TvExternalIds external_ids) {
        this.external_ids = external_ids;
    }

    public AlternativeTitles getAlternative_titles() {
        return alternative_titles;
    }

    public void setAlternative_titles(AlternativeTitles alternative_titles) {
        this.alternative_titles = alternative_titles;
    }

    public Changes getChanges() {
        return changes;
    }

    public void setChanges(Changes changes) {
        this.changes = changes;
    }

    public Keywords getKeywords() {
        return keywords;
    }

    public void setKeywords(Keywords keywords) {
        this.keywords = keywords;
    }

    public TvShowResultsPage getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(TvShowResultsPage recommendations) {
        this.recommendations = recommendations;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    public ContentRatings getContent_ratings() {
        return content_ratings;
    }

    public void setContent_ratings(ContentRatings content_ratings) {
        this.content_ratings = content_ratings;
    }

    public TvShowResultsPage getSimilar() {
        return similar;
    }

    public void setSimilar(TvShowResultsPage similar) {
        this.similar = similar;
    }

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }
}
