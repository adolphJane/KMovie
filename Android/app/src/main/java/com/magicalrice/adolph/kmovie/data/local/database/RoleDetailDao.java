package com.magicalrice.adolph.kmovie.data.local.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
