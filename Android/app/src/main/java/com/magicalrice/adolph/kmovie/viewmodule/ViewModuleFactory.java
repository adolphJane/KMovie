package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.magicalrice.adolph.kmovie.data.datasource.LoginRemoteDataSource;

/**
 * Created by Adolph on 2018/2/28.
 */

public class ViewModuleFactory extends ViewModelProvider.AndroidViewModelFactory {
    private LoginRemoteDataSource dataSource;
    private Application application;

    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link AndroidViewModel}
     */
    public ViewModuleFactory(@NonNull Application application,LoginRemoteDataSource dataSource) {
        super(application);
        this.application = application;
        this.dataSource = dataSource;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModule.class)) {
            return (T) new LoginViewModule(application,dataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
