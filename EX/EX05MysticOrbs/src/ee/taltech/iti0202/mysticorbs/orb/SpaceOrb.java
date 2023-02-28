package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {
    public SpaceOrb(String creator) {
        super(creator);
        super.energy = 100;
    }

    @Override
    public void charge(String resource, int amount) {
        energy += 0;
    }

    @Override
    public String toString() {
        return String.format("SpaceOrb by %s", super.creator);
    }

    public boolean absorb(Orb orb) {
        if (getEnergy() > orb.getEnergy()) {
            setEnergy(getEnergy() + orb.getEnergy());
            orb.setEnergy(0);
            return true;
        } else {
            return false;
        }
    }
}
