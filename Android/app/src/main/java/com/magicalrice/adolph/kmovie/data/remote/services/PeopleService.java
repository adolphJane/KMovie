package com.magicalrice.adolph.kmovie.data.remote.services;

import com.magicalrice.adolph.kmovie.data.entities.AppendToResponse;
import com.magicalrice.adolph.kmovie.data.entities.Changes;
import com.magicalrice.adolph.kmovie.data.entities.Person;
import com.magicalrice.adolph.kmovie.data.entities.PersonMovieCredits;
import com.magicalrice.adolph.kmovie.data.entities.PersonExternalIds;
import com.magicalrice.adolph.kmovie.data.entities.PersonImages;
import com.magicalrice.adolph.kmovie.data.entities.PersonResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.PersonTvCredits;
import com.magicalrice.adolph.kmovie.data.entities.TaggedImagesResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.TmdbDate;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface PeopleService {

    /**
     * Get the general person information for a specific id.
     *
     * @param personId A Person TMDb id.
     */
    @GET("person/{person_id}")
    Flowable<Person> summary(
            @Path("person_id") long personId
    );

    /**
     * Get the general person information for a specific id.
     *
     * @param personId         A Person TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movie_credits, tv_credits, combined_credits, external_ids, images, changes, tagged_images,
     */
    @GET("person/{person_id}")
    Observable<Person> summary(
            @Path("person_id") int personId,
            @Query("append_to_response") AppendToResponse appendToResponse
    );

    /**
     * Get the general person information for a specific id.
     *
     * @param personId         A Person TMDb id.
     * @param appendToResponse <em>Optional.</em> extra requests to append to the result. <b>Accepted Value(s):</b> movie_credits, tv_credits, combined_credits, external_ids, images, changes, tagged_images,
     * @param options          <em>Optional.</em> parameters for the appended extra results.
     */
    @GET("person/{person_id}")
    Observable<Person> summary(
            @Path("person_id") int personId,
            @Query("append_to_response") AppendToResponse appendToResponse,
            @QueryMap Map<String, String> options
    );

    /**
     * Get the movie credits for a specific person id.
     *
     * @param personId A Person TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/movie_credits")
    Flowable<PersonMovieCredits> movieCredits(
            @Path("person_id") long personId,
            @Query("language") String language
    );

    /**
     * Get the TV credits for a specific person id.
     *
     * @param personId A Person TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/tv_credits")
    Flowable<PersonTvCredits> tvCredits(
            @Path("person_id") long personId,
            @Query("language") String language
    );

    /**
     * Get the movie and TV credits for a specific person id.
     *
     * @param personId A Person TMDb id.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/combined_credits")
    Observable<PersonMovieCredits> combinedCredits(
            @Path("person_id") int personId,
            @Query("language") String language
    );

    /**
     * Get the external ids for a specific person id.
     *
     * @param personId A Person TMDb id.
     */
    @GET("person/{person_id}/external_ids")
    Observable<PersonExternalIds> externalIds(
            @Path("person_id") int personId
    );

    /**
     * Get the images for a specific person id.
     */
    @GET("person/{person_id}/images")
    Flowable<PersonImages> images(
            @Path("person_id") long personId
    );

    /**
     * Get the changes for a person. By default only the last 24 hours are returned.
     * <p>
     * You can query up to 14 days in a single query by using the start_date and end_date query parameters.
     *
     * @param personId   A Person TMDb id.
     * @param start_date <em>Optional.</em> Starting date of changes occurred to a movie.
     * @param end_date   <em>Optional.</em> Ending date of changes occurred to a movie.
     * @param page       <em>Optional.</em> Minimum value is 1, maximum 1000, expected value is an integer.
     * @param language   <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/changes")
    Observable<Changes> changes(
            @Path("person_id") int personId,
            @Query("language") String language,
            @Query("start_date") TmdbDate start_date,
            @Query("end_date") TmdbDate end_date,
            @Query("page") Integer page
    );

    /**
     * Get the images that have been tagged with a specific person id. Return all of the image results with a {@link
     * com.magicalrice.adolph.kmovie.data.entities.Media} object mapped for each image.
     *
     * @param personId A Person TMDb id.
     * @param page     <em>Optional.</em> Minimum value is 1, maximum 1000, expected value is an integer.
     * @param language <em>Optional.</em> ISO 639-1 code.
     */
    @GET("person/{person_id}/tagged_images")
    Observable<TaggedImagesResultsPage> taggedImages(
            @Path("person_id") int personId,
            @Query("page") Integer page,
            @Query("language") String language
    );

    /**
     * Get the list of popular people on The Movie Database. This list refreshes every day.
     */
    @GET("person/popular")
    Observable<PersonResultsPage> popular(
            @Query("page") Integer page
    );

    /**
     * Get the latest person id.
     */
    @GET("person/latest")
    Observable<Person> latest();

}
