package ee.taltech.iti0202.springboot.lotr;

public class Ring {
    private Type type;
    private Material material;

    /**
     * @param type     type
     * @param material material
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
     * @param type type
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
     * @param material material
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
