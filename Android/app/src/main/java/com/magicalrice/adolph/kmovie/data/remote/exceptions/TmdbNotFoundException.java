package com.magicalrice.adolph.kmovie.data.remote.exceptions;

public class TmdbNotFoundException extends TmdbException {
    public TmdbNotFoundException(int code, String message) {
        super(code, message);
    }
}
