package ee.taltech.iti0202.zoo.builder;

import ee.taltech.iti0202.zoo.animal.Type;
import ee.taltech.iti0202.zoo.caretaker.Caretaker;

public class CaretakerBuilder {
    private String name;
    private Type type;

    public CaretakerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CaretakerBuilder setType(Type type) {
        this.type = type;
        return this;
    }

    public Caretaker createCaretaker() {
        return new Caretaker(name, type);
    }
}