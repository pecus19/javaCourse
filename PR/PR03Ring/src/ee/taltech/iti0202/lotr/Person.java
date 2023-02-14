package ee.taltech.iti0202.lotr;

import java.util.Objects;

public class Person {
    private String race;
    private String name;
    private Ring ring;

    /**
     * @param race race
     * @param name name
     * @param ring ring
     */
    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    /**
     * @param race race
     * @param name name
     */
    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }

    /**
     * @return smt.
     */
    public String isSauron() {
        if (this.ring == null && Objects.equals(name, "Sauron")) {
            return "No, but he's claiming to be";
        } else if (this.ring == null) {
            return "No";
        }
        if (Objects.equals(getName(), "Sauron") && getRing().getMaterial() == Ring.Material.GOLD && getRing().getType() == Ring.Type.THE_ONE) {
            return "Affirmative";
        } else if (ring != null && Objects.equals(getName(), "Sauron") && getRing().getMaterial() != Ring.Material.GOLD
                && getRing().getType() == Ring.Type.THE_ONE) {
            return "No, the ring is fake!";
        } else if (ring != null && !Objects.equals(getName(), "Sauron") && getRing().getMaterial() == Ring.Material.GOLD
                && getRing().getType() == Ring.Type.THE_ONE) {
            return "No, he just stole the ring";
        } else if (ring != null && Objects.equals(getName(), "Sauron") && getRing().getType() != Ring.Type.THE_ONE) {
            return "No, but he's claiming to be";
        } else if (ring != null && Objects.equals(getName(), "Sauron") && getRing() == null) {
            return "No, but he's claiming to be";
        } else {
            return "No";
        }
    }

    /**
     * @return race.
     */
    public String getRace() {
        return race;
    }

    /**
     * @param race race
     */
    public void setRace(String race) {
        this.race = race;
    }

    /**
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return ring.
     */
    public Ring getRing() {
        return ring;
    }

    /**
     * @param ring ring
     */
    public void setRing(Ring ring) {
        this.ring = ring;
    }
}
