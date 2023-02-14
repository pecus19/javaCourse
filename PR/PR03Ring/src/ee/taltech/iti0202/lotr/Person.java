package ee.taltech.iti0202.lotr;

import java.util.Objects;

public class Person {
    private String race;
    private String name;
    private Ring ring;

    /**
     * @param race
     * @param name
     * @param ring
     */
    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    /**
     * @param race
     * @param name
     */
    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }

    /**
     * @return smt.
     */
    public String isSauron() {
        if (ring != null) {
            if (Objects.equals(getName(), "Sauron") && getRing().getMaterial() == Ring.Material.GOLD
                    && getRing().getType() == Ring.Type.THE_ONE) {
                return "Affirmative";
            } else if (Objects.equals(getName(), "Sauron") && getRing().getMaterial() != Ring.Material.GOLD
                    && getRing().getType() == Ring.Type.THE_ONE) {
                return "No, the ring is fake!";
            } else if (!Objects.equals(getName(), "Sauron") && getRing().getMaterial() == Ring.Material.GOLD
                    && getRing().getType() == Ring.Type.THE_ONE) {
                return "No, he just stole the ring";
            } else if (Objects.equals(getName(), "Sauron") && (getRing().getType() != Ring.Type.THE_ONE
                    || getRing() == null)) {
                return "No, but he's claiming to be";
            }
        } else {
            return "No";
        }
        return "No";
    }

    /**
     * @return smt.
     */
    public String getRace() {
        return race;
    }

    /**
     * @return smt.
     */
    public void setRace(String race) {
        this.race = race;
    }

    /**
     * @return smt.
     */
    public String getName() {
        return name;
    }

    /**
     * set smt.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return smt.
     */
    public Ring getRing() {
        return ring;
    }

    /**
     * set smt.
     */
    public void setRing(Ring ring) {
        this.ring = ring;
    }
}
