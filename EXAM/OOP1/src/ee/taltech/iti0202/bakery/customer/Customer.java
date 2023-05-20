package ee.taltech.iti0202.bakery.customer;

import ee.taltech.iti0202.bakery.order.Order;
import ee.taltech.iti0202.bakery.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private int age;
    private Double bankAccount;
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private boolean confirmedOrders = false;

    public Customer(String name, int age, Double bankAccount) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (age < 0) {
            throw new IllegalArgumentException("Age must be a positive number.");
        }

        if (bankAccount < 0) {
            throw new IllegalArgumentException("Bank account must be a positive number.");
        }
        this.name = name;
        this.age = age;
        this.bankAccount = bankAccount;
    }

    public void addBoughtProduct(Product product) {
        products.add(product);
    }

    public String getName() {
        return name;
    }

    public boolean isAnotherMoney(double price) {
        return getBankAccount() >= price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Double bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isConfirmedOrders() {
        return confirmedOrders;
    }

    public void setConfirmedOrders(boolean confirmedOrders) {
        this.confirmedOrders = confirmedOrders;
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
