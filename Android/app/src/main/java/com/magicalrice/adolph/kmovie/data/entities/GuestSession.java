package com.magicalrice.adolph.kmovie.data.entities;

public class GuestSession {
    private boolean success;
    private String guest_session_id;
    private String expires_at;

    public String getGuest_session_id() {
        return guest_session_id;
    }

    public void setGuest_session_id(String guest_session_id) {
        this.guest_session_id = guest_session_id;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
