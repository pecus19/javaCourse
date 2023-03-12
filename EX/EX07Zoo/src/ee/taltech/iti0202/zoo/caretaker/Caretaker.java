package ee.taltech.iti0202.zoo.caretaker;

import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.animal.Type;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Caretaker {
    private String name;
    private Type type;

    private ArrayList<Animal> animalsToFeed = new ArrayList<>();

    public Caretaker(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public ArrayList<Animal> getAnimalsToFeed() {
        return animalsToFeed;
    }

    public boolean checkType(Animal animal) {
        if (animal != null & animal.getType() != null) {
            return animal.getType().equals(getType());
        }
        return false;
    }


    public void addAnimalsToFeed(Animal animal) throws IllegalArgumentException {
        if (checkType(animal)) {
            if (animal.isHungry() && !animalsToFeed.contains(animal)) {
                animalsToFeed.add(animal);
            }
        }
    }

    public void feedAnimals() {
        for (int i = 0; i < animalsToFeed.size(); i++) {
            animalsToFeed.get(i).feeding();
            deleteAnimal(animalsToFeed.get(i));
        }
    }

    public void deleteAnimal(Animal animal) throws IllegalArgumentException {
        if (animal != null & animalsToFeed.contains(animal)) {
            animalsToFeed.remove(animal);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
