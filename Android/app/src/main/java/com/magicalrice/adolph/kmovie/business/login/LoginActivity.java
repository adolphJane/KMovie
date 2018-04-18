package com.magicalrice.adolph.kmovie.business.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.databinding.ActivityLoginBinding;
import com.magicalrice.adolph.kmovie.utils.AnimatorUtil;
import com.magicalrice.adolph.kmovie.utils.ScreenUtils;
import com.magicalrice.adolph.kmovie.viewmodule.LoginViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.ViewModuleFactory;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/2/26.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements ViewTreeObserver.OnGlobalLayoutListener,LoginClickListener {
    @Inject
    public ViewModuleFactory factory;

    private LoginViewModule viewModule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this);
        viewModule = ViewModelProviders.of(this,factory).get(LoginViewModule.class);
        binding.setListener(this);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            AnimatorUtil.createAnimator()
                    .play(binding.appTitle, 1000, null, new BounceInterpolator(), "scaleX", 0f, 4f, 6f)
                    .with(binding.appTitle, 1000, null, new BounceInterpolator(), "scaleY", 0f, 4f, 6f)
                    .before(binding.userInfo, 1000, null, new LinearInterpolator(), "alpha", 0, 1)
                    .start();
        }
    }

    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int keyboardHeight = ScreenUtils.getScreenHeight() - (rect.bottom - rect.top);
        boolean isKeyboardShowing = keyboardHeight > ScreenUtils.getScreenHeight() / 3;
        if (isKeyboardShowing) {
            binding.userInfo.setY(rect.bottom - binding.userInfo.getHeight());
        } else {
            binding.userInfo.setY(rect.bottom - binding.userInfo.getHeight() - ScreenUtils.dp2px(this, 50));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    @Override
    public void onVisitorLogin() {
        viewModule.guestLogin();
    }

    @Override
    public void onUserLogin() {
        viewModule.userLogin(binding.inputLayoutOne,binding.inputLayoutTwo);
    }

    @Override
    public void onInfoShow() {
        Toast.makeText(this, "请使用TheMovieDB的帐号进行登陆", Toast.LENGTH_SHORT).show();
    }
}