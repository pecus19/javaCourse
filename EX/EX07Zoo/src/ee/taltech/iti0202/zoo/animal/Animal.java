package ee.taltech.iti0202.zoo.animal;

import java.util.logging.Logger;

public class Animal {
    Logger logger = Logger.getLogger(Animal.class.getName());
    private String name;
    private String voice;
    private Integer timeBeforeFeeding;
    private Type type;

    public Animal(String name, Type type, String voice, Integer timeBeforeFeeding) {
        this.name = name;
        this.voice = voice;
        this.timeBeforeFeeding = timeBeforeFeeding;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        if (voice.length() == 0) {
            setTimeBeforeFeeding(0);
        }
        this.voice = voice;
    }

    public boolean isHungry() {
        return this.getVoice().length() == 0;
    }

    public void feeding() throws IllegalArgumentException {
        if (this.isHungry()) {
            setVoice("Brrr!");
            setTimeBeforeFeeding(1);
            logger.info(String.format("The animal with the name %s has been fed", this.getName()));
        } else {
            throw new IllegalArgumentException(String.format("The animal with the name %s doesn't want to eat"
                    , this.getName()));
        }
    }

    public Integer getTimeBeforeFeeding() {
        return timeBeforeFeeding;
    }

    public void setTimeBeforeFeeding(Integer timeBeforeFeeding) {
        if (this.getVoice().length() == 0) {
            this.timeBeforeFeeding = 0;
        } else {
            this.timeBeforeFeeding = timeBeforeFeeding;
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
