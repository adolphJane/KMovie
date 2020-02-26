package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonSyntaxException;
import com.magicalrice.adolph.kmovie.data.entities.RoleDetailBean;
import com.magicalrice.adolph.kmovie.data.repository.RoleRepository;
import com.magicalrice.adolph.kmovie.utils.LUtils;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by Adolph on 2018/5/7.
 */

public class RoleViewModule extends AndroidViewModel {
    private RoleRepository repository;
    public MutableLiveData<RoleDetailBean> personData = new MutableLiveData<>();

    public RoleViewModule(@NonNull Application application, RoleRepository repository) {
        super(application);
        this.repository = repository;
    }

    public void getRoleSummary(long roleId) {
        repository.getRoleDetail(roleId).subscribe(roleDetailBean -> {
            personData.setValue(roleDetailBean);
        },throwable -> {
            if (throwable instanceof HttpException) {
                ResponseBody body = ((HttpException) throwable).response().errorBody();
                try {
                    LUtils.e(body.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (throwable instanceof IllegalStateException || throwable instanceof JsonSyntaxException) {
                throwable.printStackTrace();
            } else {
                throwable.printStackTrace();
            }
        });
    }

    public void likePerson(RoleDetailBean bean) {
        repository.likeRole(bean);
    }
}
