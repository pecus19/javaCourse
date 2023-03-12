package ee.taltech.iti0202.zoo;

import ee.taltech.iti0202.zoo.animal.*;
import ee.taltech.iti0202.zoo.builder.*;
import ee.taltech.iti0202.zoo.caretaker.Caretaker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ZooTest {

    @Test
    void getAllAnimalsVoicesTest() {
        Zoo zoo = new Zoo();
        Animal animal = new AnimalBuilder()
                .setName("Animal")
                .setVoice("Gav")
                .setType(Type.MAMMAL)
                .setTimeBeforeFeeding(1)
                .createAnimal();
        Caretaker caretaker = new CaretakerBuilder()
                .setName("Den")
                .setType(Type.MAMMAL)
                .createCaretaker();
        caretaker.setType(Type.MAMMAL);
        Monkey monkey = new MonkeyBuilder()
                .setName("Monkey")
                .setTimeBeforeFeeding(1)
                .createMonkey();
        monkey.setVoice("BANANA");
        zoo.addCaretaker(caretaker);
        zoo.addAnimal(animal);
        zoo.addAnimal(animal);
        zoo.addAnimal(monkey);
        Map<Animal, String> map = new HashMap<>(Map.of(animal, animal.getVoice(), monkey, monkey.getVoice()));
        assertEquals(map, zoo.getAnimalsVoicesAtTheMoment());
    }

    @Test
    void getAllHungryAnimalsTest() {
        Zoo zoo = new Zoo();
        Caretaker caretaker1 = new CaretakerBuilder()
                .setName("Den")
                .setType(Type.MAMMAL)
                .createCaretaker();
        Animal animal1 = new AnimalBuilder()
                .setName("Animal")
                .setVoice("")
                .setType(Type.MAMMAL)
                .setTimeBeforeFeeding(0)
                .createAnimal();
        Monkey monkey1 = new MonkeyBuilder()
                .setName("Monkey")
                .setTimeBeforeFeeding(1)
                .createMonkey();
        monkey1.setVoice("BANANA");
        Turtle turtle1 = new TurtleBuilder()
                .setName("Turtle")
                .setTimeBeforeFeeding(0)
                .createTurtle();
        turtle1.setVoice("");
        Lamb lamb1 = new LambBuilder()
                .setName("Lamb")
                .createLamb();
        zoo.addCaretaker(caretaker1);
        zoo.addAnimal(animal1);
        zoo.addAnimal(monkey1);
        zoo.addAnimal(turtle1);
        zoo.addAnimal(lamb1);
        List<Animal> checkList = zoo.getHungryAnimal();
        if (checkList.contains(turtle1)) {
            List<Animal> array1 = new ArrayList<>(List.of(animal1, monkey1, turtle1));
            assertEquals(array1, checkList);
        } else {
            List<Animal> array = new ArrayList<>(List.of(animal1, monkey1));
            assertEquals(array, checkList);
        }
    }

    @Test
    void checkHungryAnimalsWithTheButtonTest() {
        Zoo zoo = new Zoo();
        Caretaker caretaker2 = new CaretakerBuilder()
                .setName("Den")
                .setType(Type.MAMMAL)
                .createCaretaker();
        Animal animal2 = new AnimalBuilder()
                .setName("Animal")
                .setVoice("")
                .setType(Type.MAMMAL)
                .setTimeBeforeFeeding(1)
                .createAnimal();
        Monkey monkey2 = new MonkeyBuilder()
                .setName("Monkey")
                .setTimeBeforeFeeding(1)
                .createMonkey();
        Turtle turtle2 = new TurtleBuilder()
                .setName("Turtle")
                .setTimeBeforeFeeding(1)
                .createTurtle();
        turtle2.setType(Type.AMPHIBIAN);
        Lamb lamb2 = new LambBuilder()
                .setName("Lamb")
                .createLamb();
        lamb2.setName("Lamb");
        lamb2.setTimeBeforeFeeding(1);
        lamb2.setType(Type.MAMMAL);
        zoo.addCaretaker(caretaker2);
        zoo.addAnimal(animal2);
        zoo.addAnimal(monkey2);
        zoo.addAnimal(turtle2);
        zoo.addAnimal(lamb2);
        zoo.nextDayButton();
        List<Animal> checkList = zoo.getHungryAnimal();
        if (zoo.getHungryAnimal().contains(turtle2)) {
            List<Animal> array1 = new ArrayList<>(List.of(animal2, monkey2, turtle2));
            assertEquals(array1, checkList);
        } else {
            List<Animal> array = new ArrayList<>(List.of(animal2, monkey2));
            assertEquals(array, checkList);
        }
    }

    @Test
    void caretakerHungryAnimalListTest() {
        Zoo zoo3 = new Zoo();
        Caretaker caretaker3 = new CaretakerBuilder()
                .setName("Den")
                .setType(Type.MAMMAL)
                .createCaretaker();
        Animal animal3 = new AnimalBuilder()
                .setName("Animal")
                .setVoice("")
                .setType(Type.MAMMAL)
                .setTimeBeforeFeeding(0)
                .createAnimal();
        Monkey monkey3 = new MonkeyBuilder()
                .setName("Monkey")
                .setTimeBeforeFeeding(1)
                .createMonkey();
        monkey3.setVoice("BANANA");
        monkey3.setType(Type.MAMMAL);
        Animal animal33 = new AnimalBuilder()
                .setName("Koer")
                .setVoice("")
                .setType(Type.MAMMAL)
                .setTimeBeforeFeeding(0)
                .createAnimal();
        Animal animal34 = new AnimalBuilder()
                .setName("Pen")
                .setVoice("")
                .setType(Type.AMPHIBIAN)
                .setTimeBeforeFeeding(0)
                .createAnimal();
        zoo3.addCaretaker(caretaker3);
        zoo3.addAnimal(animal3);
        zoo3.addAnimal(monkey3);
        zoo3.addAnimal(animal33);
        zoo3.addAnimal(animal34);
        zoo3.addHungryAnimal();
        assertEquals(3, caretaker3.getAnimalsToFeed().size());
    }
    @Test
    void caretakerFeedAnimalsTest() {
        Zoo zoo4 = new Zoo();
        Caretaker caretaker4 = new CaretakerBuilder()
                .setName("Den")
                .setType(Type.MAMMAL)
                .createCaretaker();
        Animal animal4 = new AnimalBuilder()
                .setName("Animal")
                .setVoice("")
                .setType(Type.MAMMAL)
                .setTimeBeforeFeeding(0)
                .createAnimal();
        Animal animal44 = new AnimalBuilder()
                .setName("Koer")
                .setVoice("")
                .setType(Type.AMPHIBIAN)
                .setTimeBeforeFeeding(0)
                .createAnimal();
        zoo4.addCaretaker(caretaker4);
        zoo4.addAnimal(animal4);
        zoo4.addAnimal(animal44);
        zoo4.addHungryAnimal();
        zoo4.feedAllHungryAnimals();
        assertFalse(animal4.isHungry());
    }
    @Test
    void cantAddAnimalToCaretakersHungryListTest() {
        Zoo zoo4 = new Zoo();
        Caretaker caretaker5 = new CaretakerBuilder()
                .setName("Den")
                .setType(Type.MAMMAL)
                .createCaretaker();
        caretaker5.setName("Ago");
        Animal animal5 = new AnimalBuilder()
                .setName("Animal")
                .setVoice("")
                .setType(Type.AMPHIBIAN)
                .setTimeBeforeFeeding(0)
                .createAnimal();
        zoo4.addCaretaker(caretaker5);
        zoo4.addAnimal(animal5);
        zoo4.addHungryAnimal();
        zoo4.feedAllHungryAnimals();
        assertFalse(caretaker5.checkType(animal5));
    }

}