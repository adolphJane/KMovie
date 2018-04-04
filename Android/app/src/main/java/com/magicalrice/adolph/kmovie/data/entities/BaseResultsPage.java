package com.magicalrice.adolph.kmovie.data.entities;

import java.util.List;

public abstract class BaseResultsPage<T> {

    public Integer id;
    public Integer page;
    public Integer total_pages;
    public Integer total_results;
    public List<T> results;

}
