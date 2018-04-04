package com.magicalrice.adolph.kmovie.data.remote.exceptions;

public class TmdbAuthenticationFailedException extends TmdbException {
    public TmdbAuthenticationFailedException(int code, String message) {
        super(code, message);
    }
}
