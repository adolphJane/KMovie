package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.GenreResults;
import com.magicalrice.adolph.kmovie.data.repository.MovieRepository;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Flowable;

public class MainHomeViewModule extends AndroidViewModel{
    private MovieRepository repository;
    public MutableLiveData<GenreResults> genreData = new MutableLiveData<>();
    public MutableLiveData<Boolean> shoudTop = new MutableLiveData<>();
    private int videoType = 0;

    public MainHomeViewModule(@NonNull Application application,MovieRepository repository) {
        super(application);
        this.repository = repository;
    }

    public void getMovieGenre() {
        repository.getMovieGenre().subscribe(genreResults -> {
            genreResults.setType(1);
            genreData.setValue(genreResults);
        },throwable -> Toast.makeText(getApplication(),throwable.getMessage() + "error",Toast.LENGTH_SHORT).show());
    }

    public void getTvGenre() {
        repository.getTvGenre().subscribe(genreResults -> {
            genreResults.setType(2);
            genreData.setValue(genreResults);
        },throwable -> Toast.makeText(getApplication(),throwable.getMessage() + "error",Toast.LENGTH_SHORT).show());
    }

    public Flowable<List<BaseVideo>> getVideos(int genreId, int page,int type) {
        return repository.getVideosByGenre(genreId,page,type);
    }

    public int getVideoType() {
        return videoType;
    }

    public void setVideoType(int videoType) {
        this.videoType = videoType;
    }

    public void clearCache() {
        repository.clearCache();
    }
}
