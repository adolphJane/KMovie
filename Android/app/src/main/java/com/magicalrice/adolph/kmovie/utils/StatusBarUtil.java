package com.magicalrice.adolph.kmovie.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Adolph on 2018/2/27.
 */

public class StatusBarUtil {

    public static void translucentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            translucentDown(activity);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            translucentUp(activity,false);
        }
    }

    private static void translucentDown(Activity activity) {
        Window window = activity.getWindow();
        //设置window透明
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ViewGroup contentView = activity.findViewById(Window.ID_ANDROID_CONTENT);
        View contentChild = contentView.getChildAt(0);

        if (contentChild != null) {
            contentChild.setFitsSystemWindows(false);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static void translucentUp(Activity activity, boolean hideStatusBarBackground) {
        Window window = activity.getWindow();
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (hideStatusBarBackground) {
            //如果为全透明，取消设置Window半透明的Flag
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置状态栏为透明
            window.setStatusBarColor(Color.TRANSPARENT);
            //设置window的状态栏不可见
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            //如果为半透明模式，添加设置window半透明的flag
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置系统状态栏处于可见模式
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }
        //view不根据系统窗口来调整自己的布局
        ViewGroup contentView = window.findViewById(Window.ID_ANDROID_CONTENT);
        View contentChild = contentView.getChildAt(0);
        if (contentChild != null) {
            contentChild.setFitsSystemWindows(false);
            contentChild.requestApplyInsets();
        }
    }
}
