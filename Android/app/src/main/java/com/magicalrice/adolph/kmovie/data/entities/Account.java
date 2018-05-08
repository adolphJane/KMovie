package com.magicalrice.adolph.kmovie.data.entities;

/**
 * Created by Adolph on 2018/4/4.
 */
public class Account {
    public static class Avatar {
        public static class GRAvatar {
            private String hash;

            public String getHash() {
                return hash;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }
        }
        private GRAvatar gravatar;

        public GRAvatar getGravatar() {
            return gravatar;
        }

        public void setGravatar(GRAvatar gravatar) {
            this.gravatar = gravatar;
        }
    }

    private int id;
    private String iso_639_1;
    private String iso_3166_1;
    private String name;
    private boolean include_adult;
    private String username;
    private Avatar avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInclude_adult() {
        return include_adult;
    }

    public void setInclude_adult(boolean include_adult) {
        this.include_adult = include_adult;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
