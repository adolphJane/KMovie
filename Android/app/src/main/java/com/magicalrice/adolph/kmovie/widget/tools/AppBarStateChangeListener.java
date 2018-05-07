package com.magicalrice.adolph.kmovie.widget.tools;

import android.support.annotation.IntDef;
import android.support.design.widget.AppBarLayout;
import android.view.View;

/**
 * Created by Adolph on 2018/5/7.
 */

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {
    public static final int IDLE_UP = 1001;
    public static final int IDLE_DOWN = 1002;
    public static final int EXPANDED = 2001;
    public static final int COLLAPSED = 3001;
    @State
    private int currentState = EXPANDED;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {
            if (currentState != EXPANDED) {
                onStateChanged(appBarLayout, EXPANDED);
            }
            currentState = EXPANDED;
        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            if (currentState != COLLAPSED) {
                onStateChanged(appBarLayout, COLLAPSED);
            }
            currentState = COLLAPSED;
        } else {
            if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange() / 2) {
                if (currentState != IDLE_UP) {
                    onStateChanged(appBarLayout, IDLE_UP);
                }
                currentState = IDLE_UP;
            } else {
                if (currentState != IDLE_DOWN) {
                    onStateChanged(appBarLayout, IDLE_DOWN);
                }
                currentState = IDLE_DOWN;
            }
        }
    }

    @IntDef({IDLE_UP,IDLE_DOWN, EXPANDED, COLLAPSED})
    public @interface State {
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, @State int state);
}
