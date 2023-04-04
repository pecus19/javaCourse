package ee.taltech.iti0202.delivery;

public class Packet {
    private String name;
    private Location target;

    public void setName(String name) {
        this.name = name;
    }

    public void setTarget(Location target) {
        this.target = target;
    }

    public Packet(String name, Location target) {
        this.name = name;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public Location getTarget() {
        return target;
    }
}
