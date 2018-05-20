package com.magicalrice.adolph.kmovie.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class BaseMovie extends BaseRatingObject implements Parcelable {
    private int id;
    private boolean adult;
    private String backdrop_path;
    private List<Genre> genres;
    private List<Integer> genre_ids;
    private String original_title;
    private String original_language;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private double vote_average;
    private int vote_count;
    private String media_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        if (original_language.equalsIgnoreCase("en")) {
            return "English";
        }
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.backdrop_path);
        dest.writeTypedList(this.genres);
        dest.writeList(this.genre_ids);
        dest.writeString(this.original_title);
        dest.writeString(this.original_language);
        dest.writeString(this.overview);
        dest.writeDouble(this.popularity);
        dest.writeString(this.poster_path);
        dest.writeString(this.release_date);
        dest.writeString(this.title);
        dest.writeDouble(this.vote_average);
        dest.writeInt(this.vote_count);
        dest.writeString(this.media_type);
    }

    public BaseMovie() {
    }

    protected BaseMovie(Parcel in) {
        this.id = in.readInt();
        this.adult = in.readByte() != 0;
        this.backdrop_path = in.readString();
        this.genres = in.createTypedArrayList(Genre.CREATOR);
        this.genre_ids = new ArrayList<Integer>();
        in.readList(this.genre_ids, Integer.class.getClassLoader());
        this.original_title = in.readString();
        this.original_language = in.readString();
        this.overview = in.readString();
        this.popularity = in.readDouble();
        this.poster_path = in.readString();
        this.release_date = in.readString();
        this.title = in.readString();
        this.vote_average = in.readDouble();
        this.vote_count = in.readInt();
        this.media_type = in.readString();
    }

    public static final Parcelable.Creator<BaseMovie> CREATOR = new Parcelable.Creator<BaseMovie>() {
        @Override
        public BaseMovie createFromParcel(Parcel source) {
            return new BaseMovie(source);
        }

        @Override
        public BaseMovie[] newArray(int size) {
            return new BaseMovie[size];
        }
    };
}
