package com.zaraos.colorpop.model.constants;

/**
 * Created by alejandrozaraos on 02/02/17.
 */

public enum MODE {

    LEFT(0),
    CENTER(1),
    RIGHT(2);

    private int value;

    MODE(int value) {
        this.value = value;
    }

    public int toInt() {
        return value;
    }

}
