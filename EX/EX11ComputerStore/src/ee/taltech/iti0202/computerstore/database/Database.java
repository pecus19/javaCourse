package ee.taltech.iti0202.computerstore.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

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

    public void saveToFile(String location) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path path = Paths.get(location);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadFromFile(String location) {
        Gson gson = new Gson();
        Path path = Paths.get(location);
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                instance = gson.fromJson(line, Database.class);
            }
        } catch (IOException e) {
            System.out.println("IOException:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
