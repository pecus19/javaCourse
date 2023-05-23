package ee.taltech.iti0202.bakery.baker;

import ee.taltech.iti0202.bakery.product.Product;

import java.util.List;

public class BakerBuilder {
    private String name;
    private int age;
    private Double bankAccount;
    private List<Product.Types> types;

    public BakerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BakerBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public BakerBuilder setBankAccount(Double bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public BakerBuilder setTypes(List<Product.Types> types) {
        this.types = types;
        return this;
    }

    public Baker createBaker() {
        return new Baker(name, age, bankAccount, types);
    }
}
