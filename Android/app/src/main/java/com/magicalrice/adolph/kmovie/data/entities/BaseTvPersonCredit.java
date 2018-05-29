package com.magicalrice.adolph.kmovie.data.entities;

import android.os.Parcel;

/**
 * Created by Adolph on 2018/5/22.
 */

public class BaseTvPersonCredit extends BaseTvShow {
    private String credit_id;
    private String character;
    private int episode_count;

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

    public int getEpisode_count() {
        return episode_count;
    }

    public void setEpisode_count(int episode_count) {
        this.episode_count = episode_count;
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
        dest.writeInt(this.episode_count);
    }

    public BaseTvPersonCredit() {
    }

    protected BaseTvPersonCredit(Parcel in) {
        super(in);
        this.credit_id = in.readString();
        this.character = in.readString();
        this.episode_count = in.readInt();
    }

    public static final Creator<BaseTvPersonCredit> CREATOR = new Creator<BaseTvPersonCredit>() {
        @Override
        public BaseTvPersonCredit createFromParcel(Parcel source) {
            return new BaseTvPersonCredit(source);
        }

        @Override
        public BaseTvPersonCredit[] newArray(int size) {
            return new BaseTvPersonCredit[size];
        }
    };
}
