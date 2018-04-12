package models;

import enums.Color;

/**
 * Created by mahdihs76 on 4/7/18.
 */
public class Cat extends Animal {
    private int hairDegree;

    public Cat(Color bodyColor, int hairDegree) {
        super(bodyColor);
        this.hairDegree = hairDegree;
    }

    @Override
    void walk() {
        System.out.println("Cat Walk");
    }

    public int getHairDegree() {
        return hairDegree;
    }

    public void setHairDegree(int hairDegree) {
        this.hairDegree = hairDegree;
    }

}
