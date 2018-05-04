package com.magicalrice.adolph.kmovie.widget.tab_icon;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magicalrice.adolph.kmovie.R;

/**
 * Created by Adolph on 2018/4/19.
 */

public class TabIconLayout extends RelativeLayout {
    private ImageView imageView;
    private AnimatorSet animatorSet;
    private TextView textView;
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
        textView = (TextView) getChildAt(1);
        if (imageView != null && textView != null) {
            if (selected) {
                imageView.setSelected(true);
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.yellow_1));
                textView.setAlpha(1f);
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
                textView.setTextColor(Color.WHITE);
                textView.setAlpha(0.4f);
                if (animatorSet != null && animatorSet.isRunning()) {
                    animatorSet.cancel();
                    imageView.setScaleX(1f);
                    imageView.setScaleY(1f);
                }
            }
        }
    }
}
