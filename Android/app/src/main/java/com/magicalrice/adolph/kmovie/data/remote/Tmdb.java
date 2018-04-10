package com.magicalrice.adolph.kmovie.data.remote;

import com.magicalrice.adolph.kmovie.data.entities.Status;
import com.magicalrice.adolph.kmovie.data.remote.exceptions.TmdbAuthenticationFailedException;
import com.magicalrice.adolph.kmovie.data.remote.exceptions.TmdbDuplicateEntryException;
import com.magicalrice.adolph.kmovie.data.remote.exceptions.TmdbException;
import com.magicalrice.adolph.kmovie.data.remote.exceptions.TmdbInvalidParametersException;
import com.magicalrice.adolph.kmovie.data.remote.exceptions.TmdbNotFoundException;
import com.magicalrice.adolph.kmovie.data.remote.exceptions.TmdbServiceErrorException;
import com.magicalrice.adolph.kmovie.data.remote.services.AccountService;
import com.magicalrice.adolph.kmovie.data.remote.services.AuthenticationService;
import com.magicalrice.adolph.kmovie.data.remote.services.CertificationsService;
import com.magicalrice.adolph.kmovie.data.remote.services.ChangesService;
import com.magicalrice.adolph.kmovie.data.remote.services.CollectionsService;
import com.magicalrice.adolph.kmovie.data.remote.services.CompaniesService;
import com.magicalrice.adolph.kmovie.data.remote.services.ConfigurationService;
import com.magicalrice.adolph.kmovie.data.remote.services.CreditsService;
import com.magicalrice.adolph.kmovie.data.remote.services.DiscoverService;
import com.magicalrice.adolph.kmovie.data.remote.services.FindService;
import com.magicalrice.adolph.kmovie.data.remote.services.GenresService;
import com.magicalrice.adolph.kmovie.data.remote.services.GuestSessionService;
import com.magicalrice.adolph.kmovie.data.remote.services.KeywordsService;
import com.magicalrice.adolph.kmovie.data.remote.services.ListsService;
import com.magicalrice.adolph.kmovie.data.remote.services.MoviesService;
import com.magicalrice.adolph.kmovie.data.remote.services.NetworksService;
import com.magicalrice.adolph.kmovie.data.remote.services.PeopleService;
import com.magicalrice.adolph.kmovie.data.remote.services.ReviewsService;
import com.magicalrice.adolph.kmovie.data.remote.services.SearchService;
import com.magicalrice.adolph.kmovie.data.remote.services.TimezonesService;
import com.magicalrice.adolph.kmovie.data.remote.services.TvEpisodesService;
import com.magicalrice.adolph.kmovie.data.remote.services.TvSeasonsService;
import com.magicalrice.adolph.kmovie.data.remote.services.TvService;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Helper class for easy usage of the TMDB v3 API using retrofit.
 * <p>
 * <p>Create an instance of this class and then call any of the service methods.
 * <p>
 * <p>The service methods take care of constructing the required {@link Retrofit} instance and creating the service. It
 * is recommended you use your existing OkHttp client instance by overriding {@link #okHttpClient()}.
 * <p>
 * <p>Only one {@link Retrofit} instance is created upon the first and re-used for any consequent service method call.
 */
public class Tmdb {

    /**
     * API Related Information
     */
    public static final String API_HOST = "api.themoviedb.org";
    public static final String API_VERSION = "3";
    public static final String API_URL = "https://" + API_HOST + "/" + API_VERSION + "/";

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

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    private String apiKey;

    private Boolean hasGuestSession = false;
    private Boolean hasAccountSession = false;

    Boolean isLoggedIn = false;

    String username;
    String password;
    String sessionId;
    String guestSessionId;

    /**
     * Create a new manager instance.
     *
     * @param apiKey Your TMDB API key.
     */
    @Inject
    public Tmdb(String apiKey) {
        this.apiKey = apiKey;
    }

    public void accountSession(String username, String password) throws TmdbInvalidParametersException {
        if (username == null || password == null) {
            throw new TmdbInvalidParametersException(401, "Username and Password may not be null");
        }

        this.username = username;
        this.password = password;
        hasAccountSession = true;
    }

    public void guestSession() {
        hasGuestSession = true;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getGuestSessionId() {
        return guestSessionId;
    }

    public Boolean isLoggedIn() {
        return isLoggedIn;
    }

    public Boolean hasGuestSession() {
        return hasGuestSession;
    }

    public Boolean hasAccountSession() {
        return hasAccountSession;
    }

    public void apiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String apiKey() {
        return apiKey;
    }

    /**
     * Creates a {@link Retrofit.Builder} that sets the base URL, adds a Gson converter and sets {@link #okHttpClient()}
     * as its client.
     *
     * @see #okHttpClient()
     */
    protected Retrofit.Builder retrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(TmdbHelper.getGsonBuilder().create()))
                .client(okHttpClient());
    }


    /**
     * Returns the default OkHttp client instance. It is strongly recommended to override this and use your app
     * instance.
     *
     * @see #setOkHttpClientDefaults(OkHttpClient.Builder)
     */
    protected synchronized OkHttpClient okHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            setOkHttpClientDefaults(builder);
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    /**
     * Adds an interceptor to add the api key query parameter and to log requests.
     */
    protected void setOkHttpClientDefaults(OkHttpClient.Builder builder) {
        builder.addInterceptor(new TmdbInterceptor(this)).authenticator(new TmdbAuthenticator(this));
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
    }

    /**
     * Return the current {@link Retrofit} instance. If none exists (first call, auth changed), builds a new one.
     * <p>When building, sets the base url and a custom client with an {@link Interceptor} which supplies authentication
     * data.
     */
    protected Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = retrofitBuilder().build();
        }
        return retrofit;
    }

    /**
     * Throws a {@link TmdbException} if the given unsuccessful response contains a
     * known TMDB status code.
     *
     * @see <a href="https://www.themoviedb.org/documentation/api/status-codes">Status Codes</a>
     */
    public void throwOnKnownError(Response response) throws IOException {
        if (response.isSuccessful()) {
            return;
        }

        ResponseBody responseBody = response.errorBody();
        if (responseBody == null) {
            return;
        }

        Status status = (Status) retrofit
                .responseBodyConverter(Status.class, Status.class.getAnnotations())
                .convert(responseBody);

        Integer code = status.status_code;
        String message = status.status_message;
        switch (code) {
            case 2:
            case 4:
            case 9:
            case 11:
            case 15:
            case 16:
            case 19:
            case 24:
                throw new TmdbServiceErrorException(code, message);
            case 3:
            case 14:
            case 33:
            case 7:
            case 10:
            case 17:
            case 18:
            case 26:
            case 30:
            case 31:
            case 32:
                throw new TmdbAuthenticationFailedException(code, message);
            case 5:
            case 20:
            case 22:
            case 23:
            case 27:
            case 28:
                throw new TmdbInvalidParametersException(code, message);
            case 6:
            case 34:
                throw new TmdbNotFoundException(code, message);
            case 8:
                throw new TmdbDuplicateEntryException(code, message);
        }
    }

    public AccountService accountService() {
        return getRetrofit().create(AccountService.class);
    }

    public AuthenticationService authenticationService() {
        return getRetrofit().create(AuthenticationService.class);
    }

    public CertificationsService certificationsService() {
        return getRetrofit().create(CertificationsService.class);
    }

    public ChangesService changesService() {
        return getRetrofit().create(ChangesService.class);
    }

    public CollectionsService collectionService() {
        return getRetrofit().create(CollectionsService.class);
    }

    public CompaniesService companiesService() {
        return getRetrofit().create(CompaniesService.class);
    }

    public ConfigurationService configurationService() {
        return getRetrofit().create(ConfigurationService.class);
    }

    public CreditsService creditsService() {
        return getRetrofit().create(CreditsService.class);
    }

    public DiscoverService discoverService() {
        return getRetrofit().create(DiscoverService.class);
    }

    public FindService findService() {
        return getRetrofit().create(FindService.class);
    }

    public GenresService genreService() {
        return getRetrofit().create(GenresService.class);
    }

    public GuestSessionService guestSessionService() {
        return getRetrofit().create(GuestSessionService.class);
    }

    public KeywordsService keywordsService() {
        return getRetrofit().create(KeywordsService.class);
    }

    public ListsService listsService() {
        return getRetrofit().create(ListsService.class);
    }

    public MoviesService moviesService() {
        return getRetrofit().create(MoviesService.class);
    }

    public NetworksService networksService() {
        return getRetrofit().create(NetworksService.class);
    }

    public PeopleService personService() {
        return getRetrofit().create(PeopleService.class);
    }

    public ReviewsService reviewsService() {
        return getRetrofit().create(ReviewsService.class);
    }

    public SearchService searchService() {
        return getRetrofit().create(SearchService.class);
    }

    public TimezonesService timezonesService() {
        return getRetrofit().create(TimezonesService.class);
    }

    public TvService tvService() {
        return getRetrofit().create(TvService.class);
    }

    public TvSeasonsService tvSeasonsService() {
        return getRetrofit().create(TvSeasonsService.class);
    }

    public TvEpisodesService tvEpisodesService() {
        return getRetrofit().create(TvEpisodesService.class);
    }


    public DiscoverMovieBuilder discoverMovie() {
        return new DiscoverMovieBuilder(discoverService());
    }

    public DiscoverTvBuilder discoverTv() {
        return new DiscoverTvBuilder(discoverService());
    }

}