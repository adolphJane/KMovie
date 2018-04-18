package com.magicalrice.adolph.kmovie.assist.dagger.module;

import android.app.Application;
import android.content.Context;

import com.magicalrice.adolph.kmovie.data.datasource.LoginRemoteDataSource;
import com.magicalrice.adolph.kmovie.viewmodule.ViewModuleFactory;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Adolph on 2018/2/28.
 */
@Module
public abstract class ApplicationModule {
    @Binds
    abstract Context provideContext(Application application);
}
