package com.magicalrice.adolph.kmovie.data.entities;

/**
 * Created by Adolph on 2018/4/4.
 */
public class Account {
    public static class Avatar {
        public static class GRAvatar {
            public String hash;
        }
        public GRAvatar gravatar;
    }

    public Integer id;
    public String iso_639_1;
    public String iso_3166_1;
    public String name;
    public Boolean include_adult;
    public String username;
    public Avatar avatar;
}
