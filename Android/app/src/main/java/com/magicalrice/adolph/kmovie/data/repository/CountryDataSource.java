package com.magicalrice.adolph.kmovie.data.repository;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.magicalrice.adolph.kmovie.data.entities.CountryISO1336;
import com.magicalrice.adolph.kmovie.utils.SpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Adolph on 2018/5/17.
 */

public class CountryDataSource {
    private SpUtils spUtils;
    private Gson gson;

    @Inject
    public CountryDataSource(SpUtils spUtils,Gson gson,Context context) {
        this.spUtils = spUtils;
        this.gson = gson;
        Observable.create(emitter -> {
            String country = (String) spUtils.get("country", "");
            if (TextUtils.isEmpty(country)) {
                InputStream inputStream = context.getAssets().open("country.json");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                country = new String(buffer);
                spUtils.put("country", country);
            }
            emitter.onComplete();
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public CountryISO1336 getCountryByISO3166(String iso) {
        String s = (String) spUtils.get("country","");
        CountryISO1336 bean = new CountryISO1336();
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject jsonObject1 = jsonObject.getJSONObject(iso);
                bean.setEngName(jsonObject1.getString("engName"));
                bean.setChiName(jsonObject1.getString("chiName"));
                bean.setImgPath(jsonObject1.getString("imgPath"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }
}
