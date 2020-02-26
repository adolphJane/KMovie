package com.magicalrice.adolph.kmovie.widget.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.magicalrice.adolph.kmovie.business.mainhome.fragment.SubMainFragment;
import com.magicalrice.adolph.kmovie.data.entities.Genre;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adolph on 2018/5/2.
 */

public class MainHomeAdapter extends FragmentStatePagerAdapter {
    private List<Genre> datas = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    public void addDatas(List<Genre> datas,int type) {
        this.datas.clear();
        this.datas.addAll(datas);
        for (Genre bean : datas) {
            SubMainFragment fragment = new SubMainFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("type",type);
            bundle.putParcelable("gener",bean);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
    }

    public MainHomeAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
