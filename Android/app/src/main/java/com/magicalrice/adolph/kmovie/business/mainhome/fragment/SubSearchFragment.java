package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseFragment;
import com.magicalrice.adolph.kmovie.business.mainhome.MainHomeActivity;
import com.magicalrice.adolph.kmovie.business.movie_detail.VideoDetailActivity;
import com.magicalrice.adolph.kmovie.business.movie_role.VideoRoleActivity;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.BasePerson;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;
import com.magicalrice.adolph.kmovie.data.entities.CollectionResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.CompanyResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.PersonResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.databinding.FragmentSubSearchBinding;
import com.magicalrice.adolph.kmovie.viewmodule.SearchViewModule;
import com.magicalrice.adolph.kmovie.widget.adapter.SearchResultsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adolph on 2018/5/9.
 */

public class SubSearchFragment<T, K> extends BaseFragment<FragmentSubSearchBinding> {

    private SearchViewModule viewModule;
    private SearchResultsAdapter<K> adapter;
    private int type;
    private int page = 1;
    private List<K> dataList;
    private T data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
        if (getActivity() != null) {
            viewModule = ViewModelProviders.of(getActivity(), ((MainHomeActivity) getActivity()).getFactory()).get(SearchViewModule.class);
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_sub_search;
    }

    @Override
    public void createView(View view) {
        dataList = new ArrayList<>();
        adapter = new SearchResultsAdapter<>(R.layout.item_search_layout, dataList, type);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_more) {
                    int type = ((SearchResultsAdapter) adapter).getType();
                    if (type == 1) {
                        VideoDetailActivity.startActivity(getActivity(), ((BaseMovie)adapter.getItem(position)).getId(),((BaseMovie)adapter.getItem(position)).getOverview(),1);
                    } else if (type == 2) {
                        VideoDetailActivity.startActivity(getActivity(), ((BaseTvShow)adapter.getItem(position)).getId(),((BaseTvShow)adapter.getItem(position)).getOverview(),2);
                    } else if (type == 4) {
                        VideoRoleActivity.startActivity(getActivity(), ((BasePerson)adapter.getItem(position)).getId());
                    }
                }
            }
        });
        ListPreloader.PreloadSizeProvider sizeProvider = new ViewPreloadSizeProvider();
        RecyclerViewPreloader preloader = null;
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.searchResultsList.setAdapter(adapter);
        binding.searchResultsList.setLayoutManager(manager);
        preloader = new RecyclerViewPreloader(this, adapter, sizeProvider, 10);
        if (preloader != null) {
            binding.searchResultsList.addOnScrollListener(preloader);
        }
        initData();
    }

    private void initData() {
        viewModule.queryData.observe(this, s -> {
            viewModule.search(type, s, page);
        });
        switch (type) {
            case 1:
                viewModule.movieSearchData.observe(this, movieResultsPage -> {
                    data = (T) movieResultsPage;
                    if (data != null && ((MovieResultsPage) data).getResults() != null && ((MovieResultsPage) data).getResults().size() > 0) {
                        adapter.setNewData((List<K>) movieResultsPage.getResults());
                    }
                    if (movieResultsPage.getResults().size() > 0) {
                        binding.setShowNoResults(false);
                    } else {
                        binding.setShowNoResults(true);
                    }
                });
                break;
            case 2:
                viewModule.tvSearchData.observe(this, tvShowResultsPage -> {
                    data = (T) tvShowResultsPage;
                    if (data != null && ((TvShowResultsPage) data).getResults() != null && ((TvShowResultsPage) data).getResults().size() > 0) {
                        adapter.setNewData((List<K>) tvShowResultsPage.getResults());
                    }
                    if (tvShowResultsPage.getResults().size() > 0) {
                        binding.setShowNoResults(false);
                    } else {
                        binding.setShowNoResults(true);
                    }
                });
                break;
            case 3:
                viewModule.collectionSearchData.observe(this, collectionResultsPage -> {
                    data = (T) collectionResultsPage;
                    if (data != null && ((CollectionResultsPage) data).getResults() != null && ((CollectionResultsPage) data).getResults().size() > 0) {
                        adapter.setNewData((List<K>) collectionResultsPage.getResults());
                    }
                    if (collectionResultsPage.getResults().size() > 0) {
                        binding.setShowNoResults(false);
                    } else {
                        binding.setShowNoResults(true);
                    }
                });
                break;
            case 4:
                viewModule.personSearchData.observe(this, personResultsPage -> {
                    data = (T) personResultsPage;
                    if (data != null && ((PersonResultsPage) data).getResults() != null && ((PersonResultsPage) data).getResults().size() > 0) {
                        adapter.setNewData((List<K>) personResultsPage.getResults());
                    }
                    if (personResultsPage.getResults().size() > 0) {
                        binding.setShowNoResults(false);
                    } else {
                        binding.setShowNoResults(true);
                    }
                });
                break;
            case 5:
                viewModule.companySearchData.observe(this, companyResultsPage -> {
                    data = (T) companyResultsPage;
                    if (data != null && ((CompanyResultsPage) data).getResults() != null && ((CompanyResultsPage) data).getResults().size() > 0) {
                        adapter.setNewData((List<K>) companyResultsPage.getResults());
                    }
                    if (companyResultsPage.getResults().size() > 0) {
                        binding.setShowNoResults(false);
                    } else {
                        binding.setShowNoResults(true);
                    }
                });
                break;
        }
    }
}
