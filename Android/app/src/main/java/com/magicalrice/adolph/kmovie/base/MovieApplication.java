package com.magicalrice.adolph.kmovie.base;

import com.magicalrice.adolph.kmovie.BuildConfig;
import com.magicalrice.adolph.kmovie.assist.MyLifecycle;
import com.magicalrice.adolph.kmovie.assist.dagger.component.AppComponent;
import com.magicalrice.adolph.kmovie.assist.dagger.component.DaggerAppComponent;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by Adolph on 2018/2/26.
 */

public class MovieApplication extends DaggerApplication {
    private static MovieApplication instance;
    private static boolean hasToken;

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

//    private void initBugly() {
//        UserStrategy strategy = new UserStrategy(getApplicationContext());
//        strategy.setAppChannel("Main");
//        strategy.setAppVersion(BuildConfig.VERSION_NAME);
//        strategy.setAppPackageName(BuildConfig.APPLICATION_ID);
//        CrashReport.initCrashReport(getApplicationContext(), "d820f8b302", false,strategy);
//    }

    public static MovieApplication getInstance() {
        return instance;
    }

    public static boolean isHasToken() {
        return hasToken;
    }

    public static void setHasToken(boolean hasToken) {
        MovieApplication.hasToken = hasToken;
    }
}
