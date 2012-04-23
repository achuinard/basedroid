package com.twansoftware.basedroid.entity;

import java.io.Serializable;

/**
 * Author: achuinard
 * 4/23/12
 */
public class SamplePojo implements Serializable {
    private final String stringOne;
    private final int intOne;

    public SamplePojo(final String stringOne, final int intOne) {
        this.stringOne = stringOne;
        this.intOne = intOne;
    }

    public String getStringOne() {
        return stringOne;
    }

    public int getIntOne() {
        return intOne;
    }
}
