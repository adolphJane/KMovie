package com.magicalrice.adolph.kmovie.utils;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import com.magicalrice.adolph.kmovie.R;

/**
 * Created by Adolph on 2018/5/18.
 */

public class Utils {

    public static String getTime(int time) {
        int h = time / 60;
        int min = time % 60;
        String newTime = h == 0 ? min + "m" : h + "h" + min + "m";
        return newTime;
    }

    public static String getRevenue(int money) {
        String tempMoney = "";
        while (money / 1000 > 0) {
            int temp = money % 1000;
            tempMoney += "," + fixZero(money % 1000,3);
            money /= 1000;
        }
        tempMoney = "$" + (money % 1000) + tempMoney;
        return tempMoney;
    }

    public static String fixZero(int money,int size) {
        String mon = "";
        for (int i = size;i > 0;i--) {
            if (money / (i * 10) > 0) {
                mon += money % (i * 10);
            } else if (money / (i * 10) == 0) {
                mon += 0;
            }
        }
        return mon;
    }

    public static CharSequence flipTextColor(Context context, String content, String separtor, @ColorRes int color) {
        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(separtor)) {
            return "";
        }
        if (content.contains(separtor)) {
            SpannableString ss = new SpannableString(content);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(context, color));
            int end = content.indexOf(separtor);
            ss.setSpan(colorSpan, 0, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ss;
        }
        return content;
    }
}
