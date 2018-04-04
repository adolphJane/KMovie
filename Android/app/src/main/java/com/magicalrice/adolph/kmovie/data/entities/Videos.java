package com.magicalrice.adolph.kmovie.data.entities;

import com.magicalrice.adolph.kmovie.data.enumerations.VideoType;

import java.util.List;

public class Videos {

    public static class Video {

        public String id;
        public String iso_639_1;
        public String iso_3166_1;
        public String key;
        public String name;
        public String site;
        public Integer size;
        public VideoType type;

    }

    public Integer id;
    public List<Video> results;

}
