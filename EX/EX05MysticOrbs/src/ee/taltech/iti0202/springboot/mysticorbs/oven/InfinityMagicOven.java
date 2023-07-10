package ee.taltech.iti0202.springboot.mysticorbs.oven;

import ee.taltech.iti0202.springboot.mysticorbs.storage.ResourceStorage;

public class InfinityMagicOven extends MagicOven {
    /**
     * @param name            name
     * @param resourceStorage resourceStorage
     */
    public InfinityMagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    @Override
    public boolean isBroken() {
        return false;
    }
}
