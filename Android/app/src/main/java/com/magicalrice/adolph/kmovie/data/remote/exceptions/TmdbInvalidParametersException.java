package com.magicalrice.adolph.kmovie.data.remote.exceptions;

public class TmdbInvalidParametersException extends TmdbException {
    public TmdbInvalidParametersException(int code, String message) {
        super(code, message);
    }
}
