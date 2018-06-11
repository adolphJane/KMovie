package com.magicalrice.adolph.kmovie.data.datasource;

import android.content.Context;
import android.widget.Toast;

import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;
import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.DiscoverFilter;
import com.magicalrice.adolph.kmovie.data.entities.GenreResults;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.data.enumerations.SortBy;
import com.magicalrice.adolph.kmovie.data.local.database.MovieDatabase;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.utils.LUtils;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MovieRemoteDataSource {
    private Tmdb tmdb;
    private MovieDatabase database;
    private Context context;

    @Inject
    public MovieRemoteDataSource(Context context, Tmdb tmdb, MovieDatabase database) {
        this.context = context;
        this.tmdb = tmdb;
        this.database = database;
    }

    public Observable<MovieResultsPage> getPopularMovie(int page) {
        return tmdb.moviesService().popular(page, "zh")
                .compose(RxUtils.io_main());
    }

    public Observable<TvShowResultsPage> getPopularTv(int page) {
        return tmdb.tvService().popular(page, "zh")
                .compose(RxUtils.io_main());
    }

    public Observable<GenreResults> getMovieGenre() {
        return tmdb.genreService().movie("zh")
                .compose(RxUtils.io_main());
    }

    public Observable<GenreResults> getTvGenre() {
        return tmdb.genreService().tv("zh")
                .compose(RxUtils.io_main());
    }

    public void getMoviesByGenre(int genre, int page) {
        DiscoverFilter filter = new DiscoverFilter(genre);
        tmdb.discoverService().discoverMovieWithGenre("zh", SortBy.VOTE_AVERAGE_ASC, true, true, page, filter)
                .map(movieResultsPage -> {
                    List<BaseVideo> videoList = new ArrayList<>();
                    for (BaseMovie bean : movieResultsPage.getResults()) {
                        BaseVideo video = new BaseVideo();
                        video.setTitle(bean.getTitle());
                        video.setBackdrop_path(bean.getBackdrop_path());
                        video.setPoster_path(bean.getPoster_path());
                        video.setRelease_date(bean.getRelease_date());
                        video.setOverview(bean.getOverview());
                        video.setId(bean.getId());
                        video.setPage(movieResultsPage.getPage());
                        video.setTotalPage(movieResultsPage.getTotal_pages());
                        video.setGenre(genre);
                        video.setType(1);
                        videoList.add(video);
                    }
                    database.movieListDao().insertMovieList(videoList);
                    return videoList;
                }).compose(RxUtils.io_main()).subscribe(videos -> {
        }, throwable -> LUtils.e(throwable.getMessage()));
    }

    public void getTvsByGenre(int genre, int page) {
        DiscoverFilter filter = new DiscoverFilter(genre);
        tmdb.discoverService().discoverTvWithGenre("zh", SortBy.VOTE_AVERAGE_ASC, page, filter)
                .map(tvShowResultsPage -> {
                    List<BaseVideo> videoList = new ArrayList<>();
                    for (BaseTvShow bean : tvShowResultsPage.getResults()) {
                        if (bean != null) {
                            BaseVideo video = new BaseVideo();
                            video.setTitle(bean.getName());
                            video.setBackdrop_path(bean.getBackdrop_path());
                            video.setPoster_path(bean.getPoster_path());
                            video.setRelease_date(bean.getFirst_air_date());
                            video.setOverview(bean.getOverview());
                            video.setId(bean.getId());
                            video.setPage(tvShowResultsPage.getPage());
                            video.setTotalPage(tvShowResultsPage.getTotal_pages());
                            video.setGenre(genre);
                            video.setType(2);
                            videoList.add(video);
                        }
                    }
                    database.tvListDao().insertTvList(videoList);
                    return videoList;
                }).compose(RxUtils.io_main())
                .subscribe(videos -> {}, throwable -> LUtils.e(throwable.getMessage()));
    }
}