package com.magicalrice.adolph.kmovie.assist;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Adolph on 2018/2/27.
 */

public class MyLifecycle implements Application.ActivityLifecycleCallbacks {

    private boolean isForeground = false;
    private static MyLifecycle instance;
    private List<ForegroundListener> listeners = new CopyOnWriteArrayList<>();
    static WeakReference<Activity> mTopActivityWeakRef;
    static List<Activity> mActivityList = new LinkedList<>();
    private int resumeCount = 0;

    public static MyLifecycle init(Application application) {
        if (instance == null) {
            instance = new MyLifecycle();
            application.registerActivityLifecycleCallbacks(instance);
        }
        return instance;
    }

    public static MyLifecycle get(Application application) {
        if (instance == null) {
            init(application);
        }
        return instance;
    }

    public boolean isForeground() {
        return isForeground;
    }

    public void addListener(ForegroundListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ForegroundListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivityList.add(activity);
        setTopActivityWeakRef(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        setTopActivityWeakRef(activity);
        if (resumeCount == 0) {
            Logger.e("应用切到前台");
            for (ForegroundListener listener : listeners) {
                listener.onBecomeForeground();
            }
        }
        resumeCount++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        setTopActivityWeakRef(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        resumeCount--;
        if (resumeCount == 0) {
            Logger.e("应用切到后台");
            for (ForegroundListener listener : listeners) {
                listener.onBecomeBackground();
            }
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivityList.remove(activity);
    }

    private static void setTopActivityWeakRef(Activity activity) {
        if (mTopActivityWeakRef == null || !activity.equals(mTopActivityWeakRef.get())) {
            mTopActivityWeakRef = new WeakReference<>(activity);
        }
    }
}
