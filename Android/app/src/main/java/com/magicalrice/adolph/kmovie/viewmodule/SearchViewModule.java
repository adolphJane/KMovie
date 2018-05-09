package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.magicalrice.adolph.kmovie.data.datasource.SearchRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.CollectionResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.CompanyResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.KeywordResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.PersonResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adolph on 2018/5/7.
 */

public class SearchViewModule extends AndroidViewModel {
    private List<String> historyName;
    private Application application;
    private SearchRemoteDataSource dataSource;
    public MutableLiveData<String> queryData = new MutableLiveData<>();
    public MutableLiveData<List<String>> historyData = new MutableLiveData<>();
    public MutableLiveData<MovieResultsPage> movieData = new MutableLiveData<>();
    public MutableLiveData<TvShowResultsPage> tvData = new MutableLiveData<>();
    public MutableLiveData<CompanyResultsPage> companyData = new MutableLiveData<>();
    public MutableLiveData<CollectionResultsPage> collectionData = new MutableLiveData<>();
    public MutableLiveData<PersonResultsPage> personData = new MutableLiveData<>();
    public MutableLiveData<KeywordResultsPage> keywordData = new MutableLiveData<>();

    public SearchViewModule(@NonNull Application application, SearchRemoteDataSource dataSource) {
        super(application);
        this.application = application;
        historyName = new ArrayList<>();
        this.dataSource = dataSource;
    }

    public void getHistoryList() {
        if (historyName != null && historyName.size() == 0) {
            List<String> temp = (List<String>) SpUtils.getInstance(application.getApplicationContext()).get("search_history", null);
            if (temp != null) {
                historyName.addAll(temp);
            }
        }
        if (historyName != null && historyName.size() > 0) {
            historyData.setValue(historyName);
        }
    }

    public void deleteTag(int position) {
        if (historyName != null && historyName.size() > position) {
            historyName.remove(position);
            SpUtils.getInstance(application.getApplicationContext()).put("search_history", historyName);
            historyData.setValue(historyName);
        }
    }

    public void updateQuery(String query) {
        if (!TextUtils.isEmpty(query)) {
            queryData.setValue(query);
        }
    }

    public void search(int type, String query, int page) {
        switch (type) {
            case 1:
                dataSource.searchMovie(query, page).subscribe(movieResultsPage -> {
                    movieData.setValue(movieResultsPage);
                }, throwable -> {
                });
                break;
            case 2:
                dataSource.searchTv(query, page).subscribe(tvShowResultsPage -> {
                    tvData.setValue(tvShowResultsPage);
                }, throwable -> {
                });
                break;
            case 3:
                dataSource.searchCollections(query, page).subscribe(collectionResultsPage -> {
                    collectionData.setValue(collectionResultsPage);
                }, throwable -> {
                });
                break;
            case 4:
                dataSource.searchPeople(query, page).subscribe(personResultsPage -> {
                    personData.setValue(personResultsPage);
                }, throwable -> {
                });
                break;
            case 5:
                dataSource.searchCompany(query, page).subscribe(companyResultsPage -> {
                    companyData.setValue(companyResultsPage);
                }, throwable -> {
                });
                break;
            case 6:
                dataSource.searchKeywords(query, page).subscribe(keywordResultsPage -> {
                    keywordData.setValue(keywordResultsPage);
                }, throwable -> {
                });
                break;
        }
    }
}
