package com.magicalrice.adolph.kmovie.data.remote.exceptions;

public class TmdbDuplicateEntryException extends TmdbException {
    public TmdbDuplicateEntryException(int code, String message) {
        super(code, message);
    }
}
