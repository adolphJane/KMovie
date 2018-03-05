package com.magicalrice.adolph.kmovie.assist.dagger.component;

import com.magicalrice.adolph.kmovie.assist.dagger.module.ActivityBuilder;
import com.magicalrice.adolph.kmovie.assist.dagger.module.ApplicationModule;
import com.magicalrice.adolph.kmovie.base.MovieApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Adolph on 2018/2/26.
 */

@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class, ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<MovieApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MovieApplication application);
        AppComponent build();
    }
}
