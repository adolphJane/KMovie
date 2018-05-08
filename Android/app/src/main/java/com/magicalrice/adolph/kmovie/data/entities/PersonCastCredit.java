package com.magicalrice.adolph.kmovie.data.entities;

public class PersonCastCredit extends BasePersonCredit {

    // both
    private String character;

    // tv
    private int episode_count;

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
