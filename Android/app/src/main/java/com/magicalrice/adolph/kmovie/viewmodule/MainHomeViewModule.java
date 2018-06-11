package com.magicalrice.adolph.kmovie.viewmodule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.magicalrice.adolph.kmovie.data.datasource.MovieRemoteDataSource;
import com.magicalrice.adolph.kmovie.data.entities.BaseVideo;
import com.magicalrice.adolph.kmovie.data.entities.DiscoverFilter;
import com.magicalrice.adolph.kmovie.data.entities.GenreResults;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.VideoResultsPage;
import com.magicalrice.adolph.kmovie.data.repository.MovieRepository;

import java.util.List;

public class MainHomeViewModule extends AndroidViewModel{
    private MovieRepository repository;
    public MutableLiveData<GenreResults> genreData = new MutableLiveData<>();
    public MutableLiveData<Boolean> shoudTop = new MutableLiveData<>();
    public int videoType = 1;

    public MainHomeViewModule(@NonNull Application application,MovieRepository repository) {
        super(application);
        this.repository = repository;
    }

    public void getMovieGenre() {
        repository.getMovieGenre().subscribe(genreResults -> genreData.setValue(genreResults),throwable -> Toast.makeText(getApplication(),throwable.getMessage() + "error",Toast.LENGTH_SHORT).show());
    }

    public void getTvGenre() {
        repository.getTvGenre().subscribe(genreResults -> genreData.setValue(genreResults),throwable -> Toast.makeText(getApplication(),throwable.getMessage() + "error",Toast.LENGTH_SHORT).show());
    }

    public LiveData<List<BaseVideo>> getMovies(int genreId, int page) {
        return repository.getMoviesByGenre(genreId,page);
    }

    public LiveData<List<BaseVideo>> getTvs(int genreId,int page) {
        return repository.getTvsByGenre(genreId,page);
    }

    public int getVideoType() {
        return videoType;
    }

    public void setVideoType(int videoType) {
        this.videoType = videoType;
    }
}
