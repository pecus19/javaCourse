package ee.taltech.iti0202.computerstore.database;

import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private final Map<Integer, Component> components = new HashMap<>();
    private static Database instance = null;

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void saveComponent(Component component) throws ProductAlreadyExistsException {
        boolean check = components.containsKey(component.getId());
        if (check) {
            throw new ProductAlreadyExistsException();
        }
        components.put(component.getId(), component);
    }

    public void deleteComponent(int id) throws ProductNotFoundException {
        boolean check = components.containsKey(id);
        if (check) {
            throw new ProductNotFoundException();
        }
        components.remove(id);
    }

    public void increaseComponentStock(int id, int amount) throws ProductNotFoundException {
        boolean check = components.containsKey(id);
        if (check) {
            throw new ProductNotFoundException();
        }
        Component component = components.get(id);
        if (component.getAmount() <= 0) {
            throw new IllegalArgumentException();
        }
        component.setAmount(component.getAmount() + amount);
    }

    public void decreaseComponentStock(int id, int amount) throws OutOfStockException, ProductNotFoundException {
        boolean check = components.containsKey(id);
        if (check) {
            throw new ProductNotFoundException();
        }
        Component component = components.get(id);
        if (component.getAmount() <= 0) {
            throw new IllegalArgumentException();
        }
        if (component.getAmount() > amount) {
            throw new OutOfStockException();
        }
        component.setAmount(component.getAmount() + amount);
    }

    public Map<Integer, Component> getComponents() {
        return components;
    }

    public void resetEntireDatabase() {
        BigDecimal myBigDecimal = new BigDecimal(12);
        Component component = new Component("1", Component.Type.GPU, myBigDecimal,
                "Man", 1, 2);
        component.setCounter();
        components.clear();

    }

    public void saveToFile(String location) {
    }

    public void loadFromFile(String location) {
    }
}
