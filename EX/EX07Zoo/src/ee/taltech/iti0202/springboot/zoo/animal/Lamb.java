package ee.taltech.iti0202.springboot.zoo.animal;

public class Lamb extends Animal {

    /**
     * @param name name
     */
    public static final int TIME = 10000;

    /**
     * @param name name
     */
    public Lamb(String name) {
        super(name, Type.MAMMAL, "Mää", TIME);
    }

    @Override
    public void setVoice(String voice) {
        super.setVoice("Mää");
    }

    @Override
    public void setTimeBeforeFeeding(Integer timeBeforeFeeding) {
        super.setTimeBeforeFeeding(TIME);
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
