package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private Location goTo;
    private List<String> take = new ArrayList<>();
    private List<String> deposit = new ArrayList<>();

    public Action(Location location) {
        this.goTo = location;
    }

    public List<String> getDeposit() {
        return deposit;
    }

    public List<String> getTake() {
        return take;
    }

    public Location getGoTo() {
        return goTo;
    }

    public void addDeposit(String packetName) {
        deposit.add(packetName);
    }

    public void addTake(String packetName) {
        take.add(packetName);
    }
}
