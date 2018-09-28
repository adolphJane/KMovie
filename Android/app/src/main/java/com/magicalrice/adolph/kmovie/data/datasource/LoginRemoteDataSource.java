package com.magicalrice.adolph.kmovie.data.datasource;

import android.content.Context;

import com.magicalrice.adolph.kmovie.base.MovieApplication;
import com.magicalrice.adolph.kmovie.data.entities.RequestToken;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.utils.RxUtils;
import com.magicalrice.adolph.kmovie.utils.SpUtils;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Adolph on 2018/4/12.
 */

public class LoginRemoteDataSource implements LoginDataSource {
    Tmdb tmdb;
    @Inject
    Context context;

    @Inject
    public LoginRemoteDataSource(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    public Observable<RequestToken> getBaseToken() {
        return tmdb.authenticationService()
                .requestToken()
                .compose(RxUtils.io_main_o());
    }

    @Override
    public Observable<RequestToken> getValidateToken(String userName, String password) {
        return tmdb.authenticationService()
                .requestToken()
                .flatMap(requestToken -> {
                    SpUtils.getInstance(context).put("base_token", requestToken.getRequest_token());
                    MovieApplication.getInstance().setHasToken(true);
                    return tmdb.authenticationService()
                            .validateToken(userName, password, requestToken.getRequest_token());
                })
                .compose(RxUtils.io_main_o());
    }

    @Override
    public void getTvSeasonSession() {

    }
}
