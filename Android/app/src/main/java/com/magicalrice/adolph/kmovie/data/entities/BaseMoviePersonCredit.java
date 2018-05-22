package com.magicalrice.adolph.kmovie.data.entities;

/**
 * Created by Adolph on 2018/5/22.
 */

public abstract class BaseMoviePersonCredit extends BaseMovie{
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
}
