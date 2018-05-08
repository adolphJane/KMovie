package com.magicalrice.adolph.kmovie.data.entities;

public class List extends BaseList {
    private String created_by;
    private java.util.List<BaseMovie> items;

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public java.util.List<BaseMovie> getItems() {
        return items;
    }

    public void setItems(java.util.List<BaseMovie> items) {
        this.items = items;
    }
}
