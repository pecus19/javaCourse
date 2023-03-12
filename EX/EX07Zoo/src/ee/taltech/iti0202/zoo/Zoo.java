package ee.taltech.iti0202.zoo;

import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.caretaker.Caretaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Zoo {
    private List<String> topList = new ArrayList<>();
    private ArrayList<Caretaker> caretakers = new ArrayList<>();
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Animal> hungryAnimals = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(Zoo.class.getName());

    /**
     * @return caretakers
     */
    public ArrayList<Caretaker> getAllCaretakers() {
        return caretakers;
    }

    /**
     * @param caretaker caretaker
     */
    public void addCaretaker(Caretaker caretaker) {
        if (caretaker != null && !getAllCaretakers().contains(caretaker)) {
            caretakers.add(caretaker);
        }
    }

    /**
     * @param animal animal
     */
    public void addAnimal(Animal animal) {
        if (animal != null && !getAllAnimals().contains(animal)) {
            animals.add(animal);
        }
    }

    /**
     * @return animals
     */
    public ArrayList<Animal> getAllAnimals() {
        return animals;
    }

    /**
     * @return voicesMap
     */
    public Map<Animal, String> getAnimalsVoicesAtTheMoment() {
        Map<Animal, String> voicesMap = new HashMap<>();
        if (animals.size() != 0) {
            for (int i = 0; i < animals.size(); i++) {
                voicesMap.put(animals.get(i), animals.get(i).getVoice());
            }
        }
        return voicesMap;
    }

    /**
     * add Hungry Animal
     */
    public void addHungryAnimal() {
        for (int i = 0; i < getAllAnimals().size(); i++) {
            if (!hungryAnimals.contains(animals.get(i)) & animals.get(i).isHungry()) {
                hungryAnimals.add(animals.get(i));
            }
        }
        if (hungryAnimals != null) {
            for (int i = 0; i < getAllCaretakers().size(); i++) {
                for (int j = 0; j < hungryAnimals.size(); j++) {
                    if (caretakers.get(i).checkType(animals.get(j))) {
                        caretakers.get(i).addAnimalsToFeed(animals.get(j));
                    }
                }
                String output = "Oletame, et meil on " + hungryAnimals.size() + " näljast looma. "
                        + caretakers.get(i).getName() + " oskab toita "
                        + caretakers.get(i).getAnimalsToFeed().size() + " " + hungryAnimals.size() + "st.";
                topList.add(output);
            }
        }
    }

    /**
     * feedAllHungryAnimals
     */
    public void feedAllHungryAnimals() {
        for (int i = 0; i < getAllCaretakers().size(); i++) {
            addHungryAnimal();
            caretakers.get(i).feedAnimals();
        }
    }

    /**
     * nextDayButton
     */
    public void nextDayButton() {
        for (int i = 0; i < getAllAnimals().size(); i++) {
            animals.get(i).setVoice("");
        }
    }

    /**
     * @return hungryAnimals
     */
    public ArrayList<Animal> getHungryAnimal() {
        addHungryAnimal();
        return hungryAnimals;
    }

    /**
     * @return topList
     */
    public String bestCaretaker() {
        return topList.get(topList.size() - 1);
    }
}
