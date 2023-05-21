package ee.taltech.iti0202.bakery.product;

import ee.taltech.iti0202.bakery.builder.ProductBuilder;
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
}
