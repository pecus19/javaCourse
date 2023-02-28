package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven {
    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        super.balls = 0;
    }

    @Override
    public boolean isBroken() {
        return super.balls >= 5;
    }

    @Override
    public Optional<Orb> craftOrb() {
        if (!isBroken() && !getResourceStorage().isEmpty()) {
            if (getResourceStorage().hasEnoughResource("gold", 1)) {
                if (getResourceStorage().hasEnoughResource("dust", 3)) {
                    MagicOrb orb = new MagicOrb(getName());
                    getResourceStorage().takeResource("gold", 1);
                    getResourceStorage().takeResource("dust", 3);
                    orb.charge("gold", 1);
                    orb.charge("dust", 3);
                    balls++;
                    return Optional.of(orb);
                }
            }
        }
        return Optional.empty();
    }
}
