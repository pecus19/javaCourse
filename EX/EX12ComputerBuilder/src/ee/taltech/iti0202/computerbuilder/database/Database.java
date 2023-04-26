package ee.taltech.iti0202.computerbuilder.database;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class Database {
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
        if (components.containsKey(component.getId())) {
            throw new ProductAlreadyExistsException();
        } else {
            components.put(component.getId(), component);

        }
    }

    public void deleteComponent(int id) throws ProductNotFoundException {
        if (components.containsKey(id)) {
            components.remove(id);
        } else {
            throw new ProductNotFoundException();
        }


    }

    public void increaseComponentStock(int id, int amount) throws ProductNotFoundException {
        boolean check = components.containsKey(id);
        if (!check) {
            throw new ProductNotFoundException();
        }
        Component component = components.get(id);
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        component.setAmount(component.getAmount() + amount);
    }


    public void decreaseComponentStock(int id, int amount) throws OutOfStockException, ProductNotFoundException {
        boolean check = components.containsKey(id);
        Component component = components.get(id);
        if (amount <= 0) {
            throw new IllegalArgumentException();
        } else if (!check) {
            throw new ProductNotFoundException();
        } else if (component.getAmount() < amount) {
            throw new OutOfStockException();
        } else {
            component.setAmount(component.getAmount() - amount);
        }
    }


    public Map<Integer, Component> getComponents() {
        return components;
    }


    public void resetEntireDatabase() {
        Component.setCounter();
        components.clear();
    }

}
