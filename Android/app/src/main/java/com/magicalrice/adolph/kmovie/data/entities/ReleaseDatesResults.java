package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class ReleaseDatesResults {

    private int id;
    private List<ReleaseDatesResult> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ReleaseDatesResult> getResults() {
        return results;
    }

    public void setResults(List<ReleaseDatesResult> results) {
        this.results = results;
    }
}
