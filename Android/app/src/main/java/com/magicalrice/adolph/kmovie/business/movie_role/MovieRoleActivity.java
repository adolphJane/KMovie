package com.magicalrice.adolph.kmovie.business.movie_role;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.databinding.ActivityMovieRoleBinding;

/**
 * Created by Adolph on 2018/5/8.
 */

public class MovieRoleActivity extends BaseActivity<ActivityMovieRoleBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_movie_role;
    }
}
