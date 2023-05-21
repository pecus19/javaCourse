package ee.taltech.iti0202.bakery.strategy;

import ee.taltech.iti0202.bakery.BigBakery;
import ee.taltech.iti0202.bakery.SmallBakery;
import ee.taltech.iti0202.bakery.builder.BigBakeryBuilder;
import ee.taltech.iti0202.bakery.builder.CustomerBuilder;
import ee.taltech.iti0202.bakery.builder.ProductBuilder;
import ee.taltech.iti0202.bakery.builder.SmallBakeryBuilder;
import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.*;
import ee.taltech.iti0202.bakery.product.Product;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LessKilocaloriesStrategyTest {

    @Test
    protected void lessKilocaloriesStrategyTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException, SearchProductsNotFoundException {
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
                    .setBakeryTypes(Product.bakeryTypes.COOKIE)
                    .setName("First")
                    .setKilocalories(100.0)
                    .setPrice(1.0)
                    .createProduct());
        }
        for (int i = 0; i < 2; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.bakeryTypes.BREAD)
                    .setName("Second")
                    .setKilocalories(50.0)
                    .setPrice(1.0)
                    .createProduct());
        }
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.BUN)
                .setName("Bun")
                .setKilocalories(50.0)
                .setPrice(1.0)
                .createProduct());
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(25.0)
                .setPrice(1.0)
                .createProduct());
        LessKilocaloriesStrategy strategy = new LessKilocaloriesStrategy(3.0, 125.0, customer1);
        List<Product> products = strategy.useStrategy(bakery1);
        System.out.println(products);
        assertEquals(products.get(0).getName(), "Cake");
        assertEquals(products.get(1).getName(), "Second");
        assertEquals(products.get(2).getName(), "Bun");
    }

    @Test
    protected void choseOneProductWithTheLeastCaloriesTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException, SearchProductsNotFoundException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(999999.2)
                .createCustomer();
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.BUN)
                .setName("Bun")
                .setKilocalories(50.0)
                .setPrice(1.0)
                .createProduct());
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(25.0)
                .setPrice(1.0)
                .createProduct());
        LessKilocaloriesStrategy strategy = new LessKilocaloriesStrategy(1.0, 50.0, customer1);
        List<Product> products = strategy.useStrategy(bakery1);
        assertEquals(products.size(), 1);
        assertEquals(products.get(0).getName(), "Cake");
    }

    @Test
    protected void lessKilocaloriesStrategybakeryDoesNotHaveProductsWithThisPriceOrCaloriesTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException, SearchProductsNotFoundException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(999999.2)
                .createCustomer();
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.BUN)
                .setName("Bun")
                .setKilocalories(50.0)
                .setPrice(1.0)
                .createProduct());
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(25.0)
                .setPrice(1.0)
                .createProduct());
        LessKilocaloriesStrategy strategy = new LessKilocaloriesStrategy(1.0, 20.0, customer1);
        try {
            strategy.useStrategy(bakery1);
        } catch (SearchProductsNotFoundException ex) {
            Assertions.assertEquals("Search products not found!", ex.getMessage());
        }
    }

    @Test
    protected void lessKilocaloriesStrategyNotCorrectPriceTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException, SearchProductsNotFoundException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(999999.2)
                .createCustomer();
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.BUN)
                .setName("Bun")
                .setKilocalories(50.0)
                .setPrice(1.0)
                .createProduct());
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(25.0)
                .setPrice(1.0)
                .createProduct());
        LessKilocaloriesStrategy strategy = new LessKilocaloriesStrategy(0.3, 50.0, customer1);
        try {
            strategy.useStrategy(bakery1);
        } catch (SearchProductsNotFoundException ex) {
            Assertions.assertEquals("Search products not found!", ex.getMessage());
        }
    }

    @Test
    protected void lessKilocaloriesStrategyCaloriesAreEqualsChooseWithBestPriceTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException, SearchProductsNotFoundException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(999999.2)
                .createCustomer();
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.BUN)
                .setName("Bun")
                .setKilocalories(50.0)
                .setPrice(1.0)
                .createProduct());
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(50.0)
                .setPrice(1.01)
                .createProduct());
        LessKilocaloriesStrategy strategy = new LessKilocaloriesStrategy(2.0, 50.0, customer1);
        List<Product> products = strategy.useStrategy(bakery1);
        assertEquals(products.get(0).getName(), "Bun");
        assertEquals(products.size(), 1);
    }

    @Test
    protected void lessKilocaloriesStrategyCaloriesAreEqualsChooseWithBestPriceSmallBakeryTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException, SearchProductsNotFoundException {
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(1000.34)
                .createSmallBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(999999.2)
                .createCustomer();
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake1")
                .setKilocalories(50.0)
                .setPrice(1.0)
                .createProduct());
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake2")
                .setKilocalories(50.0)
                .setPrice(1.01)
                .createProduct());
        LessKilocaloriesStrategy strategy = new LessKilocaloriesStrategy(2.0, 50.0, customer1);
        List<Product> products = strategy.useStrategy(bakery1);
        assertEquals(products.get(0).getName(), "Cake1");
        assertEquals(products.size(), 1);
    }

    @Test
    protected void lessKilocaloriesStrategyDontHaveEnoughMoneyToBuyTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException, SearchProductsNotFoundException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(1.2)
                .createCustomer();
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.BUN)
                .setName("Bun")
                .setKilocalories(50.0)
                .setPrice(1.0)
                .createProduct());
        bakery1.addProduct(new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(50.0)
                .setPrice(1.01)
                .createProduct());
        LessKilocaloriesStrategy strategy = new LessKilocaloriesStrategy(2.0, 50.0, customer1);
        try {
            strategy.useStrategy(bakery1);
        } catch (DoNotHaveEnoughMoneyToBuyException ex) {
            Assertions.assertEquals("Don't have enough money to buy this product!", ex.getMessage());
        }
    }
}