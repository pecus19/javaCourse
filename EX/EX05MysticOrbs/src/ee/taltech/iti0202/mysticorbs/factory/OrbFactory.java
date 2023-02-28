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

    /**
     * @param resourceStorage resourceStorage
     */
    public OrbFactory(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;

    }

    /**
     * \
     *
     * @param oven oven
     */
    public void addOven(Oven oven) {
        if (!ovens.contains(oven)) {
            ovens.add(oven);
        }
    }

    /**
     * @return ovens
     */
    public List<Oven> getOvens() {
        return ovens;
    }

    /**
     * @return List
     */
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

    /**
     * \
     *
     * @return int
     */
    public int produceOrbs() {
        for (int i = 0; i < getOvens().size(); i++) {
            Optional<Orb> newOrb = getOvens().get(i).craftOrb();
            newOrb.ifPresent(orb -> orbs.add(orb));
        }
        return orbs.size();
    }

    /**
     * @param cycles cycles
     * @return int
     */
    public int produceOrbs(int cycles) {
        for (int i = 0; i < cycles; i++) {
            for (int j = 0; j < getOvens().size(); j++) {
                Optional<Orb> newOrb = getOvens().get(j).craftOrb();
//                System.out.println(newOrb.toString());
                newOrb.ifPresent(orb -> orbs.add(orb));

            }
        }
        return orbs.size();
    }

    // second part

    /**
     * //     * @return List
     * //
     */
    public List<Oven> getOvensThatCannotBeFixed() {
        return null;
    }

    /**
     * smt.
     */
    public void getRidOfOvensThatCannotBeFixed() {
    }

    /**
     * smt.
     */
    public void optimizeOvensOrder() {

    }
}
