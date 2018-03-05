package com.magicalrice.adolph.kmovie.assist.dagger.module;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.data.remote.RequestInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Adolph on 2018/2/28.
 */
@Module
public class NetModule {
    @Singleton
    @Provides
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Singleton
    @Provides
    Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.create();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttp(Cache cache) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(ApiConstants.TIME_IN_SEC, TimeUnit.SECONDS)
                .readTimeout(ApiConstants.TIME_IN_SEC, TimeUnit.SECONDS)
                .addInterceptor(new RequestInterceptor())
                .cache(cache)
                .build();
        return client;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(ApiConstants.BASE_URL)
                .client(client)
                .build();
        return retrofit;
    }
}
