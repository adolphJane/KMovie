package com.magicalrice.adolph.kmovie.data.repository;

import com.magicalrice.adolph.kmovie.data.datasource.RoleLocalDataSource;
import com.magicalrice.adolph.kmovie.data.datasource.RoleRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.RoleDetailBean;

import java.util.NoSuchElementException;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RoleRepository {
    private RoleRemoteDataSource remoteDataSource;
    private RoleLocalDataSource localDataSource;

    @Inject
    public RoleRepository(RoleRemoteDataSource remoteDataSource,RoleLocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public Flowable<RoleDetailBean> getRoleDetail(long roleId) {
        return Flowable.concat(localDataSource.getRoleDetail(roleId),remoteDataSource.getRoleSummary(roleId))
                .switchIfEmpty(s -> {
                    s.onError(new NoSuchElementException());
                }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .doOnNext(movieDetailBean -> {
                    movieDetailBean.setRoleId(roleId);
                    localDataSource.insertRole(movieDetailBean);
                }).observeOn(AndroidSchedulers.mainThread());
    }

    public void likeRole(RoleDetailBean bean) {
        localDataSource.updateRole(bean);
    }
}
