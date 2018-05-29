package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseDaggerFragment;
import com.magicalrice.adolph.kmovie.databinding.FragmentMainHomeMeBinding;
import com.magicalrice.adolph.kmovie.viewmodule.MainMeViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;

import javax.inject.Inject;

import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Adolph on 2018/4/19.
 */

public class MeFragment extends BaseDaggerFragment<FragmentMainHomeMeBinding> {
    @Inject
    MainViewModuleFactory factory;
    private MainMeViewModule viewModule;
    private UserInfo userInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModule = ViewModelProviders.of(this,factory).get(MainMeViewModule.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main_home_me;
    }

    @Override
    public void createView(View view) {
        userInfo = viewModule.getUserInfo();
        if (userInfo != null) {
        }
    }
}
