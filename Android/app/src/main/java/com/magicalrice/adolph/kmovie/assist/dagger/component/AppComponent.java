package com.magicalrice.adolph.kmovie.assist.dagger.component;

import android.app.Application;

import com.magicalrice.adolph.kmovie.assist.dagger.module.ActivityBuilderModule;
import com.magicalrice.adolph.kmovie.assist.dagger.module.ApplicationModule;
import com.magicalrice.adolph.kmovie.assist.dagger.module.FragmentBuilderModule;
import com.magicalrice.adolph.kmovie.assist.dagger.module.UtilsModule;
import com.magicalrice.adolph.kmovie.assist.dagger.module.ViewModuleFactoryModule;
import com.magicalrice.adolph.kmovie.base.MovieApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Adolph on 2018/2/26.
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class,UtilsModule.class,ActivityBuilderModule.class, FragmentBuilderModule.class, ViewModuleFactoryModule.class})
public interface AppComponent extends AndroidInjector<MovieApplication>{

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
