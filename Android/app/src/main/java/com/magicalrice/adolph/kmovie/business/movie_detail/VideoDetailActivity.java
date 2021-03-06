package com.magicalrice.adolph.kmovie.business.movie_detail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.magicalrice.adolph.kmovie.R;
import com.magicalrice.adolph.kmovie.base.BaseActivity;
import com.magicalrice.adolph.kmovie.base.GlideApp;
import com.magicalrice.adolph.kmovie.business.movie_role.VideoRoleActivity;
import com.magicalrice.adolph.kmovie.business.other.TextDisplayBoxFragment;
import com.magicalrice.adolph.kmovie.data.datasource.CountryDataSource;
import com.magicalrice.adolph.kmovie.data.entities.BaseKeyword;
import com.magicalrice.adolph.kmovie.data.entities.CastMember;
import com.magicalrice.adolph.kmovie.data.entities.Credits;
import com.magicalrice.adolph.kmovie.data.entities.CrewMember;
import com.magicalrice.adolph.kmovie.data.entities.Genre;
import com.magicalrice.adolph.kmovie.data.entities.Image;
import com.magicalrice.adolph.kmovie.data.entities.Images;
import com.magicalrice.adolph.kmovie.data.entities.Keywords;
import com.magicalrice.adolph.kmovie.data.entities.Movie;
import com.magicalrice.adolph.kmovie.data.entities.MovieDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDatesResult;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDatesResults;
import com.magicalrice.adolph.kmovie.data.entities.TvShow;
import com.magicalrice.adolph.kmovie.data.entities.TvShowDetailBean;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.data.remote.ApiConstants;
import com.magicalrice.adolph.kmovie.databinding.ActivityMovieDetailBinding;
import com.magicalrice.adolph.kmovie.utils.ScreenUtils;
import com.magicalrice.adolph.kmovie.utils.Utils;
import com.magicalrice.adolph.kmovie.viewmodule.MainViewModuleFactory;
import com.magicalrice.adolph.kmovie.viewmodule.VideoDetailViewModule;
import com.magicalrice.adolph.kmovie.widget.BottomShareDialog;
import com.magicalrice.adolph.kmovie.widget.adapter.MovieReSiAdapter;
import com.magicalrice.adolph.kmovie.widget.adapter.PhotoAdapter;
import com.magicalrice.adolph.kmovie.widget.adapter.ReleaseDateAdapter;
import com.magicalrice.adolph.kmovie.widget.adapter.StarAdapter;
import com.magicalrice.adolph.kmovie.widget.adapter.TvReSiAdapter;
import com.magicalrice.adolph.kmovie.widget.view.EmptyView;

import java.util.List;

import javax.inject.Inject;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

/**
 * Created by Adolph on 2018/5/7.
 */

