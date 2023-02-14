package ee.taltech.iti0202.lotr;

public class Ring {
    private Type type;
    public  Material material;

    public Ring(Type type, Material material) {
        this.type = type;
        this.material = material;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public enum Type {
        THE_ONE, GOLDEN, NENYA, OTHER
    }
    public enum Material {
        GOLD, SILVER, BRONZE, PLASTIC, DIAMOND
    }
}
