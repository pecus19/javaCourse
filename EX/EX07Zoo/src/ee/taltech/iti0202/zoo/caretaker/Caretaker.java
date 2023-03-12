package ee.taltech.iti0202.zoo.caretaker;

import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.animal.Type;

import java.util.ArrayList;


public class Caretaker {
    private String name;
    private Type type;

    private ArrayList<Animal> animalsToFeed = new ArrayList<>();

    /**
     * @param name name
     * @param type type
     */
    public Caretaker(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public ArrayList<Animal> getAnimalsToFeed() {
        return animalsToFeed;
    }

    /**
     * @param animal animal
     * @return false
     */
    public boolean checkType(Animal animal) {
        if (animal != null & animal.getType() != null) {
            return animal.getType().equals(getType());
        }
        return false;
    }

    /**
     * @param animal animal
     */
    public void addAnimalsToFeed(Animal animal) {
        if (checkType(animal)) {
            if (animal.isHungry() && !animalsToFeed.contains(animal)) {
                animalsToFeed.add(animal);
            }
        }
    }

    /**
     * feedAnimals
     */
    public void feedAnimals() {
        for (int i = 0; i < animalsToFeed.size(); i++) {
            animalsToFeed.get(i).feeding();
            deleteAnimal(animalsToFeed.get(i));
        }
    }

    /**
     * @param animal animal
     */
    public void deleteAnimal(Animal animal) {
        if (animal != null & animalsToFeed.contains(animal)) {
            animalsToFeed.remove(animal);
        }
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return type
     */
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
