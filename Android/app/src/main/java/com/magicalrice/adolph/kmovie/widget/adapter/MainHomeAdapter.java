package com.magicalrice.adolph.kmovie.widget.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.magicalrice.adolph.kmovie.data.entities.Genre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adolph on 2018/5/2.
 */

public class MainHomeAdapter extends FragmentStatePagerAdapter {
    private List<Genre> datas = new ArrayList<>();

    public void addDatas(List<Genre> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
    }

    public MainHomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
