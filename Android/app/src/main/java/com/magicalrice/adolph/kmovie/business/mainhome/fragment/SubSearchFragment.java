package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseFragment;
import com.magicalrice.adolph.kmovie.databinding.FragmentSubSearchBinding;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.viewmodule.SearchViewModule;
import com.magicalrice.adolph.kmovie.widget.adapter.SearchResultsAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/5/9.
 */

public class SubSearchFragment<T,K> extends BaseFragment<FragmentSubSearchBinding> {
    @Inject
    MainViewModuleFactory factory;
    private SearchViewModule viewModule;
    private SearchResultsAdapter<K> adapter;
    private int type;
    private int page = 1;
    private String query;
    private List<K> dataList;
    private T data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
        viewModule = ViewModelProviders.of(this,factory).get(SearchViewModule.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_sub_search;
    }

    @Override
    public void createView(View view) {
        dataList = new ArrayList<>();
        adapter = new SearchResultsAdapter<>(R.layout.item_search_layout,dataList,type,getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.searchResultsList.setAdapter(adapter);
        binding.searchResultsList.setLayoutManager(manager);
        initData();
    }

    private void initData() {
        viewModule.queryData.observe(this,s -> {
            viewModule.search(type,s,page);
        });
        switch (type) {
            case 1:
                viewModule.movieData.observe(this,movieResultsPage -> {

                });
                break;
            case 2:
                viewModule.tvData.observe(this,tvShowResultsPage -> {

                });
                break;
            case 3:
                viewModule.collectionData.observe(this,collectionResultsPage -> {

                });
                break;
            case 4:
                viewModule.personData.observe(this,personResultsPage -> {

                });
                break;
            case 5:
                viewModule.companyData.observe(this,companyResultsPage -> {

                });
                break;
            case 6:
                viewModule.keywordData.observe(this,keywordResultsPage -> {

                });
                break;
        }
    }
}
