package com.magicalrice.adolph.kmovie.utils;

import android.content.Context;
import android.util.TypedValue;

import com.magicalrice.adolph.kmovie.base.MovieApplication;

/**
 * Created by Adolph on 2018/4/10.
 */

public class ScreenUtils {
    /**
     * 获取屏幕的宽度（单位：px）
     *
     * @return 屏幕宽
     */
    public static int getScreenWidth() {
        return MovieApplication.getInstance().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的高度（单位：px）
     *
     * @return 屏幕高
     */
    public static int getScreenHeight() {
        return MovieApplication.getInstance().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * dp 转 px
     * @param context
     * @param dpValue
     * @return
     */
    public static float dp2px(Context context, float dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpValue,context.getResources().getDisplayMetrics());
    }

    /**
     * sp 转 px
     * @param context
     * @param dpValue
     * @return
     */
    public static float sp2px(Context context,float dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,dpValue,context.getResources().getDisplayMetrics());
    }
}
