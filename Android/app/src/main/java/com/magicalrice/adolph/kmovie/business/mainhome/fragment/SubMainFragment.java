package com.magicalrice.adolph.kmovie.business.mainhome.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.ListPreloader.PreloadSizeProvider;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseFragment;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;
import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.Genre;
import com.magicalrice.adolph.kmovie.data.entities.VideoResultsPage;
import com.magicalrice.adolph.kmovie.databinding.FragmentSubMainHomeBinding;
import com.magicalrice.adolph.kmovie.utils.LUtils;
import com.magicalrice.adolph.kmovie.viewmodule.MainHomeViewModule;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.widget.adapter.MainMovieAdapter;
import com.magicalrice.adolph.kmovie.widget.adapter.MainTvAdapter;
import com.magicalrice.adolph.kmovie.widget.recyclerview.EndlessRecyclerOnScrollListener;
import com.magicalrice.adolph.kmovie.widget.recyclerview.LoadingFooter;
import com.magicalrice.adolph.kmovie.widget.recyclerview.MagicalRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Adolph on 2018/5/2.
 */

public class SubMainFragment extends BaseFragment<FragmentSubMainHomeBinding> {
    private MainHomeViewModule viewModule;
    private int totalPage = 0;
    private static final int REQUEST_COUNT = 20;
    private ArrayList<BaseVideo> videoList;
    //    private ArrayList<BaseMovie> movieList;
    private MainMovieAdapter movieAdapter;
    //    private MainTvAdapter tvAdapter;
    private Genre genre;
    private int page = 1;
    @Inject
    MainViewModuleFactory factory;
    private int type = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
        genre = getArguments().getParcelable("gener");
        viewModule = ViewModelProviders.of(this, factory).get(MainHomeViewModule.class);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        if (movieList != null && movieList.size() > 0) {
//            outState.putParcelableArrayList("movieList", movieList);
//        }
        if (videoList != null && videoList.size() > 0) {
            outState.putParcelableArrayList("videoList", videoList);
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_sub_main_home;
    }

    @Override
    public void createView(View view) {
        PreloadSizeProvider sizeProvider = new ViewPreloadSizeProvider();
        RecyclerViewPreloader preloader = null;
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        binding.recycler.setLayoutManager(manager);
//        if (type == 1) {
        videoList = new ArrayList<>();
        movieAdapter = new MainMovieAdapter(getActivity(), videoList);
        binding.recycler.setAdapter(movieAdapter);
        preloader = new RecyclerViewPreloader(this, movieAdapter, sizeProvider, 10);
//        } else if (type == 2) {
//        videoList = new ArrayList<>();
//        tvAdapter = new MainTvAdapter(getActivity(), videoList);
//        binding.recycler.setAdapter(tvAdapter);
//        preloader = new RecyclerViewPreloader(this, tvAdapter, sizeProvider, 10);
//        }
        if (preloader != null) {
            binding.recycler.addOnScrollListener(preloader);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
//            if (type == 1) {
            List<BaseVideo> temp = savedInstanceState.getParcelableArrayList("videoList");
            if (temp != null && temp.size() > 0) {
                videoList.addAll(temp);
                movieAdapter.notifyDataSetChanged();
            }
//            } else if (type == 2) {
//            List<BaseTvShow> temp = savedInstanceState.getParcelableArrayList("tvList");
//            if (temp != null && temp.size() > 0) {
//                vi.addAll(temp);
//                tvAdapter.notifyDataSetChanged();
//            }
//            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (genre != null) {
            if (type == 1 && savedInstanceState == null) {
                viewModule.getMovies(genre.getId(), page);

            } else if (type == 2 && savedInstanceState == null) {
                VideoResultsPage result = new VideoResultsPage();
                result.setId(1);
                result.setPage(30);
                result.setTotal_pages(100);
                result.setTotal_results(3132);
                List<BaseVideo> videoList = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    BaseVideo video = new BaseVideo();
                    video.setTitle("name" + 1);
                    video.setBackdrop_path("");
                    video.setPoster_path("");
                    video.setRelease_date("1995-6-"+i);
                    video.setId(i);
                    videoList.add(video);
                }
                result.setResults(videoList);
                viewModule.videoData.setValue(result);
            }
            viewModule.videoData.observe(this, videoResultsPage -> {
                List<BaseVideo> temp = videoResultsPage.getResults();
                videoList.addAll(temp);
                movieAdapter.notifyItemRangeInserted(videoList.size() - temp.size(), temp.size());

            });
//                viewModule.tvData.observe(this, tvShowResultsPage -> {
//                    List<BaseTvShow> temp = tvShowResultsPage.getResults();
//                    tvList.addAll(temp);
//                    for (BaseTvShow bean : tvList) {
//                        Toast.makeText(getActivity(),bean.getName(),Toast.LENGTH_SHORT).show();
//                    }
//                    LUtils.e("list:%d",tvList.size());
//                    tvAdapter.notifyDataSetChanged();
//                });
//            if (type == 2 && savedInstanceState == null) {
//                viewModule.getTvs(genre.getId(), page);
//                viewModule.tvData.observe(this, tvShowResultsPage -> {
//                    List<BaseTvShow> temp = tvShowResultsPage.getResults();
//                    tvList.addAll(temp);
//                    for (BaseTvShow bean : tvList) {
//                        Toast.makeText(getActivity(),bean.getName(),Toast.LENGTH_SHORT).show();
//                    }
//                    LUtils.e("list:%d",tvList.size());
//                    tvAdapter.notifyDataSetChanged();
//                });
//            }

        }
    }

    private void requestNewData() {

    }

    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {

        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
//
//            if (type == 1 && movieAdapter != null) {
//                @LoadingFooter.State int state = movieAdapter.getFooterViewState();
//                if (state == LoadingFooter.LOADING) {
//                    Log.d("@Cundong", "the state is Loading, just wait..");
//                    return;
//                }
//
//                if (page < totalPage) {
//                    // loading more
//                    movieAdapter.setFooterViewState(REQUEST_COUNT, LoadingFooter.LOADING, null);
//                    requestNewData();
//                } else {
//                    //the end
//                    movieAdapter.setFooterViewState(REQUEST_COUNT, LoadingFooter.THE_END, null);
//                }
//            } else if (type == 2 && tvAdapter != null) {
//                @LoadingFooter.State int state = tvAdapter.getFooterViewState();
//                if(state == LoadingFooter.LOADING) {
//                    Log.d("@Cundong", "the state is Loading, just wait..");
//                    return;
//                }
//
//                if (page < totalPage) {
//                    // loading more
//                    tvAdapter.setFooterViewState(REQUEST_COUNT, LoadingFooter.LOADING, null);
//                    requestNewData();
//                } else {
//                    //the end
//                    movieAdapter.setFooterViewState(REQUEST_COUNT, LoadingFooter.THE_END, null);
//                }
//            }

        }
    };
}
