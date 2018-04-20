package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.magicalrice.adolph.kmovie.data.datasource.TvRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;

import java.util.List;

/**
 * Created by Adolph on 2018/4/20.
 */

public class TvViewModule extends AndroidViewModel {
    private TvRemoteDataSource tvSource;
    private Application application;
    public MutableLiveData<Integer> tvTotal = new MutableLiveData<>();
    public MutableLiveData<Integer> tvPageTotal = new MutableLiveData<>();
    public MutableLiveData<Integer> tvPageCurrent = new MutableLiveData<>();
    public MutableLiveData<List<BaseTvShow>> tvList = new MutableLiveData<>();

    public TvViewModule(@NonNull Application application, TvRemoteDataSource tvSource) {
        super(application);
        this.application = application;
        this.tvSource = tvSource;
    }

    public void getPopularTv(int page) {
        tvSource.getPopularTv(page).subscribe(tvShowResultsPage -> {
            tvTotal.setValue(tvShowResultsPage.getTotal_results());
            tvPageTotal.setValue(tvShowResultsPage.getTotal_pages());
            tvPageCurrent.setValue(tvShowResultsPage.getPage());
            tvList.setValue(tvShowResultsPage.getResults());
        },throwable -> Toast.makeText(application,throwable.getCause().getMessage(),Toast.LENGTH_SHORT).show());
    }
}
