package com.magicalrice.adolph.kmovie.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;

import com.magicalrice.adolph.kmovie.utils.LUtils;

/**
 * Created by Adolph on 2018/5/23.
 */

public class ScrollAwareBehavior extends FloatingActionButton.Behavior {
    private boolean mIsAnimatingOut = false;

    public ScrollAwareBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        if (dyConsumed > 0 && !this.mIsAnimatingOut && child.getVisibility() == View.VISIBLE) {
            animateOut(child);
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            animateIn(child);
        }
    }

    private void animateOut(final FloatingActionButton button) {
        ViewCompat.animate(button).scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setInterpolator(new FastOutSlowInInterpolator()).withLayer()
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        ScrollAwareBehavior.this.mIsAnimatingOut = true;
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        ScrollAwareBehavior.this.mIsAnimatingOut = false;
                        button.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        ScrollAwareBehavior.this.mIsAnimatingOut = false;
                    }
                }).start();
    }

    private void animateIn(FloatingActionButton button) {
        button.setVisibility(View.VISIBLE);
        ViewCompat.animate(button).scaleX(1.0f).scaleY(1.0f).alpha(1.0f)
                .setInterpolator(new FastOutSlowInInterpolator()).withLayer()
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        button.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(View view) {

                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                }).start();
    }
}
