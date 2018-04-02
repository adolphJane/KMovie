package com.magicalrice.adolph.kmovie.assist.dagger.component;

import android.app.Application;

import com.magicalrice.adolph.kmovie.assist.dagger.module.ActivityBuilderModule;
import com.magicalrice.adolph.kmovie.assist.dagger.module.ApplicationModule;
import com.magicalrice.adolph.kmovie.base.MovieApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created by Adolph on 2018/2/26.
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class, ActivityBuilderModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    @Override
    void inject(DaggerApplication instance);

    void inject(MovieApplication application);
}
