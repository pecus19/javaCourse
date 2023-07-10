package ee.taltech.iti0202.springboot.zoo.animal;

import java.util.Objects;
import java.util.Random;

public class Monkey extends Animal {
    /**
     *
     * @param name name
     * @param timeBeforeFeeding timeBeforeFeeding
     */
    public Monkey(String name, Integer timeBeforeFeeding) {
        super(name, Type.MAMMAL, "uuh", timeBeforeFeeding);
    }

    @Override
    public void setType(Type type) {
        super.setType(Type.MAMMAL);
    }

    @Override
    public boolean isHungry() {
        return this.getVoice().equals("BANANA");
    }

    @Override
    public void setVoice(String voice) {
        if (Objects.equals(voice, "") || Objects.equals(voice, "BANANA")) {
            super.setVoice("BANANA");
            super.setTimeBeforeFeeding(0);
        } else {
            Random rand = new Random();
            boolean randomBoolean = rand.nextBoolean();
            super.setTimeBeforeFeeding(2);
            if (randomBoolean) {
                super.setVoice("uuh");
            } else {
                super.setVoice("ääh");
            }
        }

    }
}
