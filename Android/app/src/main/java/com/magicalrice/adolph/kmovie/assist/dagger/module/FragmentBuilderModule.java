package com.magicalrice.adolph.kmovie.assist.dagger.module;

import com.magicalrice.adolph.kmovie.assist.dagger.scope.FragmentScoped;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.MainHomeFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.MeFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.SubMainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {
    @FragmentScoped
    @ContributesAndroidInjector(modules = MainHomeModule.class)
    abstract MainHomeFragment bindMainHomeFragment();

    @FragmentScoped
    @ContributesAndroidInjector(modules = SubMainModule.class)
    abstract SubMainFragment bindSubMainFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MeFragment bindMeFragment();
}
