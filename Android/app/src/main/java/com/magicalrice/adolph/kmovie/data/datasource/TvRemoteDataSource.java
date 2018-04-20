package com.magicalrice.adolph.kmovie.data.datasource;

import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Adolph on 2018/4/20.
 */

public class TvRemoteDataSource {
    Tmdb tmdb;

    @Inject
    public TvRemoteDataSource(Tmdb tmdb){
        this.tmdb = tmdb;
    }

    public Observable<TvShowResultsPage> getPopularTv(int page) {
        return tmdb.tvService().popular(page,"zh")
                .compose(RxUtils.io_main());
    }
}
