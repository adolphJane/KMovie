package com.magicalrice.adolph.kmovie.assist.dagger.module;

import com.magicalrice.adolph.kmovie.business.mainhome.fragment.MainHomeFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.MeFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.SubMainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {
    @ContributesAndroidInjector(modules = MainHomeModule.class)
    abstract MainHomeFragment bindMainHomeFragment();

    @ContributesAndroidInjector(modules = SubMainModule.class)
    abstract SubMainFragment bindSubMainFragment();

    @ContributesAndroidInjector
    abstract MeFragment bindMeFragment();
}
