package com.magicalrice.adolph.kmovie.data.entities;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
/**
 * Created by Adolph on 2018/5/4.
 */

@Entity
public class BaseVideo implements Parcelable {
    private String poster_path;
    private String backdrop_path;
    private String title;
    private String release_date;
    private String overview;
    private int page;
    private int totalPage;
    private int type;
    private int genre;
    @PrimaryKey
    private long id;

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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BaseVideo() {
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
        dest.writeString(this.overview);
        dest.writeInt(this.page);
        dest.writeInt(this.totalPage);
        dest.writeInt(this.type);
        dest.writeInt(this.genre);
        dest.writeLong(this.id);
    }

    protected BaseVideo(Parcel in) {
        this.poster_path = in.readString();
        this.backdrop_path = in.readString();
        this.title = in.readString();
        this.release_date = in.readString();
        this.overview = in.readString();
        this.page = in.readInt();
        this.totalPage = in.readInt();
        this.type = in.readInt();
        this.genre = in.readInt();
        this.id = in.readLong();
    }

    public static final Creator<BaseVideo> CREATOR = new Creator<BaseVideo>() {
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
