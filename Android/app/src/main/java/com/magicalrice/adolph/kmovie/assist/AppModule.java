package com.magicalrice.adolph.kmovie.assist;

import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.data.remote.RequestInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by Adolph on 2018/2/26.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(ApiConstants.TIME_IN_SEC, TimeUnit.SECONDS);
        builder.readTimeout(ApiConstants.TIME_IN_SEC,TimeUnit.SECONDS);
        builder.addInterceptor(new RequestInterceptor());
        return builder.build();
    }
}
