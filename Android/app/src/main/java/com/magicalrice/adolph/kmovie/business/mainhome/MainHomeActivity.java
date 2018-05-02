package com.magicalrice.adolph.kmovie.business.mainhome;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.MeFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.PopularMovieFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.PopularTvFragment;
import com.magicalrice.adolph.kmovie.databinding.ActivityMainHomeBinding;
import com.magicalrice.adolph.kmovie.viewmodule.MainHomeViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;

import javax.inject.Inject;

public class MainHomeActivity extends BaseActivity<ActivityMainHomeBinding> implements TabLayout.OnTabSelectedListener {
    private MainHomeViewModule viewModule;
    @Inject
    MainViewModuleFactory factory;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }
        setUpTabLayout();
        viewModule = ViewModelProviders.of(this,factory).get(MainHomeViewModule.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main_home;
    }

    private void setUpTabLayout() {
        TabLayout bottomTab = binding.bottomTab;
        bottomTab.addTab(bottomTab.newTab().setCustomView(R.layout.tab_main_movie), true);
        bottomTab.addTab(bottomTab.newTab().setCustomView(R.layout.tab_main_tv));
        bottomTab.addTab(bottomTab.newTab().setCustomView(R.layout.tab_main_me));
        bottomTab.addOnTabSelectedListener(this);
        bottomTab.getTabAt(0).select();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tab.getCustomView().setSelected(true);
        onShowFragment(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        tab.getCustomView().setSelected(false);

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        tab.getCustomView().setSelected(true);
        onShowFragment(tab.getPosition());
    }

    private void onShowFragment(int position) {
        switch (position) {
            case 0:
                changeFragment("main_movie");
                break;
            case 1:
                changeFragment("main_tv");
                break;
            case 2:
                changeFragment("main_me");
                break;
        }
    }

    private void changeFragment(String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tr = manager.beginTransaction();
        Fragment movieFragment = manager.findFragmentByTag("main_movie");
        Fragment tvFragment = manager.findFragmentByTag("main_tv");
        Fragment meFragment = manager.findFragmentByTag("main_me");

        if (movieFragment != null) {
            tr.hide(movieFragment);
        }

        if (tvFragment != null) {
            tr.hide(tvFragment);
        }

        if (meFragment != null) {
            tr.hide(meFragment);
        }

        if (TextUtils.equals(tag,"main_movie")) {
            if (movieFragment == null) {
                movieFragment = new PopularMovieFragment();
                Bundle bundle = new Bundle();
                movieFragment.setArguments(bundle);
                tr.add(R.id.content,movieFragment,"main_movie");
            } else {
                tr.show(movieFragment);
            }
        } else if (TextUtils.equals(tag,"main_tv")) {
            if (tvFragment == null) {
                tvFragment = new PopularTvFragment();
                Bundle bundle = new Bundle();
                tvFragment.setArguments(bundle);
                tr.add(R.id.content,tvFragment,"main_tv");
            } else {
                tr.show(tvFragment);
            }
        } else if (TextUtils.equals(tag,"main_me")) {
            if (meFragment == null) {
                meFragment = new MeFragment();
                Bundle bundle = new Bundle();
                meFragment.setArguments(bundle);
                tr.add(R.id.content,meFragment,"main_me");
            } else {
                tr.show(meFragment);
            }
        }
        tr.commitAllowingStateLoss();
    }

}