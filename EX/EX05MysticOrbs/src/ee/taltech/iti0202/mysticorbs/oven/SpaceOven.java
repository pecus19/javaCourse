package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {
    protected int times;
    public static final int INT = 15;
    public static final int INT2 = 25;
    public static final int INT3 = 40;
    protected int balls = 0;


    /**
     * @param name            name
     * @param resourceStorage resourceStorage
     */
    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        times = 0;
    }

    @Override
    public boolean isBroken() {
        return balls >= INT2;
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
        if (!isBroken() && !getResourceStorage().isEmpty()
                && getResourceStorage().hasEnoughResource("meteorite stone", 1)
                && getResourceStorage().hasEnoughResource("star fragment", INT)) {
            SpaceOrb orb = new SpaceOrb(getName());
            getResourceStorage().takeResource("meteorite stone", 1);
            getResourceStorage().takeResource("star fragment", INT);
            orb.charge("meteorite stone", 1);
            orb.charge("star fragment", INT);
            balls++;
            return Optional.of(orb);
        } else {
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
                return Optional.empty();
            }
            return Optional.empty();
        }
    }

    @Override
    public void fix() throws CannotFixException {
        int num = ++times;
        System.out.println(times);
        if (!isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else if (getTimesFixed() == 5) {
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        } else if (!getResourceStorage().hasEnoughResource("liquid silver", (num) * INT3)) {
            if (!getResourceStorage().hasEnoughResource("star essence", (num) * 10)) {
                throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
            }
        }
        if (getResourceStorage().hasEnoughResource("liquid silver", (num) * INT3)) {
            resourceStorage.takeResource("liquid silver", (num) * INT3);
        } else {
            resourceStorage.takeResource("star essence", (num) * 10);
        }
        balls *= 0;

    }

    @Override
    public int getTimesFixed() {
        return times;
    }
}
