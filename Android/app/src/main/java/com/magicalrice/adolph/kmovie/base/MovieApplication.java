package com.magicalrice.adolph.kmovie.base;

import android.support.v4.app.Fragment;

import com.magicalrice.adolph.kmovie.assist.MyLifecycle;
import com.magicalrice.adolph.kmovie.assist.dagger.component.AppComponent;
import com.magicalrice.adolph.kmovie.assist.dagger.component.DaggerAppComponent;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;

/**
 * Created by Adolph on 2018/2/26.
 */

public class MovieApplication extends DaggerApplication {
    private static MovieApplication instance;
    @Inject
    DispatchingAndroidInjector<Fragment> injector;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MyLifecycle.init(this);
        initLogger();
        initLeakCanary();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    private void initLogger() {
        FormatStrategy strategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(5)
                .methodOffset(6)
                .tag("AndroidTest:")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(strategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });
    }

    private void initLeakCanary() {
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }
}
