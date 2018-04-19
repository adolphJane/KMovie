package com.magicalrice.adolph.kmovie.business.mainhome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.MeFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.PopularMovieFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.fragment.PopularTvFragment;
import com.magicalrice.adolph.kmovie.databinding.ActivityMainHomeBinding;

public class MainHomeActivity extends BaseActivity<ActivityMainHomeBinding> implements TabLayout.OnTabSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }
        setUpViewPager();
        setUpTabLayout();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main_home;
    }

    private void setUpViewPager() {
        MainHomePagerAdapter adapter = new MainHomePagerAdapter(getSupportFragmentManager());
        PopularMovieFragment fragmentMovie = new PopularMovieFragment();
        PopularTvFragment fragmentTv = new PopularTvFragment();
        MeFragment fragmentMe = new MeFragment();
        adapter.addFragment(fragmentMovie,"电影");
        adapter.addFragment(fragmentTv,"电视节目");
        adapter.addFragment(fragmentMe,"我的");
        binding.viewpager.setAdapter(adapter);
    }

    private void setUpTabLayout() {
        TabLayout bottomTab = binding.bottomTab;
        bottomTab.addTab(bottomTab.newTab().setCustomView(R.layout.tab_main_movie),true);
        bottomTab.addTab(bottomTab.newTab().setCustomView(R.layout.tab_main_tv));
        bottomTab.addTab(bottomTab.newTab().setCustomView(R.layout.tab_main_me));
        bottomTab.addOnTabSelectedListener(this);
        bottomTab.setupWithViewPager(binding.viewpager);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tab.getCustomView().setSelected(true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        tab.getCustomView().setSelected(false);

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        tab.getCustomView().setSelected(true);
    }
}