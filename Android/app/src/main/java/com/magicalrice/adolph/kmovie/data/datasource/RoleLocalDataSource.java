package com.magicalrice.adolph.kmovie.data.datasource;

import androidx.room.EmptyResultSetException;

import com.magicalrice.adolph.kmovie.data.entities.RoleDetailBean;
import com.magicalrice.adolph.kmovie.data.local.database.MovieDatabase;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class RoleLocalDataSource {
    private MovieDatabase database;

    @Inject
    public RoleLocalDataSource(MovieDatabase database) {
        this.database = database;
    }

    public Flowable<RoleDetailBean> getRoleDetail(long roleId) {
        return Flowable.create(emitter ->
                database.roleDetailDao().loadRoleDetail(roleId).subscribe(roleDetailBean -> {
                        emitter.onNext(roleDetailBean);
                }, throwable -> {
                    if (throwable instanceof EmptyResultSetException) {
                        emitter.onComplete();
                    } else {
                        emitter.onError(throwable);
                    }
                }), BackpressureStrategy.LATEST
        );
    }

    public void insertRole(RoleDetailBean bean) {
        database.roleDetailDao().insertRoleDetail(bean);
    }

    public void updateRole(RoleDetailBean bean) {
        Observable.create(emitter -> {
            database.roleDetailDao().updateRoleDetail(bean);
            emitter.onComplete();
        }).compose(RxUtils.io_main_o()).subscribe();
    }
}
