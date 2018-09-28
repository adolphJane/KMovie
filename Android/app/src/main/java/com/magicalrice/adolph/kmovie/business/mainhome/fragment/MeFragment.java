package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseDaggerFragment;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.business.mainhome.MainHomeActivity;
import com.magicalrice.adolph.kmovie.business.movie_detail.VideoDetailActivity;
import com.magicalrice.adolph.kmovie.business.movie_role.VideoRoleActivity;
import com.magicalrice.adolph.kmovie.data.entities.MovieDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.RoleDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.TvShowDetailBean;
import com.magicalrice.adolph.kmovie.databinding.FragmentMainHomeMeBinding;
import com.magicalrice.adolph.kmovie.viewmodule.MainMeViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.widget.adapter.LoveAdapter;
import com.magicalrice.adolph.kmovie.widget.view.EmptyView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by Adolph on 2018/4/19.
 */

public class MeFragment extends BaseDaggerFragment<FragmentMainHomeMeBinding> {
    @Inject
    MainViewModuleFactory factory;
    private MainMeViewModule viewModule;
    private LoveAdapter<MovieDetailBean> loveMovieAdapter;
    private LoveAdapter<TvShowDetailBean> loveTvAdapter;
    private LoveAdapter<RoleDetailBean> loveRoleAdapter;
    private List<MovieDetailBean> movieDetailBeanList = new ArrayList<>();
    private List<TvShowDetailBean> tvShowDetailBeanList = new ArrayList<>();
    private List<RoleDetailBean> roleDetailBeanList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModule = ViewModelProviders.of(this, factory).get(MainMeViewModule.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main_home_me;
    }

    @Override
    public void createView(View view) {
        GlideApp.with(this)
                .load(R.drawable.img_avastar)
                .circleCrop()
                .into(binding.imgAvastar);
        binding.loveMovieList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        loveMovieAdapter = new LoveAdapter(R.layout.layout_item_love_video, movieDetailBeanList);
        loveMovieAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MovieDetailBean bean = (MovieDetailBean) adapter.getData().get(position);
                VideoDetailActivity.startActivity(getActivity(), bean.getMovieDetailId(), bean.getMovie().getOverview(), 1);
            }
        });
        binding.loveMovieList.setAdapter(loveMovieAdapter);

        binding.loveTvList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        loveTvAdapter = new LoveAdapter(R.layout.layout_item_love_video, tvShowDetailBeanList);
        binding.loveTvList.setAdapter(loveTvAdapter);
        loveTvAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TvShowDetailBean bean = (TvShowDetailBean) adapter.getData().get(position);
                VideoDetailActivity.startActivity(getActivity(), bean.getTvId(), bean.getTvShow().getOverview(), 2);
            }
        });

        binding.loveActorList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        loveRoleAdapter = new LoveAdapter(R.layout.layout_item_love_video, roleDetailBeanList);
        binding.loveActorList.setAdapter(loveRoleAdapter);
        loveRoleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RoleDetailBean bean = (RoleDetailBean) adapter.getData().get(position);
                VideoRoleActivity.startActivity(getActivity(), bean.getRoleId());
            }
        });

        updateLoveMovie();
        updateLoveTv();
        updateLoveRole();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (getActivity() != null) {
                ((MainHomeActivity)getActivity()).hideFabButton();
            }
            updateLoveMovie();
            updateLoveTv();
            updateLoveRole();
        }
    }

    private void updateLoveMovie() {
        viewModule.getLoveMovie().observe(this, movieDetailBeans -> {
            if (movieDetailBeans != null && movieDetailBeans.size() > 0) {
                movieDetailBeanList.clear();
                movieDetailBeanList.addAll(movieDetailBeans);
                loveMovieAdapter.notifyDataSetChanged();
            } else {
                EmptyView emptyView = new EmptyView(getContext());
                emptyView.setText("暂无喜欢的电影");
                loveMovieAdapter.setEmptyView(emptyView);
            }
        });
    }

    private void updateLoveTv() {
        viewModule.getLoveTv().observe(this, tvShowDetailBeans -> {
            if (tvShowDetailBeans != null && tvShowDetailBeans.size() > 0) {
                tvShowDetailBeanList.clear();
                tvShowDetailBeanList.addAll(tvShowDetailBeans);
                loveTvAdapter.notifyDataSetChanged();
            } else {
                EmptyView emptyView = new EmptyView(getContext());
                emptyView.setText("暂无喜欢的电视节目");
                loveTvAdapter.setEmptyView(emptyView);
            }
        });
    }

    private void updateLoveRole() {
        viewModule.getLoveRole().observe(this, roleDetailBeans -> {
            if (roleDetailBeans != null && roleDetailBeans.size() > 0) {
                roleDetailBeanList.clear();
                roleDetailBeanList.addAll(roleDetailBeans);
                loveRoleAdapter.notifyDataSetChanged();
            } else {
                EmptyView emptyView = new EmptyView(getContext());
                emptyView.setText("暂无喜欢的演员");
                loveRoleAdapter.setEmptyView(emptyView);
            }
        });
    }
}
