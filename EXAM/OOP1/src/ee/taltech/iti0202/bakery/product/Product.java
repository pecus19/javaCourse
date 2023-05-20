package ee.taltech.iti0202.bakery.product;

import java.lang.reflect.Type;

public class Product {
    private String name;
    private int id;
    private double price;
    private Double kilocalories;
    private static int idCounter = -1;
    private boolean inTheBakery;
    private Product.bakeryTypes bakeryTypes;

    public Product(String name, double price, Double kilocalories, Product.bakeryTypes bakeryTypes) {
        this.name = name;
        this.price = price;
        this.kilocalories = kilocalories;
        this.bakeryTypes = bakeryTypes;
        idCounter++;
        setId(idCounter);
        inTheBakery = false;
    }

    public void setBakeryTypes(Product.bakeryTypes bakeryTypes) {
        this.bakeryTypes = bakeryTypes;
    }

    public Product.bakeryTypes getBakeryTypes() {
        return bakeryTypes;
    }

    public enum bakeryTypes {
        CAKE, BREAD, COOKIE, BUN, PIE
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getKilocalories() {
        return kilocalories;
    }

    public void setKilocalories(Double kilocalories) {
        this.kilocalories = kilocalories;
    }

    public boolean isInTheBakery() {
        return inTheBakery;
    }

    public void setInTheBakery(boolean inTheBakery) {
        this.inTheBakery = inTheBakery;
    }
}
