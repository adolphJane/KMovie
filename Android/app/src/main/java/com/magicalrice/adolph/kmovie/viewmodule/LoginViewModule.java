package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.Toast;

import com.magicalrice.adolph.kmovie.base.MovieApplication;
import com.magicalrice.adolph.kmovie.data.datasource.LoginRemoteDataSource;
import com.magicalrice.adolph.kmovie.utils.SpUtils;

import io.reactivex.functions.Consumer;

/**
 * Created by Adolph on 2018/3/1.
 */

public class LoginViewModule extends AndroidViewModel {
    private LoginRemoteDataSource dataSource;
    private MovieApplication context;

    public LoginViewModule(@NonNull Application application, LoginRemoteDataSource dataSource) {
        super(application);
        this.context = (MovieApplication) application;
        this.dataSource = dataSource;
    }

    public void guestLogin() {
        dataSource.getBaseToken()
                .subscribe(requestToken -> {
                    SpUtils.getInstance(context).put("base_token", requestToken.getRequest_token());
                    Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                    context.setHasToken(true);
                }, throwable -> Toast.makeText(context, "登录失败,请稍后重试", Toast.LENGTH_SHORT).show());
    }

    public void userLogin(TextInputLayout accountLayout,TextInputLayout passwordLayout) {
        String userName = accountLayout.getEditText().getText().toString();
        String password = passwordLayout.getEditText().getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            passwordLayout.setError("账号密码不能为空");
            return;
        }
        dataSource.getValidateToken(userName, password)
                .subscribe(requestToken -> {
                    if (requestToken.equals("请求失败")) {
                        Toast.makeText(context, "登录失败,请稍后重试", Toast.LENGTH_SHORT).show();
                    } else {
                        SpUtils.getInstance(context).put("user_token", requestToken.getRequest_token());
                        Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
