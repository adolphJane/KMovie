package com.magicalrice.adolph.kmovie.data.entities;

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
}
