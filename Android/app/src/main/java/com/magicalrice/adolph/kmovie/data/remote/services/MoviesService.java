package com.magicalrice.adolph.kmovie.data.remote.services;

import com.magicalrice.adolph.kmovie.data.entities.AccountStates;
import com.magicalrice.adolph.kmovie.data.entities.AlternativeTitles;
import com.magicalrice.adolph.kmovie.data.entities.AppendToResponse;
import com.magicalrice.adolph.kmovie.data.entities.Changes;
import com.magicalrice.adolph.kmovie.data.entities.Credits;
import com.magicalrice.adolph.kmovie.data.entities.Images;
import com.magicalrice.adolph.kmovie.data.entities.Keywords;
import com.magicalrice.adolph.kmovie.data.entities.ListResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.Movie;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.RatingObject;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDate;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDatesResults;
import com.magicalrice.adolph.kmovie.data.entities.ReviewResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.Status;
import com.magicalrice.adolph.kmovie.data.entities.TmdbDate;
import com.magicalrice.adolph.kmovie.data.entities.Translations;
import com.magicalrice.adolph.kmovie.data.entities.Videos;
import com.magicalrice.adolph.kmovie.data.enumerations.AuthenticationType;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface MoviesService {

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("language") String language
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId A Movie TMDb id.
     */
    @GET("movie/{movie_id}")
    Observable<Movie> summary(
            @Path("movie_id") int movieId
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId          A Movie TMDb id.
     * @param language         <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Values:</b> alternative_titles, changes, credits, images, keywords, release_dates, videos, translations, recommendations, similar, reviews, lists
     */
    @GET("movie/{movie_id}")
    Observable<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId          A Movie TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Values:</b> alternative_titles, changes, credits, images, keywords, release_dates, videos, translations, recommendations, similar, reviews, lists
     */
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId          A Movie TMDb id.
     * @param language         <em>Optional.</em> ISO 639-1 code.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> alternative_titles, changes, credits, images, keywords, release_dates, videos, translations, recommendations, similar, reviews, lists
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("language") String language,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Get the basic movie information for a specific movie id.
     *
     * @param movieId          A Movie TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> alternative_titles, changes, credits, images, keywords, release_dates, videos, translations, recommendations, similar, reviews, lists
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("movie/{movie_id}")
    Call<Movie> summary(
            @Path("movie_id") int movieId,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Grab the following account states for a session:
     *
     * * Movie rating
     * * If it belongs to your watchlist
     * * If it belongs to your favorite list
     *
     * <b>Requires an active Session.</b>
     *
     * @param movieId A Movie TMDb id.
     */
    @GET("movie/{movie_id}/account_states")
    Call<AccountStates> accountStates(
            @Path("movie_id") int movieId
    );

    /**
     * Get the alternative titles for a specific movie id.
     *
     * @param movieId A Movie TMDb id.
     * @param country <em>Optional.</em> ISO 3166-1 code.
     */
    @GET("movie/{movie_id}/alternative_titles")
    Call<AlternativeTitles> alternativeTitles(
            @Path("movie_id") int movieId,
            @Query("country") String country
    );

    /**
     * Get the changes for a movie. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param movieId    A Movie TMDb id.
     * @param start_date <em>Optional.</em> Starting date of changes occurred to a movie.
     * @param end_date   <em>Optional.</em> Ending date of changes occurred to a movie.
     * @param page       <em>Optional.</em> Minimum value is 1, expected value is an integer.
     */
    @GET("movie/{movie_id}/changes")
    Call<Changes> changes(
            @Path("movie_id") int movieId,
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date,
            @Query("page") Integer page
    );

    /**
     * Get the cast and crew information for a specific movie id.
     *
     * @param movieId A Movie TMDb id.
     */
    @GET("movie/{movie_id}/credits")
    Observable<Credits> credits(
            @Path("movie_id") int movieId
    );

    /**
     * Get the images (posters and backdrops) for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/images")
    Observable<Images> images(
            @Path("movie_id") int movieId,
            @Query("language") String language
    );

    /**
     * Get the plot keywords for a specific movie id.
     *
     * @param movieId A Movie TMDb id.
     */
    @GET("movie/{movie_id}/keywords")
    Observable<Keywords> keywords(
            @Path("movie_id") int movieId
    );

    /**
     * Get the lists that the movie belongs to.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/lists")
    Observable<ListResultsPage> lists(
            @Path("movie_id") int movieId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the similar movies for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/similar")
    Observable<MovieResultsPage> similar(
            @Path("movie_id") int movieId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the recommendations for a particular movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/recommendations")
    Observable<MovieResultsPage> recommendations(
            @Path("movie_id") int movieId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the release dates, certifications and related information by country for a specific movie id.
     *
     * The results are keyed by iso_3166_1 code and contain a type value which on our system, maps to:
     * {@link ReleaseDate#TYPE_PREMIERE}, {@link ReleaseDate#TYPE_THEATRICAL_LIMITED},
     * {@link ReleaseDate#TYPE_THEATRICAL}, {@link ReleaseDate#TYPE_DIGITAL}, {@link ReleaseDate#TYPE_PHYSICAL},
     * {@link ReleaseDate#TYPE_TV}
     *
     * @param movieId A Movie TMDb id.
     */
    @GET("movie/{movie_id}/release_dates")
    Observable<ReleaseDatesResults> releaseDates(
            @Path("movie_id") int movieId
    );

    /**
     * Get the reviews for a particular movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/reviews")
    Observable<ReviewResultsPage> reviews(
            @Path("movie_id") int movieId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the translations for a specific movie id.
     *
     * @param movieId A Movie TMDb id.
     */
    @GET("movie/{movie_id}/translations")
    Observable<Translations> translations(
            @Path("movie_id") int movieId
    );

    /**
     * Get the videos (trailers, teasers, clips, etc...) for a specific movie id.
     *
     * @param movieId  A Movie TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/{movie_id}/videos")
    Observable<Videos> videos(
            @Path("movie_id") int movieId,
            @Query("language") String language
    );

    /**
     * Get the latest movie id.
     */
    @GET("movie/latest")
    Observable<Movie> latest();

    /**
     * Get the list of movies playing in theaters. This list refreshes every day. The maximum number of items this list
     * will include is 100.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/now_playing")
    Observable<MovieResultsPage> nowPlaying(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of popular movies on The Movie Database. This list refreshes every day.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/popular")
    Observable<MovieResultsPage> popular(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of top rated movies. By default, this list will only include movies that have 10 or more votes. This
     * list refreshes every day.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/top_rated")
    Observable<MovieResultsPage> topRated(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of upcoming movies. This list refreshes every day. The maximum number of items this list will
     * include is 100.
     *
     * @param page     <em>Optional.</em> Minimum value is 1, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("movie/upcoming")
    Observable<MovieResultsPage> upcoming(
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Sets the Rating for the movie with the specified id.
     *
     * <b>Requires an active Session.</b>
     *
     * @param movieId            A Movie TMDb id.
     * @param authenticationType Authentication Type for this operation. Available Choices: Account, Guest.
     * @param body               <em>Required.</em> A ReviewObject Object. Minimum value is 0.5 and Maximum 10.0, expected value is a number.
     */
    @POST("movie/{movie_id}/rating")
    Observable<Status> addRating(
            @Path("movie_id") Integer movieId,
            @Query("authentication") AuthenticationType authenticationType,
            @Body RatingObject body
    );

    /**
     * Sets the Rating for the movie with the specified id.
     *
     * <b>Requires an active Session.</b>
     *
     * @param movieId A Movie TMDb id.
     * @param body    <em>Required.</em> A ReviewObject Object. Minimum value is 0.5 and Maximum 10.0, expected value is a number.
     */
    @POST("movie/{movie_id}/rating")
    Observable<Status> addRating(
            @Path("movie_id") Integer movieId,
            @Body RatingObject body
    );

    /**
     * Deletes the Rating for the movie with the specified id.
     *
     * <b>Requires an active Session.</b>
     *
     * @param movieId            A Movie TMDb id.
     * @param authenticationType Authentication Type for this operation. Available Choices: Account, Guest.
     */
    @DELETE("movie/{movie_id}/rating")
    Observable<Status> deleteRating(
            @Path("movie_id") Integer movieId,
            @Query("authentication") AuthenticationType authenticationType
    );

    /**
     * Deletes the Rating for the movie with the specified id.
     *
     * <b>Requires an active Session.</b>
     *
     * @param movieId A Movie TMDb id.
     */
    @DELETE("movie/{movie_id}/rating")
    Observable<Status> deleteRating(
            @Path("movie_id") Integer movieId
    );

}
