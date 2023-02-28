package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven {
    public final static int INT = 15;
    public final static int INT2 = 25;

    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        balls = 0;
    }

    @Override
    public boolean isBroken() {
        return balls == INT2;
    }

    @Override
    public Optional<Orb> craftOrb() {
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

}
