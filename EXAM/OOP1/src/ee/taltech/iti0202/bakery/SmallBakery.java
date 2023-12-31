package ee.taltech.iti0202.bakery;

import ee.taltech.iti0202.bakery.baker.Baker;
import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.BakerAlreadyContainsInAnotherBakeryException;
import ee.taltech.iti0202.bakery.exceptions.BakerAlreadyInTheBakeryException;
import ee.taltech.iti0202.bakery.exceptions.BakerLimitException;
import ee.taltech.iti0202.bakery.exceptions.CanNotAddProductToTheBakeryException;
import ee.taltech.iti0202.bakery.exceptions.DoNotHaveEnoughMoneyToBuyException;
import ee.taltech.iti0202.bakery.exceptions.ProductAlreadyContainsInTheBakeryException;
import ee.taltech.iti0202.bakery.exceptions.ProductDoesNotContainsInBakeryException;
import ee.taltech.iti0202.bakery.exceptions.ProductLimitExceededException;
import ee.taltech.iti0202.bakery.exceptions.SearchProductsNotFoundException;
import ee.taltech.iti0202.bakery.exceptions.SmallBakeryCanSellOnlyProductsWithOneTypeException;
import ee.taltech.iti0202.bakery.order.Order;
import ee.taltech.iti0202.bakery.product.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SmallBakery {
    protected String name;
    protected Double bankAccount;
    protected List<Product> products = new ArrayList<>();
    protected List<Baker> bakers = new ArrayList<>();
    protected List<Order> orders = new ArrayList<>();
    protected int productLimit = 0;
    protected int bakersLimit = 0;
    protected Logger logger = Logger.getLogger(SmallBakery.class.getName());
    protected Map<Product, Integer> productRating = new HashMap<>();
    private static final int TEN = 10;
    private static final int FIVE = 5;
    private static final double ZEROONE = 0.1;

    public SmallBakery(String name, Double bankAccount) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (bankAccount < 0) {
            throw new IllegalArgumentException("Bank account must be a positive number.");
        }
        this.name = name;
        this.bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public void addBaker(Baker baker) throws BakerAlreadyInTheBakeryException,
            BakerAlreadyContainsInAnotherBakeryException, BakerLimitException {
        if (bakers.contains(baker)) {
            throw new BakerAlreadyInTheBakeryException();
        }
        if (!bakersLimit()) {
            throw new BakerLimitException();
        }
        if (baker.isWorks()) {
            throw new BakerAlreadyContainsInAnotherBakeryException();
        }

        bakers.add(baker);
        bakersLimit++;
        baker.setWorks(true);
        logger.info(baker.getName() + " has been added to the bakery with name: " + getName());

    }

    public Double getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Double bankAccount) {
        this.bankAccount = bankAccount;
    }

    private void transaction(Customer customer, Product product) {
        if (bakers.contains(customer)) {
            setBankAccount(getBankAccount() + (product.getPrice() / 2));
            customer.setBankAccount(customer.getBankAccount() - (product.getPrice() / 2));
        } else {
            setBankAccount(getBankAccount() + product.getPrice());
            customer.setBankAccount(customer.getBankAccount() - product.getPrice());
        }
    }

    private void ratingCalculation(Product product) {
        boolean check = true;
        for (Map.Entry<Product, Integer> entry : productRating.entrySet()) {
            if (entry.getKey().getName().equals(product.getName())
                    && entry.getKey().getKilocalories().equals(product.getKilocalories())
                    && entry.getKey().getPrice() == product.getPrice()) {
                productRating.put(entry.getKey(), productRating.get(entry.getKey()) + 1);
                check = false;
            }
        }
        if (check) {
            productRating.put(product, 1);
        }
    }

    public List<Product> getProductRating() {
        for (Map.Entry<Product, Integer> entry : productRating.entrySet()) {
            double price = entry.getKey().getPrice();
            for (Map.Entry<Product, Integer> entry1 : productRating.entrySet()) {
                if (entry.getKey().getName().equals(entry1.getKey().getName())
                        && entry.getKey().getId() != entry1.getKey().getId()) {
                    price += entry1.getKey().getPrice();
                }
            }
            price /= entry.getKey().getKilocalories();
            entry.getKey().setRatingMultiplier(Math.floor(price * TEN) / TEN);
        }
        List<Map.Entry<Product, Integer>> entries = new ArrayList<>(productRating.entrySet());
        entries.sort(Map.Entry.<Product, Integer>comparingByValue(Collections.reverseOrder())
                .thenComparing(Comparator.comparing((Map.Entry<Product, Integer> entry)
                                -> entry.getKey().getPrice() + entry.getKey().getKilocalories()).reversed()
                        .thenComparing(entry -> entry.getKey().getRatingMultiplier()).reversed()));
        return entries.stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Product buyProductsByType(Customer customer, Product.Types product)
            throws DoNotHaveEnoughMoneyToBuyException,
            ProductDoesNotContainsInBakeryException {
        List<Product> productsByType = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getTypes().equals(product)) {
                productsByType.add((products.get(i)));
            }
        }
        if (productsByType.size() > 0) {
            if (!customer.isAnotherMoney(productsByType.get(0).getPrice())) {
                throw new DoNotHaveEnoughMoneyToBuyException();
            } else {
                transaction(customer, productsByType.get(0));
                removeProduct(productsByType.get(0));
                customer.addBoughtProduct(productsByType.get(0));
                ratingCalculation(productsByType.get(0));
                return productsByType.get(0);
            }
        }
        throw new ProductDoesNotContainsInBakeryException();
    }

    public boolean checkTypes(Product product) {
        boolean checker = false;
        for (int i = 0; i < getBakers().size(); i++) {
            if (getBakers().get(i).getTypes().contains(product.getTypes())) {
                checker = true;
            }
        }
        return checker;
    }

    public Product buyProducts(Customer customer, Product product) throws DoNotHaveEnoughMoneyToBuyException,
            ProductDoesNotContainsInBakeryException {
        if (products.contains(product)) {
            if (!customer.isAnotherMoney(product.getPrice())) {
                throw new DoNotHaveEnoughMoneyToBuyException();
            } else {
                transaction(customer, product);
                removeProduct(product);
                ratingCalculation(product);
                return product;
            }
        }
        throw new ProductDoesNotContainsInBakeryException();
    }

    public void takeOrder(Order order) {
        List<Optional<Product>> orderList = new ArrayList<>();
        for (int i = 0; i < order.getProducts().size(); i++) {
            try {
                orderList.add(findProductById(order.getProducts().get(i).getId()));
            } catch (ProductDoesNotContainsInBakeryException ex) {
                logger.info(order.getProducts().get(i).getName() + " did not find in  the bakery with name: "
                        + getName());
            }
        }
        if (orderList.size() == order.getProducts().size()) {
            order.setApproved(true);
        }
    }

    public Optional<Product> findProductById(int id) throws ProductDoesNotContainsInBakeryException {
        Optional<Product> output = null;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                output = Optional.of(products.get(i));
            }
        }

        if (Optional.ofNullable(output).isEmpty()) {
            throw new ProductDoesNotContainsInBakeryException();
        }

        return output;
    }

    public List<Product> findProduct(double price) throws SearchProductsNotFoundException {
        List<Product> output = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getPrice() == price) {
                output.add(products.get(i));
            }
        }
        if (output.size() == 0) {
            throw new SearchProductsNotFoundException();
        }
        return output;
    }

    public List<Product> findProduct(String name) throws SearchProductsNotFoundException {
        List<Product> output = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (Objects.equals(products.get(i).getName(), name)) {
                output.add(products.get(i));
            }
        }
        if (output.size() == 0) {
            throw new SearchProductsNotFoundException();
        }
        return output;
    }

    public void removeProduct(Product product) throws ProductDoesNotContainsInBakeryException {
        if (products.contains(product)) {
            products.remove(product);
            product.setInTheBakery(false);
            productLimit--;
            logger.info(product.getName() + " has been removed from the bakery with name: " + getName());
        } else {
            throw new ProductDoesNotContainsInBakeryException();
        }
    }

    public boolean productsLimit() {
        return productLimit < TEN;
    }

    public boolean bakersLimit() {
        return bakersLimit < FIVE;
    }

    public void addProduct(Product product) throws CanNotAddProductToTheBakeryException, ProductLimitExceededException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        if (!checkTypes(product)) {
            throw new CanNotAddProductToTheBakeryException();
        }
        if (products.contains(product)) {
            throw new ProductAlreadyContainsInTheBakeryException();
        }
        if (product.isInTheBakery()) {
            throw new CanNotAddProductToTheBakeryException();
        }
        if (!productsLimit()) {
            throw new ProductLimitExceededException();
        }
        if (products.size() == 0) {
            products.add(product);
            product.setInTheBakery(true);
            productLimit++;
            logger.info(product.getName() + " has been added to the bakery with name: " + getName());
        } else if (products.size() > 0) {
            if (!product.getTypes().equals(products.get(0).getTypes())) {
                throw new SmallBakeryCanSellOnlyProductsWithOneTypeException();
            } else {
                products.add(product);
                product.setInTheBakery(true);
                productLimit++;
                logger.info(product.getName() + " has been added to the bakery with name: " + getName());
            }
        }
    }

    public void increasingPrices() {
        int loopSize = Math.min(3, getProductRating().size());

        for (int i = 0; i < loopSize; i++) {
            double newPriceCalculation = getProductRating().get(i).getPrice()
                    + (productRating.get(getProductRating().get(i)) * ZEROONE / 100);
            for (int j = 0; j < getProducts().size(); j++) {
                Product currentProduct = getProducts().get(j);
                Product ratingProduct = getProductRating().get(i);
                if (currentProduct.getName().equals(ratingProduct.getName())
                        && currentProduct.getPrice() == ratingProduct.getPrice()
                        && Objects.equals(currentProduct.getKilocalories(), ratingProduct.getKilocalories())) {
                    currentProduct.setPrice(newPriceCalculation);
                }
            }
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        if (!orders.contains(order)) {
            orders.add(order);
        }
    }

    public List<Baker> getBakers() {
        return bakers;
    }
}
