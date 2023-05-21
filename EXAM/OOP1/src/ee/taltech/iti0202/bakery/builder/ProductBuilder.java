package ee.taltech.iti0202.bakery.builder;

import ee.taltech.iti0202.bakery.product.Product;

public class ProductBuilder {
    private String name;
    private double price;
    private Double kilocalories;
    private Product.Types bakeryTypes;

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder setKilocalories(Double kilocalories) {
        this.kilocalories = kilocalories;
        return this;
    }

    public ProductBuilder setBakeryTypes(Product.Types bakeryTypes) {
        this.bakeryTypes = bakeryTypes;
        return this;
    }

    public Product createProduct() {
        return new Product(name, price, kilocalories, bakeryTypes);
    }
}
