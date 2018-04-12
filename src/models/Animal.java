package models;

import enums.Color;

/**
 * Created by mahdihs76 on 4/7/18.
 */
public abstract class Animal {
    private Color bodyColor;

    public Animal(Color bodyColor) {
        this.bodyColor = bodyColor;
    }

    abstract void walk();

    @Override
    public String toString() {
        return "Animal{" +
                "bodyColor=" + bodyColor +
                '}';
    }
}
