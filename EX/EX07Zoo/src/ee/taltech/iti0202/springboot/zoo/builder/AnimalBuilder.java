package ee.taltech.iti0202.springboot.zoo.builder;

import ee.taltech.iti0202.springboot.zoo.animal.Animal;
import ee.taltech.iti0202.springboot.zoo.animal.Type;

public class AnimalBuilder {
    private String name;
    private Type type;
    private String voice;
    private Integer timeBeforeFeeding;

    /**
     * @param name name
     * @return this
     */
    public AnimalBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param type type
     * @return this
     */
    public AnimalBuilder setType(Type type) {
        this.type = type;
        return this;
    }

    /**
     * @param voice voice
     * @return this
     */
    public AnimalBuilder setVoice(String voice) {
        this.voice = voice;
        return this;
    }

    /**
     * @param timeBeforeFeeding timeBeforeFeeding
     * @return this
     */
    public AnimalBuilder setTimeBeforeFeeding(Integer timeBeforeFeeding) {
        this.timeBeforeFeeding = timeBeforeFeeding;
        return this;
    }

    /**
     * @return Animal
     */
    public Animal createAnimal() {
        return new Animal(name, type, voice, timeBeforeFeeding);
    }

}
