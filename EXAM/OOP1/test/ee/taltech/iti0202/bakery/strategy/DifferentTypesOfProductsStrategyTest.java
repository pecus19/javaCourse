package ee.taltech.iti0202.bakery.strategy;

import ee.taltech.iti0202.bakery.BigBakery;
import ee.taltech.iti0202.bakery.builder.BigBakeryBuilder;
import ee.taltech.iti0202.bakery.builder.CustomerBuilder;
import ee.taltech.iti0202.bakery.builder.ProductBuilder;
import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.DoNotHaveEnoughMoneyToBuyException;
import ee.taltech.iti0202.bakery.exceptions.CanNotAddProductToTheBakeryException;
import ee.taltech.iti0202.bakery.exceptions.SearchProductsNotFoundException;
import ee.taltech.iti0202.bakery.product.Product;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

import java.util.List;

class DifferentTypesOfProductsStrategyTest {

    @Test
    protected void chooseTwoProductsOfThreeTest() throws CanNotAddProductToTheBakeryException,
            SearchProductsNotFoundException,
            DoNotHaveEnoughMoneyToBuyException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(999999.2)
                .createCustomer();
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.Types.BUN)
                .setName("Bun1")
                .setKilocalories(50.0)
                .setPrice(1.0)
                .createProduct());
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.Types.BUN)
                .setName("Bun2")
                .setKilocalories(50.0)
                .setPrice(1.0)
                .createProduct());
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
                .setName("Cake")
                .setKilocalories(25.0)
                .setPrice(1.0)
                .createProduct());
        DifferentTypesOfProductsStrategy strategy = new
                DifferentTypesOfProductsStrategy(5.0, 125.0, customer1);
        List<Product> products = strategy.useStrategy(bakery1);
        Assertions.assertEquals(products.size(), 2);
        Assertions.assertEquals(products.get(0).getName(), "Bun1");
        Assertions.assertEquals(products.get(1).getName(), "Cake");
    }

    @Test
    protected void chooseOneTypeFromAllProductsTest() throws CanNotAddProductToTheBakeryException,
            DoNotHaveEnoughMoneyToBuyException,
            SearchProductsNotFoundException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(999999.2)
                .createCustomer();
        for (int i = 0; i < 2; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.COOKIE)
                    .setName("Cookie")
                    .setKilocalories(50.0)
                    .setPrice(1.0)
                    .createProduct());
        }
        for (int i = 0; i < 2; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.BUN)
                    .setName("Bun")
                    .setKilocalories(50.0)
                    .setPrice(1.0)
                    .createProduct());
        }
        for (int i = 0; i < 2; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.CAKE)
                    .setName("Cake")
                    .setKilocalories(25.0)
                    .setPrice(1.0)
                    .createProduct());
        }
        for (int i = 0; i < 2; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.BREAD)
                    .setName("Bread")
                    .setKilocalories(25.0)
                    .setPrice(1.0)
                    .createProduct());
        }
        for (int i = 0; i < 2; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.PIE)
                    .setName("Pie")
                    .setKilocalories(25.0)
                    .setPrice(1.0)
                    .createProduct());
        }
        DifferentTypesOfProductsStrategy strategy = new
                DifferentTypesOfProductsStrategy(75.0, 535.0, customer1);
        List<Product> products = strategy.useStrategy(bakery1);
        Assertions.assertEquals(products.size(), 5);
        Assertions.assertEquals(products.get(0).getName(), "Cookie");
        Assertions.assertEquals(products.get(1).getName(), "Bun");
        Assertions.assertEquals(products.get(2).getName(), "Cake");
        Assertions.assertEquals(products.get(3).getName(), "Bread");
        Assertions.assertEquals(products.get(4).getName(), "Pie");
    }

    @Test
    protected void chooseProductWithLessPressTest() throws CanNotAddProductToTheBakeryException,
            DoNotHaveEnoughMoneyToBuyException,
            SearchProductsNotFoundException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(999999.2)
                .createCustomer();
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.Types.BUN)
                .setName("Bun1")
                .setKilocalories(50.0)
                .setPrice(1.0)
                .createProduct());
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.Types.BUN)
                .setName("Bun2")
                .setKilocalories(50.0)
                .setPrice(1.5)
                .createProduct());
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
                .setName("Cake")
                .setKilocalories(25.0)
                .setPrice(1.5)
                .createProduct());
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
                .setName("Cake2")
                .setKilocalories(25.0)
                .setPrice(1.0)
                .createProduct());
        DifferentTypesOfProductsStrategy strategy = new
                DifferentTypesOfProductsStrategy(5.0, 125.0, customer1);
        List<Product> products = strategy.useStrategy(bakery1);
        Assertions.assertEquals(products.size(), 2);
        Assertions.assertEquals(products.get(0).getName(), "Bun1");
        Assertions.assertEquals(products.get(1).getName(), "Cake2");
    }

    @Test
    protected void lessKilocaloriesStrategyDontHaveEnoughMoneyToBuyTest()
            throws CanNotAddProductToTheBakeryException,
            SearchProductsNotFoundException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(1.2)
                .createCustomer();
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.Types.BUN)
                .setName("Bun")
                .setKilocalories(50.0)
                .setPrice(1.0)
                .createProduct());
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
                .setName("Cake")
                .setKilocalories(50.0)
                .setPrice(1.01)
                .createProduct());
        DifferentTypesOfProductsStrategy strategy
                = new DifferentTypesOfProductsStrategy(2.0, 50.0, customer1);
        try {
            strategy.useStrategy(bakery1);
        } catch (DoNotHaveEnoughMoneyToBuyException ex) {
            Assertions.assertEquals("Don't have enough money to buy this product!", ex.getMessage());
        }
    }
}
