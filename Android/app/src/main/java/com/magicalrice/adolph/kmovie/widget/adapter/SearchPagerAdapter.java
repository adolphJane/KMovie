package com.magicalrice.adolph.kmovie.widget.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.magicalrice.adolph.kmovie.business.mainhome.fragment.SubSearchFragment;
import com.magicalrice.adolph.kmovie.data.entities.BaseCollection;
import com.magicalrice.adolph.kmovie.data.entities.BaseCompany;
import com.magicalrice.adolph.kmovie.data.entities.BaseKeyword;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.BasePerson;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;
import com.magicalrice.adolph.kmovie.data.entities.CollectionResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.CompanyResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.KeywordResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.PersonResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;

import java.util.ArrayList;
import java.util.List;


public class SearchPagerAdapter extends FragmentPagerAdapter{
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public SearchPagerAdapter(FragmentManager fm) {
        super(fm);
        for (int i = 0;i < 6;i++) {
            SubSearchFragment fragment;
            switch (i) {
                case 0:
                    fragment = new SubSearchFragment<MovieResultsPage,BaseMovie>();
                    break;
                case 1:
                    fragment = new SubSearchFragment<TvShowResultsPage,BaseTvShow>();
                    break;
                case 2:
                    fragment = new SubSearchFragment<CompanyResultsPage,BaseCompany>();
                    break;
                case 3:
                    fragment = new SubSearchFragment<CollectionResultsPage,BaseCollection>();
                    break;
                case 4:
                    fragment = new SubSearchFragment<PersonResultsPage,BasePerson>();
                    break;
                case 5:
                    fragment = new SubSearchFragment<KeywordResultsPage,BaseKeyword>();
                    break;
                default:
                    fragment = new SubSearchFragment();
                    break;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("type",i + 1);
            fragment.setArguments(bundle);
            mFragmentList.add(fragment);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
