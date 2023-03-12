package ee.taltech.iti0202.zoo.animal;

import java.util.logging.Logger;

public class Animal {
    Logger logger = Logger.getLogger(Animal.class.getName());
    private String name;
    private String voice;
    private Integer timeBeforeFeeding;
    private Type type;

    /**
     * @param name              name
     * @param type              type
     * @param voice             voice
     * @param timeBeforeFeeding timeBeforeFeeding
     */
    public Animal(String name, Type type, String voice, Integer timeBeforeFeeding) {
        this.name = name;
        this.voice = voice;
        this.timeBeforeFeeding = timeBeforeFeeding;
        this.type = type;
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
     * @return voice
     */
    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        if (voice.length() == 0) {
            setTimeBeforeFeeding(0);
        }
        this.voice = voice;
    }

    /**
     * @return false
     */
    public boolean isHungry() {
        return this.getVoice().length() == 0;
    }

    /**
     * smt.
     */
    public void feeding() {
        if (this.isHungry()) {
            setVoice("Brrr!");
            setTimeBeforeFeeding(1);
            logger.info(String.format("The animal with the name %s has been fed", this.getName()));
        }
    }

    /**
     * @param timeBeforeFeeding timeBeforeFeeding
     */
    public void setTimeBeforeFeeding(Integer timeBeforeFeeding) {
        if (this.getVoice().length() == 0) {
            this.timeBeforeFeeding = 0;
        } else {
            this.timeBeforeFeeding = timeBeforeFeeding;
        }
    }

    /**
     * @return type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return timeBeforeFeeding
     */
    public Integer getTimeBeforeFeeding() {
        return timeBeforeFeeding;
    }
}
