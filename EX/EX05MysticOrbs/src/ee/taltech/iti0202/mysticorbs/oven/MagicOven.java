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
    protected static int numForCreation = 1;

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
            if (numForCreation % 2 == 0) {
                if (getResourceStorage().hasEnoughResource("gold", 1)) {
                    if (getResourceStorage().hasEnoughResource("dust", 3)) {
                        MagicOrb orb2 = new MagicOrb(getName());
                        getResourceStorage().takeResource("gold", 1);
                        getResourceStorage().takeResource("dust", 3);
                        orb2.charge("gold", 1);
                        orb2.charge("dust", 3);
                        balls++;
                        numForCreation++;
                        return Optional.of(orb2);
                    }
                }
            } else {
                if (getResourceStorage().hasEnoughResource("pearl", 1)) {
                    if (getResourceStorage().hasEnoughResource("silver", 1)) {
                        Orb orb = new Orb(getName());
                        getResourceStorage().takeResource("pearl", 1);
                        getResourceStorage().takeResource("silver", 1);
                        orb.charge("pearl", 1);
                        orb.charge("silver", 1);
                        balls++;
                        numForCreation++;
                        return Optional.of(orb);
                    }
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
        } else if (!getResourceStorage().hasEnoughResource("clay", (++times) * 25)
                && !getResourceStorage().hasEnoughResource("freezing powder", (++times + 1) * 100)) {
            --times;
            --times;
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        }
        resourceStorage.takeResource("clay", (++times) * 25);
        resourceStorage.takeResource("freezing powder", (++times) * 100);
        times--;
        times--;
        balls *= 0;

    }

    @Override
    public int getTimesFixed() {
        return times;
    }
}
