package com.magicalrice.adolph.kmovie.data.remote;

/**
 * Created by Adolph on 2018/2/26.
 */

public class ApiConstants {
    public static final int TIME_IN_SEC = 10;
    /**
     * API Related Information
     */
    public static final String API_HOST = "api.themoviedb.org";
    public static final String API_VERSION = "3";
    public static final String API_URL = "https://" + API_HOST + "/" + API_VERSION + "/";
    public static final String API_KEY = "74a1fd59a732270cc2e2b6753c3cd7aa";

    /**
     * API key, Session and Guest Session query parameter names.
     */
    public static final String PARAM_API_KEY = "api_key";
    public static final String PARAM_SESSION_ID = "session_id";
    public static final String PARAM_GUEST_SESSION_ID = "guest_session_id";

    /**
     * Authentication and Guest Session end point paths.
     */
    public static final String PATH_AUTHENTICATION = "authentication";

    public static final String TMDB_DATE_PATTERN = "yyyy-MM-dd";
}
