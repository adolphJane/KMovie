package com.magicalrice.adolph.kmovie.data.remote;

import com.magicalrice.adolph.kmovie.data.entities.Status;
import com.magicalrice.adolph.kmovie.data.remote.exceptions.TmdbAuthenticationFailedException;
import com.magicalrice.adolph.kmovie.data.remote.exceptions.TmdbDuplicateEntryException;
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

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by Adolph on 2018/4/4.
 */
public class Tmdb {
    private boolean hasGuestSession = false;
    private boolean hasAccountSession = false;
    boolean isLoggedIn = false;

    private String username;
    private String password;
    private String sessionId;
    private String guestSessionId;
    private Retrofit retrofit;

    public Tmdb(Retrofit retrofit) {
        this.retrofit = retrofit;
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

    public void throwOnKnownError(Response response) throws IOException {
        if (response.isSuccessful()) {
            return;
        }
        ResponseBody responseBody = response.errorBody();
        if (responseBody == null) {
            return;
        }

        Status status = (Status) retrofit.responseBodyConverter(Status.class, Status.class.getAnnotations())
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
            case 7:
            case 10:
            case 14:
            case 17:
            case 18:
            case 26:
            case 30:
            case 31:
            case 32:
            case 33:
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
                throw new TmdbDuplicateEntryException(code,message);
        }
    }

    public AccountService accountService() {
        return retrofit.create(AccountService.class);
    }

    public AuthenticationService authenticationService() {
        return retrofit.create(AuthenticationService.class);
    }

    public CertificationsService certificationsService() {
        return retrofit.create(CertificationsService.class);
    }

    public ChangesService changesService() {
        return retrofit.create(ChangesService.class);
    }

    public CollectionsService collectionService() {
        return retrofit.create(CollectionsService.class);
    }

    public CompaniesService companiesService() {
        return retrofit.create(CompaniesService.class);
    }

    public ConfigurationService configurationService() {
        return retrofit.create(ConfigurationService.class);
    }

    public CreditsService creditsService() {
        return retrofit.create(CreditsService.class);
    }

    public DiscoverService discoverService() {
        return retrofit.create(DiscoverService.class);
    }

    public FindService findService() {
        return retrofit.create(FindService.class);
    }

    public GenresService genreService() {
        return retrofit.create(GenresService.class);
    }

    public GuestSessionService guestSessionService() {
        return retrofit.create(GuestSessionService.class);
    }

    public KeywordsService keywordsService() {
        return retrofit.create(KeywordsService.class);
    }

    public ListsService listsService() {
        return retrofit.create(ListsService.class);
    }

    public MoviesService moviesService() {
        return retrofit.create(MoviesService.class);
    }

    public NetworksService networksService() {
        return retrofit.create(NetworksService.class);
    }

    public PeopleService personService() {
        return retrofit.create(PeopleService.class);
    }

    public ReviewsService reviewsService() {
        return retrofit.create(ReviewsService.class);
    }

    public SearchService searchService() {
        return retrofit.create(SearchService.class);
    }

    public TimezonesService timezonesService() {
        return retrofit.create(TimezonesService.class);
    }

    public TvService tvService() {
        return retrofit.create(TvService.class);
    }

    public TvSeasonsService tvSeasonsService() {
        return retrofit.create(TvSeasonsService.class);
    }

    public TvEpisodesService tvEpisodesService() {
        return retrofit.create(TvEpisodesService.class);
    }


    public DiscoverMovieBuilder discoverMovie() {
        return new DiscoverMovieBuilder(discoverService());
    }

    public DiscoverTvBuilder discoverTv() {
        return new DiscoverTvBuilder(discoverService());
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setGuestSessionId(String guestSessionId) {
        this.guestSessionId = guestSessionId;
    }
}
