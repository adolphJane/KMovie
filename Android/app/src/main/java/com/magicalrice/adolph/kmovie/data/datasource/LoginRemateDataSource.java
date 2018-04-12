package com.magicalrice.adolph.kmovie.data.datasource;

import com.magicalrice.adolph.kmovie.data.entities.RequestToken;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.data.remote.services.AccountService;
import com.magicalrice.adolph.kmovie.data.remote.services.AuthenticationService;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by Adolph on 2018/4/12.
 */

public class LoginRemateDataSource implements LoginDataSource {
    @Inject
    Tmdb tmdb;

    @Override
    public void getBaseToken() {
        tmdb.authenticationService()
                .requestToken()
                .compose(RxUtils.io_main())
                .compose(RxUtils.handleResult())
                .subscribe();
    }

    @Override
    public void getValidateToken() {

    }

    @Override
    public void getTvSeasonSession() {

    }
}
