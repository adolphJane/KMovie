package com.magicalrice.adolph.kmovie.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Adolph on 2018/4/19.
 */

public class TabIconLayout extends RelativeLayout {
    private ImageView imageView;
    private AnimatorSet animatorSet;
    public TabIconLayout(Context context) {
        super(context);
    }

    public TabIconLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabIconLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        imageView = (ImageView) getChildAt(0);
        if (selected) {
            imageView.setSelected(true);
            if (animatorSet == null) {
                animatorSet = new AnimatorSet();
                ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageView,"scaleX",1,1.4f,1);
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView,"scaleY",1,1.4f,1);
                animatorSet.playTogether(animatorX,animatorY);
                animatorSet.setInterpolator(new BounceInterpolator());
                animatorSet.setDuration(500);
            }
            if (animatorSet != null && !animatorSet.isRunning()) {
                animatorSet.start();
            }
        } else {
            imageView.setSelected(false);
            if (animatorSet != null && animatorSet.isRunning()) {
                animatorSet.cancel();
                imageView.setScaleX(1f);
                imageView.setScaleY(1f);
            }
        }

    }
}
