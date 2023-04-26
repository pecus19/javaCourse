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

//    @Override
//    public String toString() {
//        return "Component{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", type=" + type +
//                ", price=" + price +
//                ", amount=" + amount +
//                ", manufacturer='" + manufacturer + '\'' +
//                ", performancePoints=" + performancePoints +
//                ", powerConsumption=" + powerConsumption +
//                '}';
//    }

    public enum ComponentType {
        CPU, GPU, RAM, MOTHERBOARD, PSU, KEYBOARD, TOUCHPAD, SCREEN, BATTERY, FAN, CASE, HDD, SSD
    }
//    public enum StorageType{
//        HDD, SSD
//    }

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

    public void setName(String name) {
        this.name = name;
    }

    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPerformancePoints() {
        return performancePoints;
    }

    public void setPerformancePoints(int performancePoints) {
        this.performancePoints = performancePoints;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }
}
