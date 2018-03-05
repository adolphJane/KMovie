package com.magicalrice.adolph.kmovie.assist.dagger.module;

import com.magicalrice.adolph.kmovie.base.MovieApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Adolph on 2018/2/28.
 */
@Module
public class AppModule {
    @Singleton
    @Provides
    MovieApplication provideContext(MovieApplication application) {
        return application;
    }
}
