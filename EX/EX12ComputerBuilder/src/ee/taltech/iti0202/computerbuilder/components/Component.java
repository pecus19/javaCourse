package ee.taltech.iti0202.computerbuilder.components;

import java.math.BigDecimal;

public class Component {
    private int id;
    private String name;
    private ComponentType type;
    private BigDecimal price;
    private int amount = 1;
    private String manufacturer;
    private int performancePoints;
    private int powerConsumption;
    private static int counter = -1;

    public enum ComponentType {
        CPU, GPU, RAM, MOTHERBOARD, PSU, KEYBOARD, TOUCHPAD, SCREEN, BATTERY, FAN, CASE, HDD, SSD
    }

    public Component(String name, ComponentType type, BigDecimal price, String manufacturer,
                     int performancePoints, int powerConsumption) {
        this.id = -1;
        this.name = name;
        this.type = type;
        this.price = price;
        this.manufacturer = manufacturer;
        this.performancePoints = performancePoints;
        this.powerConsumption = powerConsumption;
        counter++;
        setId(counter);
    }

    public static void setCounter() {
        counter = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ComponentType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPerformancePoints() {
        return performancePoints;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }
}
