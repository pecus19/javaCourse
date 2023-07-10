package ee.taltech.iti0202.springboot.zoo.builder;

import ee.taltech.iti0202.springboot.zoo.animal.Lamb;

public class LambBuilder {
    private String name;

    /**
     * @param name name
     * @return this
     */
    public LambBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return Lamb
     */
    public Lamb createLamb() {
        return new Lamb(name);
    }
}
