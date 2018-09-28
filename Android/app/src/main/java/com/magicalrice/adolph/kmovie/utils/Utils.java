package com.magicalrice.adolph.kmovie.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.Toast;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.data.entities.AppInfo;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static String getRevenue(long money) {
        String tempMoney = "";
        while (money / 1000 > 0) {
            long temp = money % 1000;
            tempMoney += "," + fixZero(money % 1000,3);
            money /= 1000;
        }
        tempMoney = "$" + (money % 1000) + tempMoney;
        return tempMoney;
    }

    public static String fixZero(long money,int size) {
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

    public static String getDateTime(String format, Date date) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        }
        return "";
    }

    public static ArrayList<AppInfo> getShareAppList(Context context, Intent intent) {
        ArrayList<AppInfo> shareAppInfos = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfoList = getShareApps(context,intent);
        if (null == resolveInfoList) {
            return null;
        } else {
            for (ResolveInfo info : resolveInfoList) {
                AppInfo appInfo = new AppInfo();
                appInfo.setPkgName(info.activityInfo.packageName);
                appInfo.setLaunchClassName(info.activityInfo.name);
                appInfo.setAppName(info.loadLabel(packageManager).toString());
                appInfo.setAppIcon(info.loadIcon(packageManager));
                shareAppInfos.add(appInfo);
            }
        }
        return shareAppInfos;
    }

    public static List<ResolveInfo> getShareApps(Context context,Intent intent) {
        List<ResolveInfo> resolveInfoList;
        PackageManager pm = context.getPackageManager();
        resolveInfoList = pm.queryIntentActivities(intent,PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        return resolveInfoList;
    }

    public static void shareMsg(AppInfo info,String movieName,String moviePoster,Activity activity) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setComponent(new ComponentName(info.getPkgName(), info.getLaunchClassName()));

        if (moviePoster.equals("empty")) {
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "KMovie");
            intent.putExtra(Intent.EXTRA_TEXT, movieName + "最新咨询等你来看");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        } else {
            RequestManager manager = GlideApp.with(activity);
            RequestBuilder<File> builder = manager.downloadOnly();
            builder.load(ApiConstants.TMDB_IMAGE_PATH + "w400" + moviePoster);
            builder.listener(new RequestListener<File>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                    Toast.makeText(activity, "图片加载失败", Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                    try {
                        if (resource != null && resource.exists() && resource.isFile()) {
                            FileInputStream is = new FileInputStream(resource);
                            Bitmap bit = BitmapFactory.decodeStream(is);
                            String name = movieName.length() > 15 ? movieName.substring(0, 15) : movieName;
                            bit = BitmapUtils.drawShareBitmap(activity, bit, name, "KMovie", "最新电影咨询等你来看");
                            File file = new File(activity.getExternalCacheDir() + File.separator + "share.png");
                            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                            bit.compress(Bitmap.CompressFormat.PNG, 100, bos);
                            intent.setType("image/jpg");
                            Uri u = Uri.fromFile(file);
                            intent.putExtra(Intent.EXTRA_STREAM, u);
                            activity.startActivity(intent);
                            bos.flush();
                            bos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            });
            builder.preload();
        }
    }
}
