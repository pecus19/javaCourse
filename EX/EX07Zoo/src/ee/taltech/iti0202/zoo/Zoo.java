package ee.taltech.iti0202.zoo;

import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.caretaker.Caretaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Zoo {
    private ArrayList<Caretaker> caretakers = new ArrayList<>();
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Animal> hungryAnimals = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(Zoo.class.getName());

    public ArrayList<Caretaker> getAllCaretakers() {
        return caretakers;
    }

    public void addCaretaker(Caretaker caretaker) {
        if (caretaker != null && !getAllCaretakers().contains(caretaker)) {
            caretakers.add(caretaker);
        }
    }


    public void addAnimal(Animal animal) {
        if (animal != null && !getAllAnimals().contains(animal)) {
            animals.add(animal);
        }
    }

    public ArrayList<Animal> getAllAnimals() {
        return animals;
    }

    public Map<Animal, String> getAnimalsVoicesAtTheMoment() {
        Map<Animal, String> voicesMap = new HashMap<>();
        if (animals.size() != 0) {
            for (int i = 0; i < animals.size(); i++) {
                voicesMap.put(animals.get(i), animals.get(i).getVoice());
            }
        }
        return voicesMap;
    }

    public void addHungryAnimal() {
        for (int i = 0; i < getAllAnimals().size(); i++) {
            if (!hungryAnimals.contains(animals.get(i)) & animals.get(i).isHungry()) {
                hungryAnimals.add(animals.get(i));
            }
        }
        if (hungryAnimals != null) {
            LOGGER.info("Oletame, et meil on " + hungryAnimals.size() + " näljast looma.");
            for (int i = 0; i < getAllCaretakers().size(); i++) {
                for (int j = 0; j < hungryAnimals.size(); j++) {
                    if (caretakers.get(i).checkType(animals.get(j))) {
                        caretakers.get(i).addAnimalsToFeed(animals.get(j));
                    }
                }
                LOGGER.info(caretakers.get(i).getName() + " oskab toita " +
                        caretakers.get(i).getAnimalsToFeed().size() + " " + hungryAnimals.size() + "st.");
            }
        }
    }

    public void feedAllHungryAnimals() {
        for (int i = 0; i < getAllCaretakers().size(); i++) {
            addHungryAnimal();
            caretakers.get(i).feedAnimals();
        }
    }

    public void nextDayButton() {
        for (int i = 0; i < getAllAnimals().size(); i++) {
            animals.get(i).setVoice("");
        }
    }

    public ArrayList<Animal> getHungryAnimal() {
        addHungryAnimal();
        return hungryAnimals;
    }
}
