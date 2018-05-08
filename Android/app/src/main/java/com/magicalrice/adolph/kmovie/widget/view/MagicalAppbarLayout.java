package com.magicalrice.adolph.kmovie.widget.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.magicalrice.adolph.kmovie.utils.LUtils;

/**
 * Created by Adolph on 2018/5/8.
 */

public class MagicalAppbarLayout extends AppBarLayout {
    float x1 = 0,y1 = 0;
    public MagicalAppbarLayout(Context context) {
        super(context);
    }

    public MagicalAppbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            x1 = ev.getX();
            y1 = ev.getY();
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (Math.abs(ev.getX() - x1) >= Math.abs(ev.getY() - y1)) {
                return false;
            } else {
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
