package ee.taltech.iti0202.bakery.product;

import ee.taltech.iti0202.bakery.builder.ProductBuilder;
import ee.taltech.iti0202.bakery.exceptions.UnconfirmedOrdersException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ProductTest {
    @Test
    protected void checkProductIdTest() {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.BREAD)
                .setName("Bread")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(643.01)
                .setPrice(10.0)
                .createProduct();
        assertSame(product1.getId(), 0);
        assertSame(product2.getId(), 1);
        assertSame(product3.getId(), 2);
    }

    @Test
    protected void productNameIsNullTest() {
        try {
            new ProductBuilder()
                    .setBakeryTypes(Product.bakeryTypes.PIE)
                    .setName(null)
                    .setKilocalories(400.0)
                    .setPrice(1.2)
                    .createProduct();
        } catch (
                IllegalArgumentException ex) {
            Assertions.assertEquals("Name cannot be empty", ex.getMessage());
        }
    }

    @Test
    protected void productTypeIsNullTest() {
        try {
            new ProductBuilder()
                    .setBakeryTypes(null)
                    .setName("null")
                    .setKilocalories(400.0)
                    .setPrice(1.2)
                    .createProduct();
        } catch (
                IllegalArgumentException ex) {
            Assertions.assertEquals("You need to write a type of a product", ex.getMessage());
        }
    }
    @Test
    protected void productPriceIsWrongTest() {
        try {
            new ProductBuilder()
                    .setBakeryTypes(Product.bakeryTypes.PIE)
                    .setName("null")
                    .setKilocalories(400.0)
                    .setPrice(-1.3)
                    .createProduct();
        } catch (
                IllegalArgumentException ex) {
            Assertions.assertEquals("Price cannot be negative", ex.getMessage());
        }
    }
    @Test
    protected void productCaloriesIsWrongTest() {
        try {
            new ProductBuilder()
                    .setBakeryTypes(Product.bakeryTypes.PIE)
                    .setName("null")
                    .setKilocalories(-400.0)
                    .setPrice(1.3)
                    .createProduct();
        } catch (
                IllegalArgumentException ex) {
            Assertions.assertEquals("Calories cannot be negative", ex.getMessage());
        }
    }
}
