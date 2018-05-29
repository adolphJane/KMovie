package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.Toast;

import com.magicalrice.adolph.kmovie.base.MovieApplication;
import com.magicalrice.adolph.kmovie.business.login.LoginActivity;
import com.magicalrice.adolph.kmovie.business.mainhome.MainHomeActivity;
import com.magicalrice.adolph.kmovie.data.datasource.LoginRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.RequestToken;
import com.magicalrice.adolph.kmovie.utils.LUtils;
import com.magicalrice.adolph.kmovie.utils.SpUtils;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by Adolph on 2018/3/1.
 */

public class LoginViewModule extends AndroidViewModel {
    private LoginRemoteDataSource dataSource;
    public MutableLiveData<Integer> isLogin = new MutableLiveData<>();

    public LoginViewModule(@NonNull Application application, LoginRemoteDataSource dataSource) {
        super(application);
        this.dataSource = dataSource;
    }

    public void guestLogin() {
        dataSource.getBaseToken().subscribe(requestToken -> {
            SpUtils.getInstance(getApplication()).put("base_token", requestToken.getRequest_token());
            SpUtils.getInstance(getApplication()).put("login_type", 1);
            Toast.makeText(getApplication(), "登录成功", Toast.LENGTH_SHORT).show();
            MovieApplication.getInstance().setHasToken(true);
            isLogin.setValue(1);
        }, throwable -> {
            Toast.makeText(getApplication(), "登录失败,请稍后重试", Toast.LENGTH_SHORT).show();
            isLogin.setValue(0);
        });
    }

    public void userLogin(TextInputLayout accountLayout, TextInputLayout passwordLayout) {
        String userName = accountLayout.getEditText().getText().toString();
        String password = passwordLayout.getEditText().getText().toString();
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
            userLogin(userName, password);
        } else {
            passwordLayout.setError("账号密码不能为空");
        }
    }

    public void userLogin(String account, String password) {
        dataSource.getValidateToken(account, password).subscribe(requestToken -> {
            if (requestToken.equals("请求失败")) {
                Toast.makeText(getApplication(), "登录失败,请稍后重试", Toast.LENGTH_SHORT).show();
                isLogin.setValue(0);
            } else {
                SpUtils.getInstance(getApplication()).put("user_token", requestToken.getRequest_token());
                loginJMessage(account, password);
            }
        });
    }

    public void loginJMessage(String userName, String password) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            return;
        }
        UserInfo myInfo = JMessageClient.getMyInfo();
        if (myInfo == null) {
            JMessageClient.login(userName, password, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    if (i == 0) {
                        Toast.makeText(getApplication(), "登录成功", Toast.LENGTH_SHORT).show();
                        int type = (int) SpUtils.getInstance(getApplication()).get("login_type", 0);
                        if (type != 2) {
                            SpUtils.getInstance(getApplication()).put("account", userName);
                            SpUtils.getInstance(getApplication()).put("password", password);
                            SpUtils.getInstance(getApplication()).put("login_type", 2);

                            JMessageClient.getUserInfo(userName, new GetUserInfoCallback() {
                                @Override
                                public void gotResult(int i, String s, UserInfo userInfo) {

                                }
                            });
                        }
                        isLogin.setValue(2);
                    } else if (i > 0) {
                        LUtils.e(s);
                        registerJMessage(userName, password);
                    }
                }
            });
        }
    }

    public void registerJMessage(String userName, String password) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            return;
        }

        JMessageClient.register(userName, password, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                LUtils.e(s);
            }
        });
    }
}
