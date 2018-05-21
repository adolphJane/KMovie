package com.magicalrice.adolph.kmovie.business.movie_role;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.data.entities.Person;
import com.magicalrice.adolph.kmovie.data.entities.PersonCredits;
import com.magicalrice.adolph.kmovie.data.entities.PersonDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.PersonImages;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.databinding.ActivityMovieRoleBinding;
import com.magicalrice.adolph.kmovie.utils.Utils;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.viewmodule.RoleViewModule;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/5/8.
 */

public class VideoRoleActivity extends BaseActivity<ActivityMovieRoleBinding> {
    private RoleViewModule viewModule;
    private int personId;
    @Inject
    MainViewModuleFactory factory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personId = getIntent().getIntExtra("personId",-1);
        if (personId == -1) {
            return;
        }
        viewModule = ViewModelProviders.of(this,factory).get(RoleViewModule.class);
        initData();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_movie_role;
    }

    private void initData() {
        viewModule.getStarSummary(personId);
        viewModule.personData.observe(this,personDetailBean -> {
            updateView(personDetailBean);
        });
    }

    private void updateView(PersonDetailBean bean) {
        if (bean == null) {
            return;
        }
        PersonCredits movieCredits = bean.getMovieCredits();
        PersonCredits tvCredits = bean.getTvCredits();
        Person person = bean.getPerson();
        PersonImages images = bean.getPersonImages();

        if (person != null) {
            GlideApp.with(this)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + person.getProfile_path())
                    .placeholder(R.drawable.item_img_placeholder)
                    .error(R.drawable.item_img_error)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.imgPoster);
            binding.tvStarTitle.setText(Utils.flipTextColor(this,"演员名:" + person.getName(),":",R.color.yellow_1));
            person.getAlso_known_as();
        }
    }

    private void getChiName() {

    }
}
