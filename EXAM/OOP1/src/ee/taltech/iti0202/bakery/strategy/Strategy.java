package ee.taltech.iti0202.bakery.strategy;

import ee.taltech.iti0202.bakery.customer.Customer;

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
}
