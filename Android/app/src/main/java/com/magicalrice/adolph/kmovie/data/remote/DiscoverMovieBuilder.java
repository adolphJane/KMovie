package com.magicalrice.adolph.kmovie.data.remote;

import com.magicalrice.adolph.kmovie.data.entities.DiscoverFilter;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.TmdbDate;
import com.magicalrice.adolph.kmovie.data.enumerations.SortBy;
import com.magicalrice.adolph.kmovie.data.remote.services.DiscoverService;

import retrofit2.Call;

public class DiscoverMovieBuilder {

    private final DiscoverService discoverService;

    private String language;
    private String region;
    private SortBy sort_by;
    private String certification_country;
    private String certification;
    private String certification_lte;
    private boolean include_adult;
    private boolean include_video;
    private int page;
    private int primary_release_year;
    private TmdbDate primary_release_date_gte;
    private TmdbDate primary_release_date_lte;
    private TmdbDate release_date_gte;
    private TmdbDate release_date_lte;
    private int vote_count_gte;
    private int vote_count_lte;
    private float vote_average_gte;
    private float vote_average_lte;
    private DiscoverFilter with_cast;
    private DiscoverFilter with_crew;
    private DiscoverFilter with_companies;
    private DiscoverFilter with_genres;
    private DiscoverFilter with_keywords;
    private DiscoverFilter with_people;
    private int year;
    private DiscoverFilter without_genres;
    private int with_runtime_gte;
    private int with_runtime_lte;
    private DiscoverFilter with_release_type;
    private String with_original_language;
    private DiscoverFilter without_keywords;

    public DiscoverMovieBuilder(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    public DiscoverMovieBuilder language(String value) {
        this.language = value;
        return this;
    }

    public DiscoverMovieBuilder region(String value) {
        this.region = value;
        return this;
    }

    public DiscoverMovieBuilder sort_by(SortBy value) {
        this.sort_by = value;
        return this;
    }

    public DiscoverMovieBuilder certification_country(String value) {
        this.certification_country = value;
        return this;
    }

    public DiscoverMovieBuilder certification(String value) {
        this.certification = value;
        return this;
    }

    public DiscoverMovieBuilder certification_lte(String value) {
        this.certification_lte = value;
        return this;
    }

    public DiscoverMovieBuilder includeAdult() {
        this.include_adult = true;
        return this;
    }

    public DiscoverMovieBuilder includeVideo() {
        this.include_video = true;
        return this;
    }

    public DiscoverMovieBuilder page(int value) {
        this.page = value;
        return this;
    }

    public DiscoverMovieBuilder primary_release_year(int value) {
        this.primary_release_year = value;
        return this;
    }

    public DiscoverMovieBuilder primary_release_date_gte(TmdbDate value) {
        this.primary_release_date_gte = value;
        return this;
    }

    public DiscoverMovieBuilder primary_release_date_lte(TmdbDate value) {
        this.primary_release_date_lte = value;
        return this;
    }

    public DiscoverMovieBuilder release_date_gte(TmdbDate value) {
        this.release_date_gte = value;
        return this;
    }

    public DiscoverMovieBuilder release_date_lte(TmdbDate value) {
        this.release_date_lte = value;
        return this;
    }

    public DiscoverMovieBuilder vote_count_gte(int value) {
        this.vote_count_gte = value;
        return this;
    }

    public DiscoverMovieBuilder vote_count_lte(int value) {
        this.vote_count_lte = value;
        return this;
    }

    public DiscoverMovieBuilder vote_average_gte(float value) {
        this.vote_average_gte = value;
        return this;
    }

    public DiscoverMovieBuilder vote_average_lte(float value) {
        this.vote_average_lte = value;
        return this;
    }

    public DiscoverMovieBuilder with_cast(DiscoverFilter value) {
        this.with_cast = value;
        return this;
    }

    public DiscoverMovieBuilder with_crew(DiscoverFilter value) {
        this.with_crew = value;
        return this;
    }

    public DiscoverMovieBuilder with_companies(DiscoverFilter value) {
        this.with_companies = value;
        return this;
    }

    public DiscoverMovieBuilder with_genres(DiscoverFilter value) {
        this.with_genres = value;
        return this;
    }

    public DiscoverMovieBuilder with_keywords(DiscoverFilter value) {
        this.with_keywords = value;
        return this;
    }

    public DiscoverMovieBuilder with_people(DiscoverFilter value) {
        this.with_people = value;
        return this;
    }

    public DiscoverMovieBuilder year(int value) {
        this.year = value;
        return this;
    }

    public DiscoverMovieBuilder without_genres(DiscoverFilter value) {
        this.without_genres = value;
        return this;
    }

    public DiscoverMovieBuilder with_runtime_gte(int value) {
        this.with_runtime_gte = value;
        return this;
    }

    public DiscoverMovieBuilder with_runtime_lte(int value) {
        this.with_runtime_lte = value;
        return this;
    }

    public DiscoverMovieBuilder with_release_type(DiscoverFilter value) {
        this.with_release_type = value;
        return this;
    }

    public DiscoverMovieBuilder with_original_language(String value) {
        this.with_original_language = value;
        return this;
    }

    public DiscoverMovieBuilder without_keywords(DiscoverFilter keywords) {
        this.without_keywords = keywords;
        return this;
    }

    public Call<MovieResultsPage> build() {
        return discoverService.discoverMovie(
                language,
                region,
                sort_by,
                certification_country,
                certification,
                certification_lte,
                include_adult,
                include_video,
                page,
                primary_release_year,
                primary_release_date_gte,
                primary_release_date_lte,
                release_date_gte,
                release_date_lte,
                vote_count_gte,
                vote_count_lte,
                vote_average_gte,
                vote_average_lte,
                with_cast,
                with_crew,
                with_companies,
                with_genres,
                with_keywords,
                with_people,
                year,
                without_genres,
                with_runtime_gte,
                with_runtime_lte,
                with_release_type,
                with_original_language,
                without_keywords
        );
    }

}
