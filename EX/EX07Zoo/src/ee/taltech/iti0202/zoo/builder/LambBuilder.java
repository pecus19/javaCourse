package ee.taltech.iti0202.zoo.builder;

import ee.taltech.iti0202.zoo.animal.Lamb;

public class LambBuilder {
    private String name;

    public LambBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Lamb createLamb() {
        return new Lamb(name);
    }
}