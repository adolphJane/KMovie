package com.magicalrice.adolph.kmovie.data.remote;

import com.magicalrice.adolph.kmovie.data.entities.GuestSession;
import com.magicalrice.adolph.kmovie.data.entities.RequestToken;
import com.magicalrice.adolph.kmovie.data.entities.Session;
import com.magicalrice.adolph.kmovie.data.enumerations.AuthenticationType;
import com.magicalrice.adolph.kmovie.data.remote.exceptions.TmdbAuthenticationFailedException;
import com.magicalrice.adolph.kmovie.data.remote.services.AuthenticationService;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class TmdbAuthenticator implements Authenticator {

    private final Tmdb tmdb;

    public TmdbAuthenticator(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        return handleRequest(response, tmdb);
    }


    public static Request handleRequest(Response response, Tmdb tmdb) throws IOException {
        if (response.request().url().pathSegments().get(0).equals(ApiConstants.PATH_AUTHENTICATION)) {
            return null;
        }

        if (responseCount(response) >= 2) {
            throw new TmdbAuthenticationFailedException(30,
                    "Authentication failed: You do not have permissions to access the service.");
        }

        HttpUrl.Builder urlBuilder = response.request().url().newBuilder();

        AuthenticationType type = RequestInterceptor.determineAuthenticationType(urlBuilder, tmdb);

        if (tmdb.hasAccountSession() && type == AuthenticationType.ACCOUNT) {
            if (tmdb.getUsername() == null || tmdb.getPassword() == null) {
                throw new TmdbAuthenticationFailedException(26, "You must provide a username and password.");
            }
            acquireAccountSession(tmdb);
            urlBuilder.setEncodedQueryParameter(ApiConstants.PARAM_SESSION_ID, tmdb.getSessionId());
        } else if (tmdb.hasGuestSession() && type == AuthenticationType.GUEST) {
            acquireGuestSession(tmdb);
            urlBuilder.setEncodedQueryParameter(ApiConstants.PARAM_GUEST_SESSION_ID, tmdb.getGuestSessionId());
        } else {
            throw new TmdbAuthenticationFailedException(30,
                    "Authentication failed: You do not have permissions to access the service.");
        }

        return response.request().newBuilder().url(urlBuilder.build()).build();
    }


    public static void acquireAccountSession(Tmdb tmdb) throws IOException {
        AuthenticationService authService = tmdb.getRetrofit().create(AuthenticationService.class);

        RequestToken token = authService.requestToken().execute().body();

        token = authService.validateToken(tmdb.getUsername(), tmdb.getPassword(), token.request_token).execute().body();

        Session session = authService.createSession(token.request_token).execute().body();

        tmdb.setSessionId(session.session_id);
        tmdb.isLoggedIn = true;

    }

    public static void acquireGuestSession(Tmdb tmdb) throws IOException {
        AuthenticationService authService = tmdb.getRetrofit().create(AuthenticationService.class);
        GuestSession session = authService.createGuestSession().execute().body();

        tmdb.setGuestSessionId(session.guest_session_id);
        tmdb.isLoggedIn = true;

    }

    private static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
