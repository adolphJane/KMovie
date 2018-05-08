package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class Configuration {

    public static class ImagesConfiguration {
        private String base_url;
        private String secure_base_url;
        private List<String> poster_sizes;
        private List<String> backdrop_sizes;
        private List<String> profile_sizes;
        private List<String> logo_sizes;
        private List<String> still_sizes;

        public String getBase_url() {
            return base_url;
        }

        public void setBase_url(String base_url) {
            this.base_url = base_url;
        }

        public String getSecure_base_url() {
            return secure_base_url;
        }

        public void setSecure_base_url(String secure_base_url) {
            this.secure_base_url = secure_base_url;
        }

        public List<String> getPoster_sizes() {
            return poster_sizes;
        }

        public void setPoster_sizes(List<String> poster_sizes) {
            this.poster_sizes = poster_sizes;
        }

        public List<String> getBackdrop_sizes() {
            return backdrop_sizes;
        }

        public void setBackdrop_sizes(List<String> backdrop_sizes) {
            this.backdrop_sizes = backdrop_sizes;
        }

        public List<String> getProfile_sizes() {
            return profile_sizes;
        }

        public void setProfile_sizes(List<String> profile_sizes) {
            this.profile_sizes = profile_sizes;
        }

        public List<String> getLogo_sizes() {
            return logo_sizes;
        }

        public void setLogo_sizes(List<String> logo_sizes) {
            this.logo_sizes = logo_sizes;
        }

        public List<String> getStill_sizes() {
            return still_sizes;
        }

        public void setStill_sizes(List<String> still_sizes) {
            this.still_sizes = still_sizes;
        }
    }

    private ImagesConfiguration images;
    private List<String> change_keys;

    public ImagesConfiguration getImages() {
        return images;
    }

    public void setImages(ImagesConfiguration images) {
        this.images = images;
    }

    public List<String> getChange_keys() {
        return change_keys;
    }

    public void setChange_keys(List<String> change_keys) {
        this.change_keys = change_keys;
    }
}
