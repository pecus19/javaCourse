package ee.taltech.iti0202.bakery.product;

public class Product {
    private String name;
    private int id;
    private double price;
    private Double kilocalories;
    private static int idCounter = -1;
    private boolean inTheBakery;
    private Product.bakeryTypes bakerytypes;
    private double ratingMultiplier = 0;


    public double getMaxPrice() {
        return getPrice() * 1.5;
    }

    public Product(String name, double price, Double kilocalories, Product.bakeryTypes bakeryTypes) {
        this.name = name;
        this.price = price;
        this.kilocalories = kilocalories;
        this.bakerytypes = bakeryTypes;
        idCounter++;
        setId(idCounter);
        inTheBakery = false;
    }

    public void setBakerytypes(Product.bakeryTypes bakerytypes) {
        this.bakerytypes = bakerytypes;
    }

    public Product.bakeryTypes getBakerytypes() {
        return bakerytypes;
    }

    public double getRatingMultiplier() {
        return ratingMultiplier;
    }

    public void setRatingMultiplier(double ratingMultiplier) {
        this.ratingMultiplier = ratingMultiplier;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", kilocalories=" + kilocalories +
                ", bakerytypes=" + bakerytypes +
                ", ratingMultiplier=" + ratingMultiplier +
                '}';
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
        this.price = Math.min(price, getMaxPrice());
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
