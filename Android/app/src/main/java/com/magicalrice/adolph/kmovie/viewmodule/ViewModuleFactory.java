package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.magicalrice.adolph.kmovie.data.datasource.LoginRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;

/**
 * Created by Adolph on 2018/2/28.
 */

public class ViewModuleFactory extends ViewModelProvider.AndroidViewModelFactory {
    private LoginRemoteDataSource loginDataSource;
    private MovieRemoteDataSource movieDataSource;
    private Application application;

    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link AndroidViewModel}
     */
    public ViewModuleFactory(@NonNull Application application,LoginRemoteDataSource loginDataSource,MovieRemoteDataSource movieDataSource) {
        super(application);
        this.application = application;
        this.loginDataSource = loginDataSource;
        this.movieDataSource = movieDataSource;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModule.class)) {
            return (T) new LoginViewModule(application,loginDataSource);
        } else if (modelClass.isAssignableFrom(MovieViewModule.class)) {
            return (T) new MovieViewModule(application,movieDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
