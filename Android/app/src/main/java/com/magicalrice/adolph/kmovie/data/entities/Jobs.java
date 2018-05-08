package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class Jobs {
    private String department;
    private List<String> jobs;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }
}
