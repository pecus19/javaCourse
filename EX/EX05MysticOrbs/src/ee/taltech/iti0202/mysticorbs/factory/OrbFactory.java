package ee.taltech.iti0202.mysticorbs.factory;

import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.Oven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrbFactory {
    protected ResourceStorage resourceStorage;
    protected List<Oven> ovens = new ArrayList<>();
    protected List<Orb> orbs = new ArrayList<>();

    public OrbFactory(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
    }

    public void addOven(Oven oven) {
        if (!ovens.contains(oven)) {
            ovens.add(oven);
        }
    }

    public List<Oven> getOvens() {
        return ovens;
    }

    public List<Orb> getAndClearProducedOrbsList() {
        List<Orb> newOrb = new ArrayList<>();
        if (orbs.isEmpty()) {
            return newOrb;
        } else {
            ArrayList<Orb> newOrb2 = new ArrayList(orbs);
            orbs.clear();
            return newOrb2;
        }
    }

    public int produceOrbs() {
        for (int i = 0; i < getOvens().size(); i++) {
            Optional<Orb> newOrb = getOvens().get(i).craftOrb();
            newOrb.ifPresent(orb -> orbs.add(orb));
        }
        return orbs.size();
    }

    public int produceOrbs(int cycles) {
        for (int i = 0; i < cycles; i++) {
            for (int j = 0; i < getOvens().size(); j++) {
                Optional<Orb> newOrb = getOvens().get(j).craftOrb();
                newOrb.ifPresent(orb -> orbs.add(orb));
            }
        }
        return orbs.size();
    }
}
