package com.magicalrice.adolph.kmovie.business.movie_role;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.gson.JsonPrimitive;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.data.entities.Image;
import com.magicalrice.adolph.kmovie.data.entities.Person;
import com.magicalrice.adolph.kmovie.data.entities.PersonMovieCredits;
import com.magicalrice.adolph.kmovie.data.entities.PersonDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.PersonImages;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.databinding.ActivityMovieRoleBinding;
import com.magicalrice.adolph.kmovie.utils.ChineseEnglishUtil;
import com.magicalrice.adolph.kmovie.utils.Utils;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.viewmodule.RoleViewModule;
import com.magicalrice.adolph.kmovie.widget.adapter.PhotoAdapter;
import com.magicalrice.adolph.kmovie.widget.adapter.RoleVideoAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/5/8.
 */

public class VideoRoleActivity extends BaseActivity<ActivityMovieRoleBinding> implements VideoRoleEvent{
    private RoleViewModule viewModule;
    private PhotoAdapter photoAdapter;
    private RoleVideoAdapter movieAdapter,tvAdapter;
    private int personId;
    @Inject
    MainViewModuleFactory factory;

    public static void startActivity(FragmentActivity activity, int personId) {
        Intent intent = new Intent(activity, VideoRoleActivity.class);
        intent.putExtra("personId", personId);
        activity.startActivity(intent);
    }

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
        binding.setSelectItem(1);
        viewModule.getStarSummary(personId);
        viewModule.personData.observe(this,personDetailBean -> {
            updateView(personDetailBean);
        });
    }

    private void updateView(PersonDetailBean bean) {
        if (bean == null) {
            return;
        }
        PersonMovieCredits movieCredits = bean.getMovieCredits();
        PersonMovieCredits tvCredits = bean.getTvCredits();
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
            getChiName(person.getAlso_known_as());
            binding.tvStarGender.setText(Utils.flipTextColor(this,"性别:" + (person.getGender() == 2 ? "男" : "女"),":",R.color.yellow_1));
            binding.tvStarBirthday.setText(Utils.flipTextColor(this,"出生日期:" + (person.getBirthday() == null ? "----" : Utils.getDateTime("yyyy年MM月dd日",person.getBirthday())),":",R.color.yellow_1));
            binding.tvStarDeath.setText(Utils.flipTextColor(this,"死亡日期:" + (person.getDeathday() == null ? "----" : Utils.getDateTime("yyyy年MM月dd日",person.getBirthday())),":",R.color.yellow_1));
            binding.tvRoleBiography.setText(TextUtils.isEmpty(person.getBiography()) ? "无" : person.getBiography());
        }

        if (images != null) {
            List<Image> photoImages = images.getProfiles();
            photoAdapter = new PhotoAdapter(R.layout.item_movie_photo,photoImages);
            binding.ryPhotos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            binding.ryPhotos.setAdapter(photoAdapter);
        }
    }

    private void getChiName(List<JsonPrimitive> knownAs) {
        if (knownAs != null) {
            for (JsonPrimitive primitive : knownAs) {
                if (ChineseEnglishUtil.isChinese(primitive.getAsString())) {
                    binding.tvTranslatedName.setText(Utils.flipTextColor(this,"译名:" + primitive.getAsString(),":",R.color.yellow_1));
                    return;
                }
            }
            binding.tvTranslatedName.setText(Utils.flipTextColor(this,"译名:无",":",R.color.yellow_1));
        }
    }

    @Override
    public void selectItem(int position) {
        binding.setSelectItem(position);
    }
}
