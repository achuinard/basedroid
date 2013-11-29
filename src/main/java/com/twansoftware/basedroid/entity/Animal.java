package com.twansoftware.basedroid.entity;

import java.io.Serializable;

/**
 * User: achuinard
 * Date: 11/29/13
 */
public class Animal implements Serializable {
    private final String name;
    private final String sound;

    public Animal(final String name, final String sound) {
        this.name = name;
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    public String getSound() {
        return sound;
    }

    @Override
    public String toString() {
        return getName();
    }
}
