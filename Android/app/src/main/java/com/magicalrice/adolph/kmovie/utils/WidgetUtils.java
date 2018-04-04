package com.magicalrice.adolph.kmovie.utils;

import android.support.design.widget.TextInputLayout;

/**
 * Created by Adolph on 2018/4/3.
 */
public class WidgetUtils {

    /**
     * TextInputLayout 显示错误提示,并获取焦点
     * @param layout
     * @param error
     */
    public static void showTextInputError(TextInputLayout layout, String error) {
        layout.setError(error);
        layout.getEditText().setFocusable(true);
        layout.getEditText().setFocusableInTouchMode(true);
        layout.getEditText().requestFocus();
    }
}
