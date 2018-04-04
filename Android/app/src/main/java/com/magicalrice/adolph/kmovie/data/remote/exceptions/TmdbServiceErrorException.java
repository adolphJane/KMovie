package com.magicalrice.adolph.kmovie.data.remote.exceptions;

public class TmdbServiceErrorException extends TmdbException {
    public TmdbServiceErrorException(int code, String message) {
        super(code, message);
    }
}
