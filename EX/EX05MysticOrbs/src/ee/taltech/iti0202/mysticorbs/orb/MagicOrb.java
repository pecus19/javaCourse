package ee.taltech.iti0202.mysticorbs.orb;

import java.util.Locale;

public class MagicOrb extends Orb {
    public MagicOrb(String creator) {
        super(creator);
    }

    @Override
    public void charge(String resource, int amount) {
        String newStr = resource.replace(" ", "").toLowerCase(Locale.ROOT);
        if (!newStr.equals("dust") && newStr.length() != 0) {
            energy += 2 * (resource.length() * amount);
        }
    }
    @Override
    public String toString() {
        return String.format("MagicOrb by %s", super.creator);
    }
}
