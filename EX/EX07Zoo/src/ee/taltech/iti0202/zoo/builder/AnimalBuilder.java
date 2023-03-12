package ee.taltech.iti0202.zoo.builder;

import ee.taltech.iti0202.zoo.animal.Animal;
import ee.taltech.iti0202.zoo.animal.Type;
import ee.taltech.iti0202.zoo.caretaker.Caretaker;

public class AnimalBuilder {
    private String name;
    private Type type;
    private String voice;
    private Integer timeBeforeFeeding;

    public AnimalBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AnimalBuilder setType(Type type) {
        this.type = type;
        return this;
    }

    public AnimalBuilder setVoice(String voice) {
        this.voice = voice;
        return this;
    }

    public AnimalBuilder setTimeBeforeFeeding(Integer timeBeforeFeeding) {
        this.timeBeforeFeeding = timeBeforeFeeding;
        return this;
    }

    public Animal createAnimal() {
        return new Animal(name, type, voice, timeBeforeFeeding);
    }

}