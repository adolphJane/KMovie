package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public class AccountStatesResults {
    private int id;
    private List<AccountStates> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<AccountStates> getResults() {
        return results;
    }

    public void setResults(List<AccountStates> results) {
        this.results = results;
    }
}
