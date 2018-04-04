package com.magicalrice.adolph.kmovie.data.remote.services;

import com.magicalrice.adolph.kmovie.data.entities.Certifications;

import retrofit2.Call;
import retrofit2.http.GET;


public interface CertificationsService {
    /**
     * Get the Certifications for Movies.
     */
    @GET("certification/movie/list")
    Call<Certifications> movie();

    /**
     * Get the Certifications for TV Shows.
     */
    @GET("certification/tv/list")
    Call<Certifications> tv();
}
