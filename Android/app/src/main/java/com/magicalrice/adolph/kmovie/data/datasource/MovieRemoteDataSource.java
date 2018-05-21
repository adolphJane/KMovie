package com.magicalrice.adolph.kmovie.data.datasource;

import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.BaseTvShow;
import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.DiscoverFilter;
import com.magicalrice.adolph.kmovie.data.entities.GenreResults;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.VideoResultsPage;
import com.magicalrice.adolph.kmovie.data.enumerations.SortBy;
import com.magicalrice.adolph.kmovie.data.remote.Tmdb;
import com.magicalrice.adolph.kmovie.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class MovieRemoteDataSource {
    Tmdb tmdb;

    @Inject
    public MovieRemoteDataSource(Tmdb tmdb) {
        this.tmdb = tmdb;
    }

    public Observable<MovieResultsPage> getPopularMovie(int page) {
        return tmdb.moviesService().popular(page, "zh")
                .compose(RxUtils.io_main());
    }

    public Observable<TvShowResultsPage> getPopularTv(int page) {
        return tmdb.tvService().popular(page,"zh")
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

    public Observable<VideoResultsPage> getMoviesByGenre(DiscoverFilter genre, int page) {
        return tmdb.discoverService().discoverMovieWithGenre("zh",SortBy.VOTE_AVERAGE_ASC,true,true,page,genre)
                .compose(RxUtils.io_main()).map(movieResultsPage -> {
                        VideoResultsPage result = new VideoResultsPage();
                        result.setId(movieResultsPage.getId());
                        result.setPage(movieResultsPage.getPage());
                        result.setTotal_pages(movieResultsPage.getTotal_pages());
                        result.setTotal_results(movieResultsPage.getTotal_results());
                        List<BaseVideo> videoList = new ArrayList<>();
                        for (BaseMovie bean : movieResultsPage.getResults()) {
                            BaseVideo video = new BaseVideo();
                            video.setTitle(bean.getTitle());
                            video.setBackdrop_path(bean.getBackdrop_path());
                            video.setPoster_path(bean.getPoster_path());
                            video.setRelease_date(bean.getRelease_date());
                            video.setOverview(bean.getOverview());
                            video.setId(bean.getId());
                            videoList.add(video);
                        }
                        result.setResults(videoList);
                        return result;
                });
    }

    public Observable<VideoResultsPage> getTvsByGenre(DiscoverFilter genre, int page) {
        return tmdb.discoverService().discoverTvWithGenre("zh", SortBy.VOTE_AVERAGE_ASC, page,genre)
                .compose(RxUtils.io_main()).compose(RxUtils.io_main()).map(tvShowResultsPage -> {
                    VideoResultsPage result = new VideoResultsPage();
                    result.setId(tvShowResultsPage.getId());
                    result.setPage(tvShowResultsPage.getPage());
                    result.setTotal_pages(tvShowResultsPage.getTotal_pages());
                    result.setTotal_results(tvShowResultsPage.getTotal_results());
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
                            videoList.add(video);
                        }
                    }
                    result.setResults(videoList);
                    return result;
                });
    }
}