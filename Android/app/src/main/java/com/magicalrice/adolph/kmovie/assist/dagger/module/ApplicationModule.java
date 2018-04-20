package com.magicalrice.adolph.kmovie.assist.dagger.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Adolph on 2018/2/28.
 */
@Module
public abstract class ApplicationModule {
    @Binds
    abstract Context provideContext(Application application);
}
