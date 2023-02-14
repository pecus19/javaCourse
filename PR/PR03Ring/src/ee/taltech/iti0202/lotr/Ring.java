package ee.taltech.iti0202.lotr;

public class Ring {
    private Type type;
    private Material material;

    public Ring(Type type, Material material) {
        this.type = type;
        this.material = material;
    }

    /**
     * @return smt.
     */
    public Type getType() {
        return type;
    }

    /**
     * @return smt.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return smt.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @return smt.
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * @return smt.
     */
    public enum Type {
        THE_ONE, GOLDEN, NENYA, OTHER
    }

    /**
     * @return smt.
     */
    public enum Material {
        GOLD, SILVER, BRONZE, PLASTIC, DIAMOND
    }
}
