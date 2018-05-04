package com.magicalrice.adolph.kmovie.data.entities;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
/**
 * Created by Adolph on 2018/5/4.
 */

public class BaseVideo implements Parcelable {
    private String poster_path;
    private String backdrop_path;
    private String title;
    private String release_date;
    private int id;

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.poster_path);
        dest.writeString(this.backdrop_path);
        dest.writeString(this.title);
        dest.writeString(this.release_date);
        dest.writeInt(this.id);
    }

    public BaseVideo() {
    }

    protected BaseVideo(Parcel in) {
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();
        this.title = in.readString();
        this.release_date = in.readString();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<BaseVideo> CREATOR = new Parcelable.Creator<BaseVideo>() {
        @Override
        public BaseVideo createFromParcel(Parcel source) {
            return new BaseVideo(source);
        }

        @Override
        public BaseVideo[] newArray(int size) {
            return new BaseVideo[size];
        }
    };
}