package com.magicalrice.adolph.kmovie.assist.dagger.module;

import com.magicalrice.adolph.kmovie.business.mainhome.fragment.MeFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.PopularMovieFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.PopularTvFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {
    @ContributesAndroidInjector(modules = MovieFragmentModule.class)
    abstract PopularMovieFragment bindMovieFragment();

    @ContributesAndroidInjector
    abstract PopularTvFragment bindTvFragment();

    @ContributesAndroidInjector
    abstract MeFragment bindMeFragment();
}
