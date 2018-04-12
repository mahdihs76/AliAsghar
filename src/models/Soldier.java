package models;

import interfaces.Alive;
import interfaces.Attacker;

/**
 * Created by mahdihs76 on 4/7/18.
 */
public class Soldier extends Person implements Attacker {
    private int power;

    public Soldier(int power) {
        this.power = power;
    }

    @Override
    public void attack() {
        System.out.println("Attack(Soldier)");
    }

    @Override
    public String toString() {
        return "Soldier{" +
                "power=" + power +
                '}';
    }

}
