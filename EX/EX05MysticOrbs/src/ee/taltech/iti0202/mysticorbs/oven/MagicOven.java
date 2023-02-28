package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven implements Fixable {
    /**
     * \
     *
     * @param name            name
     * @param resourceStorage resourceStorage
     */
    protected int times;
    protected int balls = 0;
    public static final int INT = 25;

    /**
     * @param name            name
     * @param resourceStorage resourceStorage
     */
    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        times = 0;
    }

    @Override
    public boolean isBroken() {
        return balls == 5;
    }

    @Override
    public Optional<Orb> craftOrb() {
        if (isBroken()) {
            try {
                fix(); // Call the fix() method to attempt to fix the orb
            } catch (CannotFixException e) {
//            System.out.println("Cannot craft orb: " + e.getMessage());
//            return Optional.empty();
            }
        }
        if (!isBroken() && !getResourceStorage().isEmpty()) {
            if (getResourceStorage().hasEnoughResource("gold", 1)) {
                if (getResourceStorage().hasEnoughResource("dust", 3)) {
                    Orb orb;
                    if ((balls + 1) % 2 == 0) {
                        orb = new MagicOrb(getName());
                    } else {
                        orb = new Orb(getName());
                    }
                    balls++;
                    getResourceStorage().takeResource("gold", 1);
                    getResourceStorage().takeResource("dust", 3);
                    orb.charge("gold", 1);
                    orb.charge("dust", 3);
                    return Optional.of(orb);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void fix() throws CannotFixException {
        if (!isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else if (getTimesFixed() == 10) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        } else if (!getResourceStorage().hasEnoughResource("clay", (++times) * INT)
                && !getResourceStorage().hasEnoughResource("freezing powder", (++times + 1) * 100)) {
            --times;
            --times;
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        }
        resourceStorage.takeResource("clay", (++times) * INT);
        resourceStorage.takeResource("freezing powder", (++times) * 100);
        times--;
        times--;
        balls *= 0;

    }
    @Override
    public boolean isDied() {
        return times >= 10;
    }


    @Override
    public int getTimesFixed() {
        return times;
    }
}
