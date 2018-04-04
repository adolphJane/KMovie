package com.magicalrice.adolph.kmovie.login;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.databinding.ActivityLoginBinding;
import com.magicalrice.adolph.kmovie.utils.AnimatorUtil;
import com.magicalrice.adolph.kmovie.utils.WidgetUtils;

/**
 * Created by Adolph on 2018/2/26.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void onResume() {
        super.onResume();
        AnimatorUtil.createAnimator()
                .play(binding.appTitle, 1000, null, new BounceInterpolator(), "scaleX", 0f,4f,6f)
                .with(binding.appTitle, 1000, null, new BounceInterpolator(), "scaleY", 0f,4f,6f)
                .after(binding.userInfo, 500,null,new LinearInterpolator(),"alpha",0,255)
                .start();
    }




}