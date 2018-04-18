package com.magicalrice.adolph.kmovie.data.remote.services;

import com.magicalrice.adolph.kmovie.data.entities.GuestSession;
import com.magicalrice.adolph.kmovie.data.entities.RequestToken;
import com.magicalrice.adolph.kmovie.data.entities.Session;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AuthenticationService {

    /**
     * Requests authentication Token.
     */
    @GET(ApiConstants.PATH_AUTHENTICATION + "/token/new")
    Observable<RequestToken> requestToken();

    /**
     * Attempts to Login with a Request Token and Username/Password.
     *
     * @param username      Username of TMDb Account.
     * @param password      Password of TMDb Account.
     * @param request_token The Token you requested.
     */
    @GET(ApiConstants.PATH_AUTHENTICATION + "/token/validate_with_login")
    Observable<RequestToken> validateToken(
            @Query("username") String username,
            @Query("password") String password,
            @Query("request_token") String request_token
    );

    /**
     * Creates TvSeason with the Request Token you validated with your username/password.
     *
     * @param request_token The Token you requested.
     */
    @GET(ApiConstants.PATH_AUTHENTICATION + "/session/new")
    Observable<Session> createSession(
            @Query("request_token") String request_token
    );

    /**
     * Creates Guest TvSeason
     */
    @GET(ApiConstants.PATH_AUTHENTICATION + "/guest_session/new")
    Observable<GuestSession> createGuestSession();
}
