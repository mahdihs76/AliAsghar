import models.Animal;
import models.Soldier;

import java.util.ArrayList;

/**
 * Created by mahdihs76 on 4/7/18.
 */
public class World {
//    private ArrayList<Animal> animals;
    private ArrayList<Soldier> soldiers;

    public World() {
//        animals = new ArrayList<>();
        soldiers = new ArrayList<>();
    }

//    public void addAnimal(Animal animal){
//        animals.add(animal);
//    }
//
    public void addSoldier(Soldier soldier){
        soldiers.add(soldier);
    }

//    public ArrayList<Animal> getAnimals() {
//        return animals;
//    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }
}
