package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.gson.JsonSyntaxException;
import com.magicalrice.adolph.kmovie.data.datasource.RoleRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.Person;
import com.magicalrice.adolph.kmovie.data.entities.PersonMovieCredits;
import com.magicalrice.adolph.kmovie.data.entities.RoleDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.PersonImages;
import com.magicalrice.adolph.kmovie.data.entities.PersonTvCredits;
import com.magicalrice.adolph.kmovie.data.repository.RoleRepository;
import com.magicalrice.adolph.kmovie.utils.LUtils;

import io.reactivex.Observable;
import io.reactivex.functions.Function4;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by Adolph on 2018/5/7.
 */

public class RoleViewModule extends AndroidViewModel {
    private RoleRepository repository;
    public MutableLiveData<RoleDetailBean> personData = new MutableLiveData<>();

    public RoleViewModule(@NonNull Application application,RoleRepository repository) {
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
