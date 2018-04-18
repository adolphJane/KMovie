package com.magicalrice.adolph.kmovie.data.datasource;

import com.magicalrice.adolph.kmovie.data.entities.RequestToken;

import io.reactivex.Observable;

/**
 * Created by Adolph on 2018/4/12.
 */

public interface LoginDataSource {
    public Observable<RequestToken> getBaseToken();
    public Observable<RequestToken> getValidateToken(String userName,String password);
    public void getTvSeasonSession();
}
