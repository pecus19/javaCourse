package ee.taltech.iti0202.bakery.builder;

import ee.taltech.iti0202.bakery.SmallBakery;

public class SmallBakeryBuilder {
    private String name;
    private Double bankAccount;

    public SmallBakeryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SmallBakeryBuilder setBankAccount(Double bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public SmallBakery createSmallBakery() {
        return new SmallBakery(name, bankAccount);
    }
}