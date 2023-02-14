package ee.taltech.iti0202.lotr;

import java.util.Objects;

public class Person {
    private String race;
    private String name;
    private Ring ring;

    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }

    public String isSauron() {
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
        } else {
            return "No";
        }
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ring getRing() {
        return ring;
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }
}
