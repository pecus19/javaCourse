package ee.taltech.iti0202.bakery.builder;

import ee.taltech.iti0202.bakery.BigBakery;

public class BigBakeryBuilder {
    private String name;
    private Double bankAccount;

    public BigBakeryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BigBakeryBuilder setBankAccount(Double bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public BigBakery createBigBakery() {
        return new BigBakery(name, bankAccount);
    }
}