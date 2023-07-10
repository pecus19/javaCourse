
package ee.taltech.iti0202.springboot.shelter.animal;

public abstract class Animal {
    /**
     * Enum
     */
    public enum Type {
        HIROLA, TARANTULA, WOMBAT
    }

    private String color;

    /**
     * @param color color
     */
    public Animal(String color) {
        this.color = color;
    }

    /**
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color color
     */
    public void setColor(String color) {
        this.color = color;
    }
}
