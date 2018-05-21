package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
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
    private SearchRemoteDataSource dataSource;
    public MutableLiveData<String> queryData = new MutableLiveData<>();
    public MutableLiveData<List<String>> historyData = new MutableLiveData<>();
    public MutableLiveData<MovieResultsPage> movieSearchData = new MutableLiveData<>();
    public MutableLiveData<TvShowResultsPage> tvSearchData = new MutableLiveData<>();
    public MutableLiveData<CompanyResultsPage> companySearchData = new MutableLiveData<>();
    public MutableLiveData<CollectionResultsPage> collectionSearchData = new MutableLiveData<>();
    public MutableLiveData<PersonResultsPage> personSearchData = new MutableLiveData<>();
    public MutableLiveData<KeywordResultsPage> keywordSearchData = new MutableLiveData<>();

    public SearchViewModule(@NonNull Application application, SearchRemoteDataSource dataSource) {
        super(application);
        historyName = new ArrayList<>();
        this.dataSource = dataSource;
    }

    public void getHistoryList() {
        if (historyName != null && historyName.size() == 0) {
            List<String> temp = (List<String>) SpUtils.getInstance(getApplication().getApplicationContext()).get("search_history", new ArrayList<>());
            if (temp != null) {
                historyName.clear();
                historyName.addAll(temp);
            }
        }
        if (historyName != null && historyName.size() > 0) {
            historyData.setValue(historyName);
        }
    }

    public void updateQuery(String query) {
        if (!TextUtils.isEmpty(query)) {
            queryData.postValue(query);
        }
    }

    public void search(int type, String query, int page) {
        switch (type) {
            case 1:
                dataSource.searchMovie(query, page).subscribe(movieResultsPage -> {
                    movieSearchData.setValue(movieResultsPage);
                    updateHistory(query, true);
                }, throwable -> {
                });
                break;
            case 2:
                dataSource.searchTv(query, page).subscribe(tvShowResultsPage -> {
                    tvSearchData.setValue(tvShowResultsPage);
                    updateHistory(query, true);
                }, throwable -> {
                });
                break;
            case 3:
                dataSource.searchCollections(query, page).subscribe(collectionResultsPage -> {
                    collectionSearchData.setValue(collectionResultsPage);
                    updateHistory(query, true);
                }, throwable -> {
                });
                break;
            case 4:
                dataSource.searchPeople(query, page).subscribe(personResultsPage -> {
                    personSearchData.setValue(personResultsPage);
                    updateHistory(query, true);
                }, throwable -> {
                });
                break;
            case 5:
                dataSource.searchCompany(query, page).subscribe(companyResultsPage -> {
                    companySearchData.setValue(companyResultsPage);
                    updateHistory(query, true);
                }, throwable -> {
                });
                break;
        }
    }

    public void updateHistory(String query, boolean isAdded) {
        if (isAdded) {
            if (historyName.size() > 5) {
                historyName = historyName.subList(historyName.size() - 5, historyName.size() - 1);
            } else if (historyName.size() == 5) {
                historyName.remove(0);
            }
            if (!historyName.contains(query)) {
                historyName.add(query);
            }
        } else {
            if (historyName != null && historyName.contains(query)) {
                historyName.remove(query);
            }
        }
        SpUtils.getInstance(getApplication()).put("search_history", historyName);
        historyData.setValue(historyName);
    }
}
