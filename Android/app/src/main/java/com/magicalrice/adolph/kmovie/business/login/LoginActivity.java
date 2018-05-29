package com.magicalrice.adolph.kmovie.business.login;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.base.MovieApplication;
import com.magicalrice.adolph.kmovie.business.mainhome.MainHomeActivity;
import com.magicalrice.adolph.kmovie.databinding.ActivityLoginBinding;
import com.magicalrice.adolph.kmovie.utils.AnimatorUtil;
import com.magicalrice.adolph.kmovie.utils.ScreenUtils;
import com.magicalrice.adolph.kmovie.utils.SpUtils;
import com.magicalrice.adolph.kmovie.utils.StatusBarUtil;
import com.magicalrice.adolph.kmovie.viewmodule.LoginViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.LoginViewModuleFactory;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/2/26.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements ViewTreeObserver.OnGlobalLayoutListener, LoginClickListener {
    @Inject
    public LoginViewModuleFactory factory;

    private LoginViewModule viewModule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.translucentStatusBar(this, false);
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this);
        viewModule = ViewModelProviders.of(this, factory).get(LoginViewModule.class);
        binding.setListener(this);
        canLoginDirect();
        loginViewModule();
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

    private void loginViewModule() {
        viewModule.isLogin.observe(this, integer -> {
            int status = integer == null ? 0 : integer.intValue();
            binding.setShowProgress(false);
            if (status == 0) {
            } else if (status == 1) {
                startActivity(new Intent(LoginActivity.this, MainHomeActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onVisitorLogin() {
        binding.setShowProgress(true);
        viewModule.guestLogin();
    }

    @Override
    public void onUserLogin() {
        binding.setShowProgress(true);
        String account = (String) SpUtils.getInstance(this).get("account", "");
        String password = (String) SpUtils.getInstance(this).get("password", "");
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            viewModule.userLogin(binding.inputLayoutOne, binding.inputLayoutTwo);
        } else {
            viewModule.userLogin(account,password);
        }
    }

    @Override
    public void onInfoShow() {
        Toast.makeText(this, "请使用TheMovieDB的帐号进行登陆", Toast.LENGTH_SHORT).show();
    }

    private void canLoginDirect() {
        int type = (int) SpUtils.getInstance(this).get("login_type", 0);
        if (type == 0) {
            return;
        } else if (type == 1) {
            onVisitorLogin();
        } else if (type == 2) {
            onUserLogin();
        }
    }
}