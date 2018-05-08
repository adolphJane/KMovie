package com.magicalrice.adolph.kmovie.data.entities;

import java.util.HashMap;
import java.util.List;

public class Certifications {

    public static class Certification {
        private String certification;
        private String meaning;
        private int order;

        public String getCertification() {
            return certification;
        }

        public void setCertification(String certification) {
            this.certification = certification;
        }

        public String getMeaning() {
            return meaning;
        }

        public void setMeaning(String meaning) {
            this.meaning = meaning;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }
    }

    private HashMap<String, List<Certification>> certifications;

    public HashMap<String, List<Certification>> getCertifications() {
        return certifications;
    }

    public void setCertifications(HashMap<String, List<Certification>> certifications) {
        this.certifications = certifications;
    }
}
