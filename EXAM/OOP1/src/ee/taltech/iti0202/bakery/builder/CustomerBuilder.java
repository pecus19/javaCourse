package ee.taltech.iti0202.bakery.builder;

import ee.taltech.iti0202.bakery.customer.Customer;

public class CustomerBuilder {
    private String name;
    private int age;
    private Double bankAccount;

    public CustomerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public CustomerBuilder setBankAccount(Double bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public Customer createCustomer() {
        return new Customer(name, age, bankAccount);
    }
}
