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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final SamplePojo that = (SamplePojo) o;

        if (intOne != that.intOne) return false;
        if (stringOne != null ? !stringOne.equals(that.stringOne) : that.stringOne != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stringOne != null ? stringOne.hashCode() : 0;
        result = 31 * result + intOne;
        return result;
    }
}
