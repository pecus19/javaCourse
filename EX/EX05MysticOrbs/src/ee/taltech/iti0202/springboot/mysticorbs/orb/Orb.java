package ee.taltech.iti0202.springboot.mysticorbs.orb;

import java.util.Locale;

public class Orb {
    protected String creator;
    protected Integer energy;

    /**
     * @param creator creator
     */
    public Orb(String creator) {
        this.creator = creator;
        this.energy = 0;
    }

    /**
     * @param resource resource
     * @param amount   amount
     */
    public void charge(String resource, int amount) {
        String newStr = resource.replace(" ", "").toLowerCase(Locale.ROOT);
        if (!newStr.equals("dust") && newStr.length() != 0 && amount > -1) {
            energy += resource.length() * amount;
        }
    }

    /**
     * @return energy
     */
    public int getEnergy() {
        return energy;
    }

    @Override
    public String toString() {
        return "Orb by " + creator;
    }

    /**
     * @param energy energy
     */
    public void setEnergy(Integer energy) {
        this.energy = energy;
    }
}
