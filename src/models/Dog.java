package models;

import enums.Color;

/**
 * Created by mahdihs76 on 4/7/18.
 */
public class Dog extends Animal {

    public Dog(Color bodyColor) {
        super(bodyColor);
    }

    @Override
    void walk() {
        System.out.println("Dog Walk");
    }
}
