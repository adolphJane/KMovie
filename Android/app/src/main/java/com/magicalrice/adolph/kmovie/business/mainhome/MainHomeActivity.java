package com.magicalrice.adolph.kmovie.business.mainhome;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.databinding.ActivityMainHomeBinding;

public class MainHomeActivity extends BaseActivity<ActivityMainHomeBinding>{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
        }
        setUpViewPager();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main_home;
    }

    private void setUpViewPager() {
        MainHomePagerAdapter adapter = new MainHomePagerAdapter(getSupportFragmentManager());
        binding.viewpager.setAdapter(adapter);
    }
}