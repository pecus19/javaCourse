package ee.taltech.iti0202.mysticorbs.orb;

import java.util.Locale;

public class Orb {
    protected String creator;
    protected Integer energy;

    public Orb(String creator) {
        this.creator = creator;
        this.energy = 0;
    }

    public void charge(String resource, int amount) {
        String newStr = resource.replace(" ", "").toLowerCase(Locale.ROOT);
        if (!newStr.equals("dust") && newStr.length() != 0) {
            energy += resource.length() * amount;
        }
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    public String toString() {
        return "Orb by " + creator;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }
}
