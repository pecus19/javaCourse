package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class Oven implements Fixable {
    protected String name;
    protected ResourceStorage resourceStorage;
    protected Integer balls;
    protected Orb orb;
    public static final int INT = 15;

    /**
     * @param name            name
     * @param resourceStorage resourceStorage
     */
    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        this.resourceStorage = resourceStorage;
        balls = 0;
    }

    public int getCreatedOrbsAmount() {
        return balls;
    }

    public boolean isBroken() {
        return balls >= INT;
    }

    /**
     * @return Orb
     */
    public Optional<Orb> craftOrb() {
        if (!isBroken() && !getResourceStorage().isEmpty()) {
            if (getResourceStorage().hasEnoughResource("pearl", 1)) {
                if (getResourceStorage().hasEnoughResource("silver", 1)) {
                    Orb orb = new Orb(getName());
                    getResourceStorage().takeResource("pearl", 1);
                    getResourceStorage().takeResource("silver", 1);
                    orb.charge("pearl", 1);
                    orb.charge("silver", 1);
                    balls++;
                    return Optional.of(orb);
                }
            }
        }
        return Optional.empty();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceStorage getResourceStorage() {
        return resourceStorage;
    }

    public void setResourceStorage(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
    }

    @Override
    public void fix() throws CannotFixException {

    }

    @Override
    public int getTimesFixed() {
        return 0;
    }

    //second part

    /**
     * @param o o
     * @return int
     */
    public int compareTo(Oven o) {
        return 0;
    }
}
