package ee.taltech.iti0202.zoo.builder;

import ee.taltech.iti0202.zoo.animal.Turtle;

public class TurtleBuilder {
    private String name;
    private Integer timeBeforeFeeding;

    /**
     * @param name name
     * @return this
     */
    public TurtleBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @param timeBeforeFeeding timeBeforeFeeding
     * @return this
     */
    public TurtleBuilder setTimeBeforeFeeding(Integer timeBeforeFeeding) {
        this.timeBeforeFeeding = timeBeforeFeeding;
        return this;
    }

    /**
     * @return Turtle
     */
    public Turtle createTurtle() {
        return new Turtle(name, timeBeforeFeeding);
    }
}
