package com.magicalrice.adolph.kmovie.data.enumerations;

public enum ReleaseType {

    PREMIERE(1),
    THEATRICAL_LIMITED(2),
    THEATRICAL(3),
    DIGITAL(4),
    PHYSICAL(5),
    TV(6);

    public final int id;

    ReleaseType(int id) {
        this.id = id;
    }
}
