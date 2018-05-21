package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.google.gson.JsonSyntaxException;
import com.magicalrice.adolph.kmovie.data.datasource.RoleRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.Person;
import com.magicalrice.adolph.kmovie.data.entities.PersonCredits;
import com.magicalrice.adolph.kmovie.data.entities.PersonDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.PersonImages;
import com.magicalrice.adolph.kmovie.utils.LUtils;

import io.reactivex.Observable;
import io.reactivex.functions.Function4;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by Adolph on 2018/5/7.
 */

public class RoleViewModule extends AndroidViewModel {
    private RoleRemoteDataSource dataSource;
    public MutableLiveData<PersonDetailBean> personData = new MutableLiveData<>();

    public RoleViewModule(@NonNull Application application,RoleRemoteDataSource dataSource) {
        super(application);
        this.dataSource = dataSource;
    }

    public void getStarSummary(int personId) {
        Observable.zip(
                dataSource.getImages(personId),
                dataSource.getMovieCredits(personId),
                dataSource.getPersonSummary(personId),
                dataSource.getTvCredits(personId),
                new Function4<PersonImages, PersonCredits, Person, PersonCredits, PersonDetailBean>() {
                    @Override
                    public PersonDetailBean apply(PersonImages personImages, PersonCredits personCredits, Person person, PersonCredits personCredits2) throws Exception {
                        PersonDetailBean bean = new PersonDetailBean();
                        bean.setPersonImages(personImages);
                        bean.setMovieCredits(personCredits);
                        bean.setPerson(person);
                        bean.setTvCredits(personCredits2);
                        return bean;
                    }
                }
        ).subscribe(personDetailBean -> {
            personData.setValue(personDetailBean);
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
                LUtils.e("ErrorMessage:%s,ErrorCause%s", throwable.getMessage(), throwable.getCause().toString());
            }
        });
    }
}
