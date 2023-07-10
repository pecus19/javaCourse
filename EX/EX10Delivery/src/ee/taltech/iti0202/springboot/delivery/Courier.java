package ee.taltech.iti0202.springboot.delivery;

import java.util.Optional;

public class Courier {
    private String name;
    private Location location;
    private Strategy strategy;


    public Courier(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;

    }

    public Strategy getStrategy() {

        return strategy;
    }

    public String getName() {
        return name;
    }

    public Optional<Location> getLocation() {
        return location == null ? Optional.empty() : Optional.of(location);
    }
}
