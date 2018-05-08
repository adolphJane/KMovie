package com.magicalrice.adolph.kmovie.data.entities;

public class ListCreateResponse extends Status {
    private Boolean success;
    private Integer list_id;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getList_id() {
        return list_id;
    }

    public void setList_id(Integer list_id) {
        this.list_id = list_id;
    }
}
