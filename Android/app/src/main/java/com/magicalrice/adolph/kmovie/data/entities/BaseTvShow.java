package com.magicalrice.adolph.kmovie.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class BaseTvShow extends BaseRatingObject implements Parcelable {
    private int id;
    private String original_name;
    private String original_language;
    private String overview;
    private String name;
    private List<String> origin_country;
    private List<Integer> genre_ids;
    private String first_air_date;
    private String backdrop_path;
    private String poster_path;
    private double popularity;
    private double vote_average;
    private int vote_count;
    private String media_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOriginal_language() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
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
        dest.writeString(this.original_name);
        dest.writeString(this.original_language);
        dest.writeString(this.overview);
        dest.writeString(this.name);
        dest.writeStringList(this.origin_country);
        dest.writeList(this.genre_ids);
        dest.writeString(this.first_air_date);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.poster_path);
        dest.writeDouble(this.popularity);
        dest.writeDouble(this.vote_average);
        dest.writeInt(this.vote_count);
        dest.writeString(this.media_type);
    }

    public BaseTvShow() {
    }

    protected BaseTvShow(Parcel in) {
        this.id = in.readInt();
        this.original_name = in.readString();
        this.original_language = in.readString();
        this.overview = in.readString();
        this.name = in.readString();
        this.origin_country = in.createStringArrayList();
        this.genre_ids = new ArrayList<Integer>();
        in.readList(this.genre_ids, Integer.class.getClassLoader());
        this.first_air_date = in.readString();
        this.backdrop_path = in.readString();
        this.poster_path = in.readString();
        this.popularity = in.readDouble();
        this.vote_average = in.readDouble();
        this.vote_count = in.readInt();
        this.media_type = in.readString();
    }

    public static final Parcelable.Creator<BaseTvShow> CREATOR = new Parcelable.Creator<BaseTvShow>() {
        @Override
        public BaseTvShow createFromParcel(Parcel source) {
            return new BaseTvShow(source);
        }

        @Override
        public BaseTvShow[] newArray(int size) {
            return new BaseTvShow[size];
        }
    };
}
