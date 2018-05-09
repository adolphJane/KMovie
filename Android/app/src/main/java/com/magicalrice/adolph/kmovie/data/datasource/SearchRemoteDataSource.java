package com.magicalrice.adolph.kmovie.data.datasource;

import android.text.TextUtils;

import com.magicalrice.adolph.kmovie.data.entities.CollectionResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.CompanyResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.KeywordResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.PersonResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Adolph on 2018/5/9.
 */

public class SearchRemoteDataSource {
    Tmdb tmdb;

    @Inject
    public SearchRemoteDataSource(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    public Observable<MovieResultsPage> searchMovie(String query, int page) {
        return tmdb.searchService().movieSearch(query,page,"zh",true).compose(RxUtils.io_main());
    }

    public Observable<TvShowResultsPage> searchTv(String query, int page) {
        return tmdb.searchService().tvSearch(query,page,"zh").compose(RxUtils.io_main());
    }

    public Observable<CompanyResultsPage> searchCompany(String query, int page) {
        return tmdb.searchService().company(query,page).compose(RxUtils.io_main());
    }

    public Observable<CollectionResultsPage> searchCollections(String query,int page) {
        return tmdb.searchService().collection(query,page,"zh").compose(RxUtils.io_main());
    }

    public Observable<PersonResultsPage> searchPeople(String query, int page) {
        return tmdb.searchService().person(query,page,true,"phrase").compose(RxUtils.io_main());
    }

    public Observable<KeywordResultsPage> searchKeywords(String query, int page) {
        return tmdb.searchService().keyword(query,page).compose(RxUtils.io_main());
    }
}
