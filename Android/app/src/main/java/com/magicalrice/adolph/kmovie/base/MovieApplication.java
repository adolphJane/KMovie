package com.magicalrice.adolph.kmovie;

import android.app.Activity;
import android.app.Application;

import com.magicalrice.adolph.kmovie.assist.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Adolph on 2018/2/26.
 */

public class MovieApplication extends Application implements HasActivityInjector{

    @Inject
    DispatchingAndroidInjector<Activity> injector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    private void initializeComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return injector;
    }
}
