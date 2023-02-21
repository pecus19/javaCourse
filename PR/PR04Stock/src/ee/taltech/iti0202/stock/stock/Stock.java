package ee.taltech.iti0202.stock.stock;

import ee.taltech.iti0202.stock.exceptions.StockException;
import ee.taltech.iti0202.stock.product.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The stock class.
 * <p>
 * Each stock has a name, list of products that are currently stored in it
 * and the maximum amount of products that stock can store.
 * <p>
 * If adding a product to the stock is not possible, due to exceeding the maximum limit of products
 * OR stock already contains a product, a StockException must be thrown,
 * otherwise product must be added to the stock.
 * <p>
 * When getting (not removing) a product from our stock,
 * it is important to find the product with the lowest price first.
 */

public class Stock {
    private String name;
    private int maxCapacity;
    private List<Product> products = new ArrayList<>();
    private int numOfProducts = 0;
    private int totalPrice;

    /**
     * Create a new stock with the given name and the max capacity for the products.
     *
     * @param name        the name of the stock.
     * @param maxCapacity max amount of products allowed in the stock.
     */
    public Stock(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
    }

    /**
     * Add a product to the stock, if stock does not contain the product and is not full yet.
     * <p>
     * Check in following order:
     * If stock already contains a product, throw an StockException with a STOCK_ALREADY_CONTAINS_PRODUCT reason.
     * If stock is full, throw a StockException with a STOCK_IS_FULL reason.
     *
     * @param product to be added
     * @throws StockException STOCK_ALREADY_CONTAINS_PRODUCT, STOCK_IS_FULL
     */

    public void addProduct(Product product) throws StockException {
        if (!products.contains(product)) {
            if ((numOfProducts += 1) <= maxCapacity) {
                products.add(product);
                totalPrice += product.getPrice();
            } else {
                throw new StockException(StockException.Reason.STOCK_IS_FULL);
            }

        } else {
            throw new StockException(StockException.Reason.STOCK_ALREADY_CONTAINS_PRODUCT);
        }
    }

    /**
     * Get a product from a stock by name with the lowest price.
     * <p>
     * If there are several products with the same name and price,
     * returns the product with the lowest id.
     *
     * @param name the product's name
     * @return Optional
     */
    public Optional<Product> getProduct(String name) {
        getProducts(name);
        return Optional.of(getProducts(name).get(0));
    }

    /**
     * Remove a product from a stock, return the product that got removed
     * if stock has a given product.
     * <p>
     * Use getProduct() method to get the product.
     * <p>
     * If there is nothing to remove, return Optional.empty()
     *
     * @param name Name of the product to be removed
     * @return Optional
     */

    public Optional<Product> removeProduct(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (name != null) {
                if (!Objects.equals(products.get(i).getName(), name)) {
                    return Optional.empty();
                } else {
                    Optional<Product> ret = Optional.ofNullable(getProducts(name).get(0));
                    products.remove(getProducts(name).get(0));
                    numOfProducts -= 1;
                    totalPrice -= getProducts(name).get(0).getPrice();
                    return ret;
                }
            }
        }
        return Optional.empty();

    }

    /**
     * Get a list of current products in the stock.
     *
     * @return List
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Get a list of current products in the stock filtered by name.
     * <p>
     * Order the products by price ascending. In case of the same price, by id ascending.
     *
     * @param name Name to be filtered.
     * @return List
     */
    public List<Product> getProducts(String name) {
        if (name != null) {
            return products.stream()
                    .filter(p -> p.getName().contains(name))
                    .sorted(Comparator.comparing(Product::getPrice).thenComparing(Product::getId)).toList();
        } else {
            return null;
        }

    }

    /**
     * Get total price of all the products.
     *
     * @return Total price.
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * Check if stock is full.
     *
     * @return boolean
     */
    public boolean isFull() {
        return numOfProducts == maxCapacity;
    }
}
