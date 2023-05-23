package ee.taltech.iti0202.bakery.strategy;

import ee.taltech.iti0202.bakery.BigBakery;
import ee.taltech.iti0202.bakery.baker.Baker;
import ee.taltech.iti0202.bakery.baker.BakerBuilder;
import ee.taltech.iti0202.bakery.builder.BigBakeryBuilder;
import ee.taltech.iti0202.bakery.builder.CustomerBuilder;
import ee.taltech.iti0202.bakery.builder.ProductBuilder;
import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.*;
import ee.taltech.iti0202.bakery.product.Product;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

class LessPriceStrategyTest {

    @Test
    protected void lessPriceStrategyTest() throws CanNotAddProductToTheBakeryException,
            DoNotHaveEnoughMoneyToBuyException,
            SearchProductsNotFoundException, BakerAlreadyInTheBakeryException,
            BakerAlreadyContainsInAnotherBakeryException, BakerLimitException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(999999.2)
                .createCustomer();
        Baker baker = new BakerBuilder()
                .setName("Danila")
                .setAge(12)
                .setBankAccount(123.4)
                .setTypes(List.of(Product.Types.BUN, Product.Types.COOKIE, Product.Types.BREAD))
                .createBaker();
        bakery1.addBaker(baker);
        for (int i = 0; i < 2; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.COOKIE)
                    .setName("First")
                    .setKilocalories(100.0)
                    .setPrice(1.0)
                    .createProduct());
        }
        for (int i = 0; i < 30; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.BREAD)
                    .setName("Second")
                    .setKilocalories(50.0)
                    .setPrice(0.2)
                    .createProduct());
        }
        LessPriceStrategy strategy = new LessPriceStrategy(1.2, 125.0, customer1);
        List<Product> products = strategy.useStrategy(bakery1);
        double c = 0;
        for (int i = 0; i < products.size(); i++) {
            c += products.get(i).getPrice();
        }
        System.out.println(c);
        assertEquals(products.size(), 6);
    }
    @Test
    protected void lessPriceStrategyTakeProductWithLowerPriceNotEnoughToTakeOneMoreTest()
            throws CanNotAddProductToTheBakeryException,
            DoNotHaveEnoughMoneyToBuyException,
            SearchProductsNotFoundException, BakerAlreadyInTheBakeryException,
            BakerAlreadyContainsInAnotherBakeryException, BakerLimitException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(999999.2)
                .createCustomer();
        Baker baker = new BakerBuilder()
                .setName("Danila")
                .setAge(12)
                .setBankAccount(123.4)
                .setTypes(List.of(Product.Types.BUN, Product.Types.COOKIE, Product.Types.BREAD))
                .createBaker();
        bakery1.addBaker(baker);
        for (int i = 0; i < 2; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.COOKIE)
                    .setName("First")
                    .setKilocalories(100.0)
                    .setPrice(0.25)
                    .createProduct());
        }
        for (int i = 0; i < 19; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.BREAD)
                    .setName("PROD")
                    .setKilocalories(50.0)
                    .setPrice(0.1)
                    .createProduct());
        }
        for (int i = 0; i < 4; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.BREAD)
                    .setName("Second")
                    .setKilocalories(50.0)
                    .setPrice(0.2)
                    .createProduct());
        }
        LessPriceStrategy strategy = new LessPriceStrategy(2.0, 125.0, customer1);
        List<Product> products = strategy.useStrategy(bakery1);
        assertEquals(products.size(), 19);
    }
    @Test
    protected void lessPriceStrategyPriceTest() throws CanNotAddProductToTheBakeryException,
            DoNotHaveEnoughMoneyToBuyException,
            SearchProductsNotFoundException, BakerAlreadyInTheBakeryException,
            BakerAlreadyContainsInAnotherBakeryException, BakerLimitException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(999999.2)
                .createCustomer();
        Baker baker = new BakerBuilder()
                .setName("Danila")
                .setAge(12)
                .setBankAccount(123.4)
                .setTypes(List.of(Product.Types.BUN, Product.Types.COOKIE, Product.Types.BREAD))
                .createBaker();
        bakery1.addBaker(baker);
        for (int i = 0; i < 2; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.COOKIE)
                    .setName("First")
                    .setKilocalories(100.0)
                    .setPrice(0.25)
                    .createProduct());
        }
        for (int i = 0; i < 2; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.BREAD)
                    .setName("PROD")
                    .setKilocalories(50.0)
                    .setPrice(0.1)
                    .createProduct());
        }
        for (int i = 0; i < 10; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.BREAD)
                    .setName("Second")
                    .setKilocalories(50.0)
                    .setPrice(0.2)
                    .createProduct());
        }
        LessPriceStrategy strategy = new LessPriceStrategy(0.2, 125.0, customer1);
        List<Product> products = strategy.useStrategy(bakery1);
        assertEquals(products.size(), 2);
    }
}