public class VideoDetailActivity extends BaseActivity<ActivityMovieDetailBinding> implements VideoDetailEvent {
    private VideoDetailViewModule viewModule;
    private ReleaseDateAdapter dateAdapter;
    private StarAdapter starAdapter;
    private PhotoAdapter photoBackAdapter, photoPosterAdapter;
    private MovieReSiAdapter recommendMovieAdapter, similarMovieAdapter;
    private TvReSiAdapter recommendTvAdapter, similarTvAdapter;
    private int type;
    private long videoId;
    private String overview;
    private BottomShareDialog shareDialog;
    private String imgPoster;
    private String title;
    private MovieDetailBean movieDetailBean;
    private TvShowDetailBean tvShowDetailBean;
    @Inject
    MainViewModuleFactory factory;
    @Inject
    CountryDataSource countrySource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getIntExtra("type", -1);
        videoId = getIntent().getLongExtra("videoId", -1);
        overview = getIntent().getStringExtra("overview");
        if (type == -1 || videoId == -1) {
            this.finish();
            return;
        }
        viewModule = new ViewModelProvider(this, factory).get(VideoDetailViewModule.class);
        initData();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_movie_detail;
    }

    private void initData() {
        binding.setSelectOne(1);
        binding.setSelectTwo(1);
        binding.setEvent(this);
        binding.setType(type);
        binding.setIsLoading(true);
        if (type == 1) {
            viewModule.getMovieDetail(videoId);
        } else if (type == 2) {
            viewModule.getTVDetail(videoId);
        }
        viewModule.movieDetailLiveData.observe(this, movieDetailBean -> {
            updateView(movieDetailBean);
        });
        viewModule.tvDetailLiveData.observe(this, tvShowDetailBean -> {
            updateView(tvShowDetailBean);
        });
    }

    private void updateView(MovieDetailBean bean) {
        binding.setIsLoading(false);
        if (bean == null) {
            return;
        }
        movieDetailBean = bean;
        Keywords keywords = bean.getKeywords();
        Credits credits = bean.getCredits();
        Images images = bean.getImages();
        ReleaseDatesResults datesResults = bean.getDatesResults();
        Movie movie = bean.getMovie();
        MovieResultsPage recommend = bean.getRecommendationResult();
        MovieResultsPage similar = bean.getSimilarResult();
        binding.ryPhotos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.ryVideos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.setIsLike(bean.isLike());
        if (movie != null) {
            imgPoster = movie.getPoster_path();
            title = movie.getTitle();
            GlideApp.with(this)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + movie.getPoster_path())
                    .placeholder(R.drawable.item_img_placeholder)
                    .error(R.drawable.item_img_error)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.imgPoster);
            binding.tvMovieTitle.setText(movie.getTitle());
            binding.tvMovieRelease.setText(Utils.flipTextColor(this,"发行日期:" + movie.getRelease_date(),":",R.color.yellow_1));
            binding.tvContentContent.setText(TextUtils.isEmpty(movie.getOverview()) ? (TextUtils.isEmpty(overview) ? "无" : overview) : "    " + movie.getOverview());
            List<Genre> genres = movie.getGenres();
            if (genres != null && genres.size() > 0) {
                showGenreTag(StreamSupport.stream(genres).map(genre -> genre.getName()).collect(Collectors.toList()), binding.rlGenre);
            } else if (genres != null && genres.size() == 0) {
                showNoTag(false,binding.rlGenre);
            }
            ((TextView) findViewById(R.id.tv_original_title_data)).setText(TextUtils.isEmpty(movie.getOriginal_title()) ? "无" : movie.getOriginal_title());
            ((TextView) findViewById(R.id.tv_original_lanuage_data)).setText(TextUtils.isEmpty(movie.getOriginal_language()) ? "无" : movie.getOriginal_language());
            ((TextView) findViewById(R.id.tv_time_data)).setText(movie.getRuntime() == 0 ? "无" : Utils.getTime(movie.getRuntime()));
            ((TextView) findViewById(R.id.tv_budget_data)).setText(movie.getBudget() == 0 ? "无" : Utils.getRevenue(movie.getBudget()) + "");
            ((TextView) findViewById(R.id.tv_box_office_data)).setText(movie.getRevenue() == 0 ? "无" : Utils.getRevenue(movie.getRevenue()) + "");
            ((TextView) findViewById(R.id.tv_homepage_data)).setText(TextUtils.isEmpty(movie.getHomepage()) ? "无" : movie.getHomepage());
        }

        if (datesResults != null) {
            List<ReleaseDatesResult> dataList = datesResults.getResults();
            if (dataList != null && dataList.size() > 0) {
                dateAdapter = new ReleaseDateAdapter(R.layout.item_release_date_layout, dataList, countrySource);
                binding.ryShowTime.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                binding.ryShowTime.setAdapter(dateAdapter);
            }
        }

        if (similar != null) {
            similarMovieAdapter = new MovieReSiAdapter(R.layout.item_search_layout, similar.getResults());
            EmptyView emptyView = new EmptyView(this);
            emptyView.setText("暂无相关电影");
            similarMovieAdapter.setEmptyView(emptyView);
            binding.ryVideos.setAdapter(similarMovieAdapter);
        }

        if (recommend != null) {
            recommendMovieAdapter = new MovieReSiAdapter(R.layout.item_search_layout, recommend.getResults());
            EmptyView emptyView = new EmptyView(this);
            emptyView.setText("暂无推荐电影");
            recommendMovieAdapter.setEmptyView(emptyView);
        }

        updateCast(credits);
        updateKeywords(keywords);
        updateImages(images);
    }

    private void updateView(TvShowDetailBean bean) {
        binding.setIsLoading(false);
        if (bean == null) {
            return;
        }
        tvShowDetailBean = bean;
        Keywords keywords = bean.getKeywords();
        Credits credits = bean.getCredits();
        TvShowResultsPage recommend = bean.getTvRecommendation();
        TvShowResultsPage similar = bean.getTvSimilar();
        Images images = bean.getImages();
        TvShow tvShow = bean.getTvShow();
        binding.ryPhotos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.ryVideos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.setIsLike(bean.isLike());

        if (tvShow != null) {
            imgPoster = tvShow.getPoster_path();
            title = tvShow.getName();
            GlideApp.with(this)
                    .load(ApiConstants.TMDB_IMAGE_PATH + "w400" + tvShow.getPoster_path())
                    .placeholder(R.drawable.item_img_placeholder)
                    .error(R.drawable.item_img_error)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.imgPoster);
            binding.tvMovieTitle.setText(tvShow.getName());
            binding.tvContentContent.setText(TextUtils.isEmpty(tvShow.getOverview()) ? (TextUtils.isEmpty(overview) ? "无" : overview) : "    " + tvShow.getOverview());
            List<Genre> genres = tvShow.getGenres();
            if (genres != null && genres.size() > 0) {
                showGenreTag(StreamSupport.stream(genres).map(genre -> genre.getName()).collect(Collectors.toList()), binding.rlGenre);
            } else if (genres != null && genres.size() == 0) {
                showNoTag(false,binding.rlGenre);
            }
            ((TextView) findViewById(R.id.tv_original_title_data)).setText(TextUtils.isEmpty(tvShow.getOriginal_name()) ? "无" : tvShow.getOriginal_name());
            ((TextView) findViewById(R.id.tv_original_lanuage_data)).setText(TextUtils.isEmpty(tvShow.getOriginal_language()) ? "无" : tvShow.getOriginal_language());
            ((TextView) findViewById(R.id.tv_time_data)).setText("无");
            ((TextView) findViewById(R.id.tv_budget_data)).setText("无");
            ((TextView) findViewById(R.id.tv_box_office_data)).setText("无");
            ((TextView) findViewById(R.id.tv_homepage_data)).setText(TextUtils.isEmpty(tvShow.getHomepage()) ? "无" : tvShow.getHomepage());
        }
        if (recommend != null) {
            recommendTvAdapter = new TvReSiAdapter(R.layout.item_search_layout, recommend.getResults());
            EmptyView emptyView = new EmptyView(this);
            emptyView.setText("暂无相关剧集");
            recommendTvAdapter.setEmptyView(emptyView);
            binding.ryVideos.setAdapter(recommendTvAdapter);
        }
        if (similar != null) {
            similarTvAdapter = new TvReSiAdapter(R.layout.item_search_layout, similar.getResults());
            EmptyView emptyView = new EmptyView(this);
            emptyView.setText("暂无推荐剧集");
            similarTvAdapter.setEmptyView(emptyView);
        }


        updateKeywords(keywords);
        updateCast(credits);
        updateImages(images);
    }

    private void updateKeywords(Keywords keywords) {
        if (keywords != null) {
            List<BaseKeyword> keyList = keywords.getKeywords();
            if (keyList != null && keyList.size() > 0) {
                showGenreTag(StreamSupport.stream(keyList).map(baseKeyword -> baseKeyword.getName()).collect(Collectors.toList()), binding.rlKeyword);
            }
            if (keyList.size() == 0) {
                showNoTag(true,binding.rlKeyword);
            }
        }
    }

    private void updateCast(Credits credits) {
        if (credits != null) {
            List<CastMember> memberList = credits.getCast();
            if (memberList != null && memberList.size() > 0) {
                starAdapter = new StarAdapter(R.layout.item_movie_star_layout, memberList);
                starAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if (adapter.getItem(position) != null && ((StarAdapter)adapter).getItem(position).getId() > 0) {
                            VideoRoleActivity.startActivity(VideoDetailActivity.this,((StarAdapter)adapter).getItem(position).getId());
                        }
                    }
                });
                binding.ryPhotos.setAdapter(starAdapter);
            }
            List<CrewMember> memberList1 = credits.getCrew();
            if (memberList1 != null && memberList1.size() > 0) {
                if (memberList1.get(0).getJob().equalsIgnoreCase("Director")) {
                    binding.tvMovieDirector.setText(Utils.flipTextColor(this,"导演:" + memberList1.get(0).getName(),":",R.color.yellow_1));
                } else {
                    binding.tvMovieDirector.setText(Utils.flipTextColor(this,"导演:无",":",R.color.yellow_1));
                }
            }
        }
    }

    private void updateImages(Images images) {
        if (images != null) {
            List<Image> imgBackList = images.getBackdrops();
            if (imgBackList != null) {
                photoBackAdapter = new PhotoAdapter(R.layout.item_movie_photo, imgBackList);
                EmptyView emptyView = new EmptyView(this);
                emptyView.setText("暂无剧照");
                photoBackAdapter.setEmptyView(emptyView);
            }

            List<Image> imgPosterList = images.getPosters();
            if (imgPosterList != null) {
                photoPosterAdapter = new PhotoAdapter(R.layout.item_movie_photo, imgPosterList);
                EmptyView emptyView = new EmptyView(this);
                emptyView.setText("暂无海报");
                photoPosterAdapter.setEmptyView(emptyView);
            }
        }
    }

    public static void startActivity(FragmentActivity activity, long videoId, String overview, int type) {
        Intent intent = new Intent(activity, VideoDetailActivity.class);
        intent.putExtra("videoId", videoId);
        intent.putExtra("type", type);
        intent.putExtra("overview", overview);
        activity.startActivity(intent);
    }

    private void showNoTag(boolean isKeyword, RelativeLayout parent) {
        TextView textView = new TextView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (isKeyword) {
            textView.setText("无标签");
        } else {
            textView.setText("无类型");
        }
        textView.setTextSize(16);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        parent.addView(textView,params);
    }

    private void showGenreTag(List<String> words, RelativeLayout parent) {
        if (words != null && words.size() > 0 && parent != null) {
            parent.post(() -> {
                int preId = 0, textViewWidth = 0, lines = 1;
                int wordTagId = 10001;
                int width = parent.getMeasuredWidth();
                if (width == 0) {
                    width = ScreenUtils.getScreenWidth();
                }
                for (int i = 0; i < words.size(); i++) {
                    String word = words.get(i);
                    TextView textView = new TextView(this);
                    textView.setBackgroundResource(R.drawable.shape_color_black6_20dp_corners);
                    textView.setPadding((int) ScreenUtils.dp2px(this, 10), (int) ScreenUtils.dp2px(this, 3), (int) ScreenUtils.dp2px(this, 10), (int) ScreenUtils.dp2px(this, 3));
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextColor(ContextCompat.getColor(this, R.color.white_4));
                    textView.setText(word);
                    RelativeLayout.LayoutParams tvParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    textView.measure(spec, spec);
                    if (preId > 0) {
                        //测量textview
                        if (width >= textViewWidth + textView.getMeasuredWidth() + (int) ScreenUtils.dp2px(this, 15)) {
                            tvParams.addRule(RelativeLayout.RIGHT_OF, preId);
                            tvParams.setMarginStart((int) ScreenUtils.dp2px(this, 15));
                        } else {
                            lines++;
                            textViewWidth = 0;
                        }
                    }
                    tvParams.setMargins(0, (int) ((textView.getMeasuredHeight() + ScreenUtils.dp2px(this, 5)) * (lines - 1)), 0, 0);
                    textViewWidth += textView.getMeasuredWidth() + (int) ScreenUtils.dp2px(this, 15);
                    textView.setId(wordTagId + i);
                    preId = textView.getId();
                    parent.addView(textView, tvParams);
                }
            });
        }
    }

    @Override
    public void switchBaseInfo(int position) {
        binding.setSelectOne(position);
        switch (position) {
            case 1:
                break;
            case 2:
                binding.ryPhotos.setAdapter(starAdapter);
                break;
            case 3:
                binding.ryPhotos.setAdapter(photoBackAdapter);
                break;
            case 4:
                binding.ryPhotos.setAdapter(photoPosterAdapter);
                break;
        }
    }

    @Override
    public void switchMovieInfo(int position) {
        binding.setSelectTwo(position);
        if (position == 1) {
            if (type == 1) {
                binding.ryVideos.setAdapter(similarMovieAdapter);
            } else if (type == 2) {
                binding.ryVideos.setAdapter(similarTvAdapter);
            }
        } else if (position == 2) {
            if (type == 1) {
                binding.ryVideos.setAdapter(recommendMovieAdapter);
            } else if (type == 2) {
                binding.ryVideos.setAdapter(recommendTvAdapter);
            }
        }
    }

    @Override
    public void backEvent() {
        finish();
    }

    @Override
    public void likeEvent() {
        if (type == 1 && movieDetailBean != null) {
            if (movieDetailBean.isLike()) {
                Toast.makeText(this,"取消收藏",Toast.LENGTH_SHORT).show();
                movieDetailBean.setLike(false);
            } else {
                Toast.makeText(this,"成功收藏电影",Toast.LENGTH_SHORT).show();
                movieDetailBean.setLike(true);
            }
            binding.setIsLike(movieDetailBean.isLike());
            viewModule.likeMovie(movieDetailBean);
        } else if (type == 2 && tvShowDetailBean != null) {
            if (tvShowDetailBean.isLike()) {
                Toast.makeText(this,"取消收藏",Toast.LENGTH_SHORT).show();
                tvShowDetailBean.setLike(false);
            } else {
                Toast.makeText(this,"成功收藏电视节目",Toast.LENGTH_SHORT).show();
                tvShowDetailBean.setLike(true);
            }
            binding.setIsLike(tvShowDetailBean.isLike());
            viewModule.likeTv(tvShowDetailBean);
        }
    }

    @Override
    public void shareEvent() {
        if (shareDialog == null) {
            shareDialog = new BottomShareDialog();
        }
        if (!shareDialog.isAdded()) {
            Bundle bundle = new Bundle();
            bundle.putString("imgPoster",imgPoster);
            bundle.putString("title",title);
            shareDialog.setArguments(bundle);
            shareDialog.show(getSupportFragmentManager(),"share");
        }
    }

    @Override
    public void openTextBox() {
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(overview)) {
            return;
        }
        TextDisplayBoxFragment.showFragment(this, title, overview);
    }
}
