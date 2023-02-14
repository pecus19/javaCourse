package ee.taltech.iti0202.lotr;

public class Ring {
    private Type type;
    private Material material;

    /**
     * @param type
     * @param material
     */
    public Ring(Type type, Material material) {
        this.type = type;
        this.material = material;
    }

    /**
     * @return type.
     */
    public Type getType() {
        return type;
    }

    /**
     * set smt.
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
     * set smt.
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Types.
     */
    public enum Type {
        THE_ONE, GOLDEN, NENYA, OTHER
    }

    /**
     * Materials.
     */
    public enum Material {
        GOLD, SILVER, BRONZE, PLASTIC, DIAMOND
    }
}
