package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.Toast;

import com.magicalrice.adolph.kmovie.base.MovieApplication;
import com.magicalrice.adolph.kmovie.data.datasource.LoginRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.RequestToken;
import com.magicalrice.adolph.kmovie.utils.LUtils;
import com.magicalrice.adolph.kmovie.utils.SpUtils;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by Adolph on 2018/3/1.
 */

public class LoginViewModule extends AndroidViewModel {
    private LoginRemoteDataSource dataSource;

    public LoginViewModule(@NonNull Application application, LoginRemoteDataSource dataSource) {
        super(application);
        this.dataSource = dataSource;
    }

    public Observable<RequestToken> guestLogin() {
        return dataSource.getBaseToken();
    }

    public Observable<RequestToken> userLogin(TextInputLayout accountLayout, TextInputLayout passwordLayout) {
        String userName = accountLayout.getEditText().getText().toString();
        String password = passwordLayout.getEditText().getText().toString();
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
            return dataSource.getValidateToken(userName, password);
        } else {
            passwordLayout.setError("账号密码不能为空");
            return Observable.empty();
        }
    }

    public void loginJMessage(String userName,String password) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            return;
        }
        UserInfo myInfo = JMessageClient.getMyInfo();
        if (myInfo == null) {
            JMessageClient.login(userName, password, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    if (i == 0) {

                    } else if (i > 0) {
                        LUtils.e(s);
                        registerJMessage(userName,password);
                    }
                }
            });
        }
    }

    public void registerJMessage(String userName,String password) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            return;
        }

        JMessageClient.register(userName, password, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {

            }
        });
    }
}
