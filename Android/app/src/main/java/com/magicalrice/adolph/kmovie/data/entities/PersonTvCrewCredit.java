package com.magicalrice.adolph.kmovie.data.entities;

/**
 * Created by Adolph on 2018/5/23.
 */

public class PersonTvCrewCredit extends BaseTvPersonCredit {
    private String job;
    private String department;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
