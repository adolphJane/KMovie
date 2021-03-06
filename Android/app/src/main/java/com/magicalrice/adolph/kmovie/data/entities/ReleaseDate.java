package com.magicalrice.adolph.kmovie.data.entities;

import java.util.Date;

public class ReleaseDate {

    public static Integer TYPE_PREMIERE = 1;                //首映
    public static Integer TYPE_THEATRICAL_LIMITED = 2;      //剧场有限
    public static Integer TYPE_THEATRICAL = 3;              //剧场
    public static Integer TYPE_DIGITAL = 4;                 //数字
    public static Integer TYPE_PHYSICAL = 5;                //物理
    public static Integer TYPE_TV = 6;                      //电视

    private String certification;
    private String iso_639_1;
    private String note;
    private Date release_date;
    private Integer type;

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        if (type != 0) {
            switch (type) {
                case 1:
                    return "首映场";
                case 2:
                    return "剧场有限";
                case 3:
                    return "剧场";
                case 4:
                    return "数字场";
                case 5:
                    return "物理场";
                case 6:
                    return "电视";
            }
        }
        return "";
    }
}
