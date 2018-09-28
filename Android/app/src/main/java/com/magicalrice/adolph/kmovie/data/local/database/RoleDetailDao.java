package com.magicalrice.adolph.kmovie.data.local.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.magicalrice.adolph.kmovie.data.entities.RoleDetailBean;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface RoleDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoleDetail(RoleDetailBean roleDetailBean);

    @Query("SELECT * FROM role_detail_bean WHERE roleId = :roleId")
    Single<RoleDetailBean> loadRoleDetail(long roleId);

    @Query("SELECT * FROM role_detail_bean WHERE isLike = :isLike")
    LiveData<List<RoleDetailBean>> loadLoveRole(boolean isLike);

    @Update
    void updateRoleDetail(RoleDetailBean roleDetailBean);
}
