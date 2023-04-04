package ee.taltech.iti0202.delivery;


import java.util.HashMap;
import java.util.Optional;

public class Location {
    private String name;
    private HashMap<String, Integer> distances = new HashMap<>();
    private HashMap<String, Packet> packets = new HashMap<>();

    public Location(String name) {
        this.name = name;
    }

    public Integer getDistanceTo(String name) {
        return distances.getOrDefault(name, Integer.MAX_VALUE);
    }

    public void addPacket(Packet packet) {
        if (!packets.containsKey(packet.getName())) {
            packets.put(packet.getName(), packet);
        }
    }

    public Optional<Packet> getPacket(String name) {
        if (packets.containsKey(name) && name != null) {
            packets.remove(name);
            return Optional.of(packets.get(name));
        }
        return Optional.empty();
    }

    public void addDistance(String location, int distance) {
        if (!distances.containsKey(location)) {
            distances.put(location, distance);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
