package ee.taltech.iti0202.zoo.builder;

import ee.taltech.iti0202.zoo.animal.Monkey;

public class MonkeyBuilder {
    private String name;
    private Integer timeBeforeFeeding;

    public MonkeyBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MonkeyBuilder setTimeBeforeFeeding(Integer timeBeforeFeeding) {
        this.timeBeforeFeeding = timeBeforeFeeding;
        return this;
    }

    public Monkey createMonkey() {
        return new Monkey(name, timeBeforeFeeding);
    }
}