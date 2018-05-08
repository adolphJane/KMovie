package com.magicalrice.adolph.kmovie.data.entities;


import com.magicalrice.adolph.kmovie.data.enumerations.CreditType;
import com.magicalrice.adolph.kmovie.data.enumerations.MediaType;


public class Credit {
    private CreditType credit_type;
    private String department;
    private String job;
    private CreditMedia media;
    private MediaType media_type;
    private String id;
    private BasePerson person;

    public CreditType getCredit_type() {
        return credit_type;
    }

    public void setCredit_type(CreditType credit_type) {
        this.credit_type = credit_type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public CreditMedia getMedia() {
        return media;
    }

    public void setMedia(CreditMedia media) {
        this.media = media;
    }

    public MediaType getMedia_type() {
        return media_type;
    }

    public void setMedia_type(MediaType media_type) {
        this.media_type = media_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BasePerson getPerson() {
        return person;
    }

    public void setPerson(BasePerson person) {
        this.person = person;
    }
}
