package com.magicalrice.adolph.kmovie.viewmodule;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.magicalrice.adolph.kmovie.base.MovieApplication;

/**
 * Created by Adolph on 2018/2/28.
 */

public class MovieViewModuleFactory implements ViewModelProvider.Factory {



    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModule.class)) {
            return (T) new LoginViewModule(MovieApplication.getInstance());
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
