package com.magicalrice.adolph.kmovie.assist;

import android.app.Application;

import com.magicalrice.adolph.kmovie.MovieApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Adolph on 2018/2/26.
 */

@Component(modules = {AppModule.class, AndroidInjectionModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(MovieApplication application);
}
