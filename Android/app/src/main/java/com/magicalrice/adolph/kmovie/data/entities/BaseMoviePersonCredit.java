package com.magicalrice.adolph.kmovie.data.entities;

import android.os.Parcel;

/**
 * Created by Adolph on 2018/5/22.
 */

public class BaseMoviePersonCredit extends BaseMovie{
    private String credit_id;
    private String character;

    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.credit_id);
        dest.writeString(this.character);
    }

    public BaseMoviePersonCredit() {
    }

    protected BaseMoviePersonCredit(Parcel in) {
        super(in);
        this.credit_id = in.readString();
        this.character = in.readString();
    }

    public static final Creator<BaseMoviePersonCredit> CREATOR = new Creator<BaseMoviePersonCredit>() {
        @Override
        public BaseMoviePersonCredit createFromParcel(Parcel source) {
            return new BaseMoviePersonCredit(source);
        }

        @Override
        public BaseMoviePersonCredit[] newArray(int size) {
            return new BaseMoviePersonCredit[size];
        }
    };
}
