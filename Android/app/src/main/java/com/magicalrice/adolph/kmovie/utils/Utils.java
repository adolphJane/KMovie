package com.magicalrice.adolph.kmovie.utils;

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
}
