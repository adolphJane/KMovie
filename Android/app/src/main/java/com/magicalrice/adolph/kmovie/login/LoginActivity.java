package com.magicalrice.adolph.kmovie.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.BounceInterpolator;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.databinding.ActivityLoginBinding;
import com.magicalrice.adolph.kmovie.utils.AnimatorUtil;
import com.magicalrice.adolph.kmovie.utils.StatusBarUtil;

/**
 * Created by Adolph on 2018/2/26.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.translucentStatusBar(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void onResume() {
        super.onResume();
        AnimatorUtil.createAnimator()
                .play(binding.appTitle, 600, null, new BounceInterpolator(), "scaleX", 0f,4f,6f)
                .with(binding.appTitle, 600, null, new BounceInterpolator(), "scaleY", 0f,4f,6f)
                .start();
    }


}