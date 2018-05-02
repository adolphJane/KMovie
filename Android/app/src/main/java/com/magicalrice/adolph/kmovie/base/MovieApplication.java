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
import com.sw.debug.view.DebugViewWrapper;

import cn.jpush.im.android.api.JMessageClient;
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
        initJMessage();
        initDebugView();
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

    private void initJMessage() {
        if (BuildConfig.DEBUG) {
            JMessageClient.setDebugMode(true);
        }
        JMessageClient.init(getApplicationContext(), true);
    }

    private void initDebugView() {
        DebugViewWrapper.Companion.getInstance().init(
                new DebugViewWrapper.Builder(this)
                        .viewWidth(250)
                        .bgColor(0x6f677700)
                        .alwaysShowOverlaySetting(true)
                        .logMaxLines(20)
        );

        DebugViewWrapper.Companion.getInstance().show();
    }


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
