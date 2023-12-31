package ee.taltech.iti0202.springboot.delivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

public class World {
    private final Map<String, Location> locationMap = new HashMap<>();
    private List<Integer> distances = new ArrayList<>();
    private Map<String, Courier> courierMap = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(World.class.getName());

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        Set<String> newLocations = new HashSet<>(otherLocations);
        if (locationMap.containsKey(name)) {
            return Optional.empty();
        }

        if (newLocations.size() != distances.size()) {
            return Optional.empty();
        }

        for (String location : locationMap.keySet()) {
            if (!newLocations.contains(location)) {
                return Optional.empty();
            }
        }
        Location location = new Location(name);
        for (int i = 0; i < otherLocations.size(); i++) {
            if (locationMap.containsKey(otherLocations.get(i))) {
                location.addDistance(otherLocations.get(i), distances.get(i));
                locationMap.get(otherLocations.get(i)).addDistance(location.getName(), distances.get(i));
            }
        }
        locationMap.put(name, location);
        LOGGER.info("Location: " + name + " added");
        return Optional.of(location);
    }

    public Optional<Courier> addCourier(String name, String locationName) {
        if (name != null && locationName != null && !courierMap.containsKey(name)
                & locationMap.containsKey(locationName)) {
            Courier courier = new Courier(name);
            courier.setLocation(locationMap.get(locationName));
            courierMap.put(name, courier);
            LOGGER.info("Courier: " + name + " added");
            return Optional.of(courier);
        } else {
            return Optional.empty();
        }
    }

    public boolean giveStrategy(String name, Strategy strategy) {
        if (courierMap.containsKey(name)) {
            courierMap.get(name).setStrategy(strategy);
            LOGGER.info("A strategy was added to the courier with the name " + name);
            return true;
        } else {
            return false;
        }
    }

    public void tick() {
//        courierMap.get("Mati")
//                .getLocation().ifPresentOrElse(
//                        location -> {
//                            System.out.println("Location exists");
//                        },
//                        () -> {
//                            System.out.println("location does not exist");
//                        }
//                );
//        for (String courier : courierMap.keySet()) {
//            for (String location : locationMap.keySet()) {
//                if (courierMap.get(courier).getLocation().isPresent()) {
//                    if (courierMap.get(courier).getLocation().get()
//                            .addDistance(courierMap.get(courier).getLocation(),
//                                    courierMap.get(courier).getLocation().get().getDistanceTo(location)
//                                            + 1
//                            ))
//                        courierMap.get(i).setLocation();
//                }
//            }
//        }
    }
}
