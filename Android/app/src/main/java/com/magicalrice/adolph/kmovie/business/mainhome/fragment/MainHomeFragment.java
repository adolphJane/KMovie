package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseFragment;
import com.magicalrice.adolph.kmovie.databinding.FragmentMainHomeBinding;

/**
 * Created by Adolph on 2018/5/2.
 */

public class MainHomeFragment extends BaseFragment<FragmentMainHomeBinding> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main_home;
    }

    @Override
    public void createView(View view) {

    }
}
