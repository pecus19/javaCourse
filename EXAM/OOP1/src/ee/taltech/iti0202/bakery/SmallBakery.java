package ee.taltech.iti0202.bakery;

import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.*;
import ee.taltech.iti0202.bakery.order.Order;
import ee.taltech.iti0202.bakery.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

public class SmallBakery {
    protected String name;
    protected Double bankAccount;
    protected List<Product> products = new ArrayList<>();
    protected List<Order> orders = new ArrayList<>();
    protected int productLimit = 0;
    protected Logger logger = Logger.getLogger(SmallBakery.class.getName());

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

    public void setName(String name) {
        this.name = name;
    }

    public Double getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Double bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void transaction(Customer customer, Product product) {
        setBankAccount(getBankAccount() + product.getPrice());
        customer.setBankAccount(customer.getBankAccount() - product.getPrice());
    }

    public Product buyProductsByType(Customer customer, Product.bakeryTypes product) throws DoNotHaveEnoughMoneyToBuyException,
            ProductDoesNotContainsInBakeryException {
        List<Product> productsByType = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getBakeryTypes().equals(product)) {
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
                return productsByType.get(0);
            }
        }
        throw new ProductDoesNotContainsInBakeryException();
    }

    public Product buyProducts(Customer customer, Product product) throws DoNotHaveEnoughMoneyToBuyException,
            ProductDoesNotContainsInBakeryException {
        if (products.contains(product)) {
            if (!customer.isAnotherMoney(product.getPrice())) {
                throw new DoNotHaveEnoughMoneyToBuyException();
            } else {
                transaction(customer, product);
                removeProduct(product);
                return product;
            }
        }
        throw new ProductDoesNotContainsInBakeryException();
    }

    public void takeOrder(Order order) throws ProductDoesNotContainsInBakeryException {
        List<Optional<Product>> orderList = new ArrayList<>();
        for (int i = 0; i < order.getProducts().size(); i++) {
//            findProductById(order.getProducts().get(i).getId());
            try {
                // Код, который может вызвать исключение
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
        return productLimit < 10;
    }

    public void addProduct(Product product) throws ProductDoesNotContainsInBakeryException,
            ProductAlreadyContainsInAnotherBakeryException, ProductLimitExceededException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        if (products.contains(product)) {
            throw new ProductAlreadyContainsInTheBakeryException();
        }
        if (product.isInTheBakery()) {
            throw new ProductAlreadyContainsInAnotherBakeryException();
        }
        if (!productsLimit()) {
            throw new ProductLimitExceededException();
        }
        if (products.size() == 0) {
            products.add(product);
            product.setInTheBakery(true);
            productLimit++;
            logger.info(product.getName() + " has been added to the bakery with name: " + getName());
        }
        if (products.size() > 0) {
            if (!product.getBakeryTypes().equals(products.get(0).getBakeryTypes())) {
                throw new SmallBakeryCanSellOnlyProductsWithOneTypeException();
            }
        }

        products.add(product);
        product.setInTheBakery(true);
        productLimit++;
        logger.info(product.getName() + " has been added to the bakery with name: " + getName());

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
}
