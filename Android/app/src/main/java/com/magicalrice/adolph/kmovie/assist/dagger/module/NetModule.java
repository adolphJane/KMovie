package com.magicalrice.adolph.kmovie.assist.dagger.module;

import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Adolph on 2018/2/28.
 */
@Module
public class NetModule {

    @Singleton
    @Provides
    Tmdb provideTmde() {
        Tmdb tmdb = new Tmdb(ApiConstants.API_KEY);
        return tmdb;
    }
}
