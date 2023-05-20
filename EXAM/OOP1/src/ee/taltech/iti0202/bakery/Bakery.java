package ee.taltech.iti0202.bakery;

import ee.taltech.iti0202.bakery.bank.BankAccount;
import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.DoNotHaveEnoughMoneyToBuyException;
import ee.taltech.iti0202.bakery.exceptions.ProductAlreadyContainsInAnotherBakeryException;
import ee.taltech.iti0202.bakery.exceptions.ProductAlreadyContainsInBakeryException;
import ee.taltech.iti0202.bakery.exceptions.SearchProductsNotFoundException;
import ee.taltech.iti0202.bakery.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

public class Bakery {
    private String name;
    private Double bankAccount;
    public List<Product> products = new ArrayList<>();
    protected Logger logger = Logger.getLogger(Bakery.class.getName());

    public Bakery(String name, Double bankAccount) {
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
        customer.setBankAccount(getBankAccount() - product.getPrice());
    }

    public void buyProducts(Customer customer, Product product) throws DoNotHaveEnoughMoneyToBuyException,
            ProductAlreadyContainsInBakeryException {
        if (!customer.isAnotherMoney(product.getPrice())) {
            throw new DoNotHaveEnoughMoneyToBuyException();
        } else {
            transaction(customer, product);
            removeProduct(product);

        }

    }

    public Optional<Product> findProductById(int id) throws SearchProductsNotFoundException {
        Optional<Product> output = null;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                output = Optional.of(products.get(i));
            }
        }
        assert output != null;
        if (output.isPresent()) {
            throw new SearchProductsNotFoundException();
        }

        return output;
    }

    public List<Product> findProduct(int price) throws SearchProductsNotFoundException {
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

    public void removeProduct(Product product) throws ProductAlreadyContainsInBakeryException {
        if (products.contains(product)) {
            products.remove(product);
            product.setInTheBakery(false);
            logger.info(product.getName() + " has been removed from the bakery with name: " + getName());
        } else {
            throw new ProductAlreadyContainsInBakeryException();
        }
    }

    public void addProduct(Product product) throws ProductAlreadyContainsInBakeryException,
            ProductAlreadyContainsInAnotherBakeryException {
        if (product.isInTheBakery()) {
            throw new ProductAlreadyContainsInAnotherBakeryException();
        }
        if (!products.contains(product)) {
            products.add(product);
            product.setInTheBakery(true);
            logger.info(product.getName() + " has been added to the bakery with name: " + getName());
        } else {
            throw new ProductAlreadyContainsInBakeryException();
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}
