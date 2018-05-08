package com.magicalrice.adolph.kmovie.data.entities;


import java.util.List;

public class Movie extends BaseMovie {
    private Collection belongs_to_collection;
    private int budget;
    private String homepage;
    private String imdb_id;
    private List<BaseCompany> production_companies;
    private List<Country> production_countries;
    private int revenue;
    private int runtime;
    private List<SpokenLanguage> spoken_languages;
    private String status;
    private String tagline;

    // Following are used with append_to_response
    private AlternativeTitles alternative_titles;
    private Changes changes;
    private Keywords keywords;
    private ListResultsPage lists;
    private Images images;
    private Translations translations;
    private Credits credits;
    private ReleaseDatesResults release_dates;
    private MovieResultsPage similar;
    private MovieResultsPage recommendations;
    private ReviewResultsPage reviews;
    private Videos videos;

    public Collection getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public void setBelongs_to_collection(Collection belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public List<BaseCompany> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<BaseCompany> production_companies) {
        this.production_companies = production_companies;
    }

    public List<Country> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<Country> production_countries) {
        this.production_countries = production_countries;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<SpokenLanguage> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<SpokenLanguage> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
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

    public ListResultsPage getLists() {
        return lists;
    }

    public void setLists(ListResultsPage lists) {
        this.lists = lists;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public ReleaseDatesResults getRelease_dates() {
        return release_dates;
    }

    public void setRelease_dates(ReleaseDatesResults release_dates) {
        this.release_dates = release_dates;
    }

    public MovieResultsPage getSimilar() {
        return similar;
    }

    public void setSimilar(MovieResultsPage similar) {
        this.similar = similar;
    }

    public MovieResultsPage getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(MovieResultsPage recommendations) {
        this.recommendations = recommendations;
    }

    public ReviewResultsPage getReviews() {
        return reviews;
    }

    public void setReviews(ReviewResultsPage reviews) {
        this.reviews = reviews;
    }

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }
}
