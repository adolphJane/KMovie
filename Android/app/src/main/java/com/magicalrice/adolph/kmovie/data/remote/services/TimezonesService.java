package com.magicalrice.adolph.kmovie.data.remote.services;

import com.magicalrice.adolph.kmovie.data.entities.Timezones;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TimezonesService {

    /**
     * Get the list of supported timezones on TMDb.
     */
    @GET("timezones/list")
    Call<Timezones> timezones();
}
