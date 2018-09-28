package com.magicalrice.adolph.kmovie.data.local.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.magicalrice.adolph.kmovie.data.entities.AlternativeTitle;
import com.magicalrice.adolph.kmovie.data.entities.BaseCompany;
import com.magicalrice.adolph.kmovie.data.entities.BaseKeyword;
import com.magicalrice.adolph.kmovie.data.entities.BaseMovie;
import com.magicalrice.adolph.kmovie.data.entities.CastMember;
import com.magicalrice.adolph.kmovie.data.entities.Changes;
import com.magicalrice.adolph.kmovie.data.entities.Changes.Entries;
import com.magicalrice.adolph.kmovie.data.entities.ContentRating;
import com.magicalrice.adolph.kmovie.data.entities.Country;
import com.magicalrice.adolph.kmovie.data.entities.Credits;
import com.magicalrice.adolph.kmovie.data.entities.CrewMember;
import com.magicalrice.adolph.kmovie.data.entities.Genre;
import com.magicalrice.adolph.kmovie.data.entities.Image;
import com.magicalrice.adolph.kmovie.data.entities.Images;
import com.magicalrice.adolph.kmovie.data.entities.Keywords;
import com.magicalrice.adolph.kmovie.data.entities.Movie;
import com.magicalrice.adolph.kmovie.data.entities.MovieResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.Network;
import com.magicalrice.adolph.kmovie.data.entities.Person;
import com.magicalrice.adolph.kmovie.data.entities.PersonImages;
import com.magicalrice.adolph.kmovie.data.entities.PersonMovieCredits;
import com.magicalrice.adolph.kmovie.data.entities.PersonTvCredits;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDatesResult;
import com.magicalrice.adolph.kmovie.data.entities.ReleaseDatesResults;
import com.magicalrice.adolph.kmovie.data.entities.SpokenLanguage;
import com.magicalrice.adolph.kmovie.data.entities.Translations.Translation;
import com.magicalrice.adolph.kmovie.data.entities.TvShow;
import com.magicalrice.adolph.kmovie.data.entities.TvShowResultsPage;
import com.magicalrice.adolph.kmovie.data.entities.Videos.Video;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

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
