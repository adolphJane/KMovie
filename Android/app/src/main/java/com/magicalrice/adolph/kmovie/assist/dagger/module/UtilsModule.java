package com.magicalrice.adolph.kmovie.assist.dagger.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.data.remote.RequestInterceptor;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.data.repository.CountryDataSource;
import com.magicalrice.adolph.kmovie.utils.SpUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Adolph on 2018/2/28.
 */
@Module
public class UtilsModule {

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
    HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttp(Cache cache,HttpLoggingInterceptor interceptor) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(ApiConstants.TIME_IN_SEC, TimeUnit.SECONDS)
                .readTimeout(ApiConstants.TIME_IN_SEC, TimeUnit.SECONDS)
                .addInterceptor(new RequestInterceptor())
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
        return client;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConstants.API_URL)
                .client(client)
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    Tmdb provideTmdb(Retrofit retrofit) {
        Tmdb tmdb = new Tmdb(retrofit);
        return tmdb;
    }

    @Singleton
    @Provides
    SpUtils provideSharePreference(Context context) {
        SpUtils spUtils = new SpUtils(context);
        return spUtils;
    }

    @Singleton
    @Provides
    CountryDataSource provideCountryData(SpUtils spUtils,Gson gson,Context context) {
        CountryDataSource dataSource = new CountryDataSource(spUtils,gson,context);
        return dataSource;
    }
}
