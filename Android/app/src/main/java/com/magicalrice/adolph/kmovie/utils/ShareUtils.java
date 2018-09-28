package com.magicalrice.adolph.kmovie.utils;

public class ShareUtils {
    private static ShareUtils instance;

    private ShareUtils() {

    }

    public static ShareUtils getInstance() {
        if (instance == null) {
            synchronized (ShareUtils.class) {
                if (instance == null) {
                    instance = new ShareUtils();
                }
            }
        }
        return instance;
    }
}
