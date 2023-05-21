package ee.taltech.iti0202.bakery.strategy;

import ee.taltech.iti0202.bakery.SmallBakery;
import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.DoNotHaveEnoughMoneyToBuyException;
import ee.taltech.iti0202.bakery.exceptions.SearchProductsNotFoundException;
import ee.taltech.iti0202.bakery.product.Product;

import java.util.List;

public abstract class Strategy {
    private Double price;
    private Double kilocalories;
    private Customer customer;

    public Strategy(Double price, Double kilocalories, Customer customer) {
        this.price = price;
        this.kilocalories = kilocalories;
        this.customer = customer;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getKilocalories() {
        return kilocalories;
    }

    public void setKilocalories(Double kilocalories) {
        this.kilocalories = kilocalories;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public abstract List<Product> useStrategy(SmallBakery bakery)
            throws SearchProductsNotFoundException, DoNotHaveEnoughMoneyToBuyException;
}
