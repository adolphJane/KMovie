package com.magicalrice.adolph.kmovie.login;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.data.entities.Account;
import com.magicalrice.adolph.kmovie.data.entities.Movie;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.databinding.ActivityLoginBinding;
import com.magicalrice.adolph.kmovie.utils.AnimatorUtil;
import com.magicalrice.adolph.kmovie.utils.ScreenUtils;
import com.magicalrice.adolph.kmovie.viewmodule.LoginViewModule;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/2/26.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements ViewTreeObserver.OnGlobalLayoutListener, LoginClickListener {
    private LoginViewModule viewModule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this);
        viewModule = ViewModelProviders.of(this).get(LoginViewModule.class);
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
        Tmdb tmdb = new Tmdb(ApiConstants.API_KEY);

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Logger.e(tmdb.accountService().summary().execute().body().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onUserLogin() {
    }

    @Override
    public void onInfoShow() {
        Toast.makeText(this, "请使用TheMovieDB的帐号进行登陆", Toast.LENGTH_SHORT).show();
    }
}