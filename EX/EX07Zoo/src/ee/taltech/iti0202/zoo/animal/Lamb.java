package ee.taltech.iti0202.zoo.animal;

public class Lamb extends Animal {

    /**
     * @param name name
     */
    public final static int time = 10000;

    public Lamb(String name) {
        super(name, Type.MAMMAL, "Mää", time);
    }

    @Override
    public void setVoice(String voice) {
        super.setVoice("Mää");
    }

    @Override
    public void setTimeBeforeFeeding(Integer timeBeforeFeeding) {
        super.setTimeBeforeFeeding(time);
    }

    @Override
    public void setType(Type type) {
        super.setType(Type.MAMMAL);
    }

    @Override
    public boolean isHungry() {
        return false;
    }

}
