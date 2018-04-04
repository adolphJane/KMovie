package com.magicalrice.adolph.kmovie.data.entities;


import com.magicalrice.adolph.kmovie.data.enumerations.CreditType;
import com.magicalrice.adolph.kmovie.data.enumerations.MediaType;


public class Credit {

    public CreditType credit_type;
    public String department;
    public String job;
    public CreditMedia media;
    public MediaType media_type;
    public String id;
    public BasePerson person;

}
