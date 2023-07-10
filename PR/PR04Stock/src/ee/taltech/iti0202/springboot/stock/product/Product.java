package ee.taltech.iti0202.springboot.stock.product;

import ee.taltech.iti0202.springboot.stock.exceptions.StockException;

public class Product {
    public static final int ONE = 1;
    private String name;
    private int price;
    public static int idCounter = 1;
    private final int id;

    /**
     * Create a new product with the given name and price.
     * <p>
     * If the price is negative, throw a StockException with a NEGATIVE_PRICE reason.
     *
     * @param name  Name of the product.
     * @param price Price of the product.
     * @throws StockException NEGATIVE_PRICE
     */
    public Product(String name, int price) throws StockException {
        this.name = name;
        if (price <= -ONE) {
            throw new StockException(StockException.Reason.NEGATIVE_PRICE);
        } else {
            this.price = price;
        }
        id = getNextId();
    }

    /**
     * Returns the next id.
     * <p>
     * This value has to increment with every call.
     *
     * @return The next id.
     */
    public static int getNextId() {
        return idCounter++;
    }

    /**
     * Returns id of the product.
     *
     * @return id of the product.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the product.
     *
     * @return the name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the product.
     *
     * @return the price of the product.
     */
    public int getPrice() {
        return price;
    }

//    @Override
//    public String toString() {
//        return "Product{" +
//                "name='" + name + '\'' +
//                ", price=" + price + ", id=" + getId() +
//                '}';
//    }
}
