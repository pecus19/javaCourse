package ee.taltech.iti0202.bakery.product;

public class Product {
    private String name;
    private int id;
    private double price;
    private Double kilocalories;
    private static int idCounter = -1;
    private boolean inTheBakery;
    private Product.bakeryTypes BakeryTypes;
    private double ratingMultiplier = 0;
    private static final double MULTIPLIER = 1.5;


    public double getMaxPrice() {
        return getPrice() * MULTIPLIER;
    }

    public Product(String name, double price, Double kilocalories, Product.bakeryTypes bakeryTypes) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if (kilocalories < 0) {
            throw new IllegalArgumentException("Calories cannot be negative");
        }
        if (bakeryTypes == null) {
            throw new IllegalArgumentException("You need to write a type of a product");
        }
        this.name = name;
        this.price = price;
        this.kilocalories = kilocalories;
        this.BakeryTypes = bakeryTypes;
        idCounter++;
        setId(idCounter);
        inTheBakery = false;
    }


    public Product.bakeryTypes getBakeryTypes() {
        return BakeryTypes;
    }

    public double getRatingMultiplier() {
        return ratingMultiplier;
    }

    public void setRatingMultiplier(double ratingMultiplier) {
        this.ratingMultiplier = ratingMultiplier;
    }

    public enum bakeryTypes {
        CAKE, BREAD, COOKIE, BUN, PIE
    }

    public String getName() {
        return name;
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
        this.price = Math.min(price, getMaxPrice());
    }

    public Double getKilocalories() {
        return kilocalories;
    }

    public boolean isInTheBakery() {
        return inTheBakery;
    }

    public void setInTheBakery(boolean inTheBakery) {
        this.inTheBakery = inTheBakery;
    }
}
