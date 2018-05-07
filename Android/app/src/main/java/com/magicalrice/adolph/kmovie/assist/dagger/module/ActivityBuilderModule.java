package com.magicalrice.adolph.kmovie.assist.dagger.module;

import com.magicalrice.adolph.kmovie.assist.dagger.scope.ActivityScoped;
import com.magicalrice.adolph.kmovie.business.login.LoginActivity;
import com.magicalrice.adolph.kmovie.business.mainhome.MainHomeActivity;
import com.magicalrice.adolph.kmovie.business.movie_detail.MovieDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Adolph on 2018/2/28.
 */
@Module
public abstract class ActivityBuilderModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity bindLoginActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainHomeModule.class)
    abstract MainHomeActivity bindMainHomeActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = MovieDetailModule.class)
    abstract MovieDetailActivity bindMovieDetailActivity();
}
