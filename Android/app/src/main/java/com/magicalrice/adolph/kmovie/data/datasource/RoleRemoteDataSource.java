package com.magicalrice.adolph.kmovie.data.datasource;

import com.magicalrice.adolph.kmovie.data.entities.Person;
import com.magicalrice.adolph.kmovie.data.entities.PersonCredits;
import com.magicalrice.adolph.kmovie.data.entities.PersonImages;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Adolph on 2018/5/21.
 */

public class RoleRemoteDataSource {
    Tmdb tmdb;
    @Inject
    public RoleRemoteDataSource(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    public Observable<Person> getPersonSummary(int personId) {
        return tmdb.personService().summary(personId).compose(RxUtils.io_main());
    }

    public Observable<PersonCredits> getMovieCredits(int personId) {
        return tmdb.personService().movieCredits(personId,"zh").compose(RxUtils.io_main());
    }

    public Observable<PersonCredits> getTvCredits(int personId) {
        return tmdb.personService().tvCredits(personId,"zh").compose(RxUtils.io_main());
    }

    public Observable<PersonImages> getImages(int personId) {
        return tmdb.personService().images(personId).compose(RxUtils.io_main());
    }
}
