package ee.taltech.iti0202.bakery.baker;

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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BakerTest {

    @Test
    protected void addBakerToBakeryBasicTest() throws BakerAlreadyInTheBakeryException,
            BakerAlreadyContainsInAnotherBakeryException, BakerLimitException {
        Baker baker = new BakerBuilder()
                .setName("Danila")
                .setAge(12)
                .setBankAccount(123.4)
                .setTypes(List.of(Product.Types.BUN))
                .createBaker();
        BigBakery bakery2 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(13000.82)
                .createBigBakery();
        bakery2.addBaker(baker);
        assertEquals(bakery2.getBakers().size(), 1);
    }

    @Test
    protected void addBakerToBakerySecondTimeTest() throws BakerAlreadyInTheBakeryException,
            BakerAlreadyContainsInAnotherBakeryException, BakerLimitException {
        Baker baker = new BakerBuilder()
                .setName("Danila")
                .setAge(12)
                .setBankAccount(123.4)
                .setTypes(List.of(Product.Types.BUN))
                .createBaker();
        BigBakery bakery2 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(13000.82)
                .createBigBakery();
        bakery2.addBaker(baker);
        try {
            bakery2.addBaker(baker);
        } catch (BakerAlreadyInTheBakeryException ex) {
            Assertions.assertEquals("Baker already in the bakery!", ex.getMessage());
        }
    }

    @Test
    protected void addBakerToAnotherBakeryTest() throws BakerAlreadyInTheBakeryException,
            BakerAlreadyContainsInAnotherBakeryException, BakerLimitException {
        Baker baker = new BakerBuilder()
                .setName("Danila")
                .setAge(12)
                .setBankAccount(123.4)
                .setTypes(List.of(Product.Types.BUN))
                .createBaker();
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(13000.82)
                .createSmallBakery();
        BigBakery bakery2 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(13000.82)
                .createBigBakery();
        bakery2.addBaker(baker);
        try {
            bakery1.addBaker(baker);
        } catch (BakerAlreadyContainsInAnotherBakeryException | BakerLimitException ex) {
            Assertions.assertEquals("Baker already in another bakery!", ex.getMessage());
        }
    }

    @Test
    protected void checkBakersMoneyAfterBuyingTest() throws CanNotAddProductToTheBakeryException,
            ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException,
            BakerAlreadyInTheBakeryException, BakerAlreadyContainsInAnotherBakeryException, BakerLimitException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(10.0)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.BREAD)
                .setName("Bread")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
                .setName("Cake")
                .setKilocalories(643.01)
                .setPrice(10.0)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.00)
                .createBigBakery();
        Baker baker = new BakerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(23112.23)
                .setTypes(List.of(Product.Types.BUN))
                .createBaker();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        bakery1.addBaker(baker);
        double moneyBefore = bakery1.getBankAccount();
        double discount = product1.getPrice() / 2;
        bakery1.buyProductsByType(baker, Product.Types.PIE);
        assertEquals(bakery1.getBankAccount(), moneyBefore + discount);
    }

    @Test
    protected void checkBakersMoneyAfterBuyingWhenHeNotInTheBakeryTest() throws CanNotAddProductToTheBakeryException,
            ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException,
            BakerAlreadyInTheBakeryException, BakerAlreadyContainsInAnotherBakeryException, BakerLimitException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(10.0)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.BREAD)
                .setName("Bread")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
                .setName("Cake")
                .setKilocalories(643.01)
                .setPrice(10.0)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.00)
                .createBigBakery();
        SmallBakery bakery2 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(1000.00)
                .createSmallBakery();
        Baker baker = new BakerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(23112.23)
                .setTypes(List.of(Product.Types.BUN))
                .createBaker();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        bakery2.addBaker(baker);
        double moneyBefore = bakery1.getBankAccount();
        double discount = product1.getPrice();
        bakery1.buyProductsByType(baker, Product.Types.PIE);
        assertEquals(bakery1.getBankAccount(), moneyBefore + discount);
    }

    @Test
    protected void addBakerToTheSmallBakeryMoreThanAllowedTest() throws BakerAlreadyInTheBakeryException,
            BakerAlreadyContainsInAnotherBakeryException, BakerLimitException {
        Baker baker = new BakerBuilder()
                .setName("Danila")
                .setAge(12)
                .setBankAccount(123.4)
                .setTypes(List.of(Product.Types.BUN))
                .createBaker();
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(13000.82)
                .createSmallBakery();
        BigBakery bakery2 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(13000.82)
                .createBigBakery();
        for (int i = 0; i < 5; i++) {
            bakery1.addBaker(new BakerBuilder()
                    .setName("Danila")
                    .setAge(12)
                    .setBankAccount(123.4)
                    .setTypes(List.of(Product.Types.BUN))
                    .createBaker());
        }
        try {
            bakery1.addBaker(baker);
        } catch (BakerLimitException ex) {
            Assertions.assertEquals("We can not add more than 5 bakers!", ex.getMessage());
        }
    }

    @Test
    protected void addBakerToTheBigBakeryALotOfTimesTest() throws BakerAlreadyInTheBakeryException,
            BakerAlreadyContainsInAnotherBakeryException, BakerLimitException {
        Baker baker = new BakerBuilder()
                .setName("Danila")
                .setAge(12)
                .setBankAccount(123.4)
                .setTypes(List.of(Product.Types.BUN))
                .createBaker();
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(13000.82)
                .createSmallBakery();
        BigBakery bakery2 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(13000.82)
                .createBigBakery();
        for (int i = 0; i < 12; i++) {
            bakery2.addBaker(new BakerBuilder()
                    .setName("Danila")
                    .setAge(12)
                    .setBankAccount(123.4)
                    .setTypes(List.of(Product.Types.BUN))
                    .createBaker());
        }
        bakery2.addBaker(baker);
        assertEquals(bakery2.getBakers().size(), 13);
    }
}