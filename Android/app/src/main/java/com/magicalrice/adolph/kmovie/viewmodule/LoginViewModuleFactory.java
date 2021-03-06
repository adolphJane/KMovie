package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.magicalrice.adolph.kmovie.data.datasource.LoginRemoteDataSource;

/**
 * Created by Adolph on 2018/2/28.
 */

public class LoginViewModuleFactory extends ViewModelProvider.AndroidViewModelFactory {
    private LoginRemoteDataSource loginDataSource;
    private Application application;

    public LoginViewModuleFactory(@NonNull Application application, LoginRemoteDataSource loginDataSource) {
        super(application);
        this.application = application;
        this.loginDataSource = loginDataSource;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModule.class)) {
            return (T) new LoginViewModule(application,loginDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
