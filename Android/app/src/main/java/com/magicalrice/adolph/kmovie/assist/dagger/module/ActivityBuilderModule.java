package com.magicalrice.adolph.kmovie.assist.dagger.module;

import com.magicalrice.adolph.kmovie.login.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Adolph on 2018/2/28.
 */
@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity bindLoginActivity();
}
