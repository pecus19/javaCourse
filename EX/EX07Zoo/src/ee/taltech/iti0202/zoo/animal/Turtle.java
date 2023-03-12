package ee.taltech.iti0202.zoo.animal;

import java.util.Objects;
import java.util.Random;

public class Turtle extends Animal {

    public Turtle(String name, Integer timeBeforeFeeding) {
        super(name, Type.AMPHIBIAN, "", timeBeforeFeeding);
    }

    @Override
    public void setType(Type type) {
        super.setType(Type.AMPHIBIAN);
    }

    @Override
    public void setVoice(String voice) {
        super.setVoice("");
    }

    @Override
    public boolean isHungry() {
        Random rand = new Random();
        return rand.nextBoolean();
    }
}
