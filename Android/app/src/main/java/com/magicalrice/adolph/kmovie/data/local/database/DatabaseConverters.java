package com.magicalrice.adolph.kmovie.data.local.database;


import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.magicalrice.adolph.kmovie.data.entities.Credits;
import com.magicalrice.adolph.kmovie.data.entities.Images;
import com.magicalrice.adolph.kmovie.data.entities.Keywords;
import com.magicalrice.adolph.kmovie.data.entities.Movie;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.Person;
import com.magicalrice.adolph.kmovie.data.entities.PersonImages;
import com.magicalrice.adolph.kmovie.data.entities.PersonMovieCredits;
import com.magicalrice.adolph.kmovie.data.entities.PersonTvCredits;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDatesResults;
import com.magicalrice.adolph.kmovie.data.entities.TvShow;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;

public class DatabaseConverters {
    @TypeConverter
    public static Movie revertMovie(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value,Movie.class);
    }

    @TypeConverter
    public static String convertMovie(Movie value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static Credits revertCredits(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value,Credits.class);
    }

    @TypeConverter
    public static String convertCredits(Credits value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static Images revertImages(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value,Images.class);
    }

    @TypeConverter
    public static String convertImages(Images value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static ReleaseDatesResults revertReleaseDatesResults(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value,ReleaseDatesResults.class);
    }

    @TypeConverter
    public static String convertReleaseDatesResults(ReleaseDatesResults value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static Keywords revertKeywords(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value,Keywords.class);
    }

    @TypeConverter
    public static String convertKeywords(Keywords value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static MovieResultsPage revertMovieResultsPage(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value,MovieResultsPage.class);
    }

    @TypeConverter
    public static String convertMovieResultsPage(MovieResultsPage value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static TvShow revertTvShow(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value,TvShow.class);
    }

    @TypeConverter
    public static String convertTvShow(TvShow value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static TvShowResultsPage revertTvShowResultsPage(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value,TvShowResultsPage.class);
    }

    @TypeConverter
    public static String convertTvShowResultsPage(TvShowResultsPage value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static PersonImages revertPersonImages(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value,PersonImages.class);
    }

    @TypeConverter
    public static String convertPersonImages(PersonImages value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static PersonMovieCredits revertPersonMovieCredits(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value,PersonMovieCredits.class);
    }

    @TypeConverter
    public static String convertPersonMovieCredits(PersonMovieCredits value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static PersonTvCredits revertPersonTvCredits(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value,PersonTvCredits.class);
    }

    @TypeConverter
    public static String convertPersonTvCredits(PersonTvCredits value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }

    @TypeConverter
    public static Person revertPerson(String value) {
        Gson gson = new Gson();
        return gson.fromJson(value,Person.class);
    }

    @TypeConverter
    public static String convertPerson(Person value) {
        Gson gson = new Gson();
        return gson.toJson(value);
    }
}
