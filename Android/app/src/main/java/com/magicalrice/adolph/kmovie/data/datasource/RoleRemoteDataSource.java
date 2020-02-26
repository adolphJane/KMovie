package com.magicalrice.adolph.kmovie.data.datasource;

import com.magicalrice.adolph.kmovie.data.entities.Person;
import com.magicalrice.adolph.kmovie.data.entities.PersonImages;
import com.magicalrice.adolph.kmovie.data.entities.PersonMovieCredits;
import com.magicalrice.adolph.kmovie.data.entities.PersonTvCredits;
import com.magicalrice.adolph.kmovie.data.entities.RoleDetailBean;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function4;

/**
 * Created by Adolph on 2018/5/21.
 */

public class RoleRemoteDataSource {
    Tmdb tmdb;
    @Inject
    public RoleRemoteDataSource(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    public Flowable<Person> getPersonSummary(long personId) {
        return tmdb.personService().summary(personId);
    }

    public Flowable<PersonMovieCredits> getMovieCredits(long personId) {
        return tmdb.personService().movieCredits(personId,"zh");
    }

    public Flowable<PersonTvCredits> getTvCredits(long personId) {
        return tmdb.personService().tvCredits(personId,"zh");
    }

    public Flowable<PersonImages> getImages(long personId) {
        return tmdb.personService().images(personId);
    }

    public Flowable<RoleDetailBean> getRoleSummary(long roleId) {
        return Flowable.zip(
                getImages(roleId),
                getMovieCredits(roleId),
                getPersonSummary(roleId),
                getTvCredits(roleId),
                new Function4<PersonImages, PersonMovieCredits, Person, PersonTvCredits, RoleDetailBean>() {

                    @Override
                    public RoleDetailBean apply(PersonImages personImages, PersonMovieCredits personMovieCredits, Person person, PersonTvCredits personTvCredits) throws Exception {
                        RoleDetailBean bean = new RoleDetailBean();
                        bean.setPersonImages(personImages);
                        bean.setMovieCredits(personMovieCredits);
                        bean.setPerson(person);
                        bean.setTvCredits(personTvCredits);
                        return bean;
                    }
                }
        );
    }
}
