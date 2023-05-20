package ee.taltech.iti0202.bakery;

import ee.taltech.iti0202.bakery.builder.BigBakeryBuilder;
import ee.taltech.iti0202.bakery.builder.CustomerBuilder;
import ee.taltech.iti0202.bakery.builder.ProductBuilder;
import ee.taltech.iti0202.bakery.builder.SmallBakeryBuilder;
import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.*;
import ee.taltech.iti0202.bakery.order.Order;
import ee.taltech.iti0202.bakery.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


public class BakeryTest {
    @Test
    protected void addProductToBakeryBasicTest() throws ProductAlreadyContainsInAnotherBakeryException, ProductLimitExceededException, ProductDoesNotContainsInBakeryException, ProductAlreadyContainsInTheBakeryException, SearchProductsNotFoundException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
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
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(1000.34)
                .createSmallBakery();
        BigBakery bakery2 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(13000.82)
                .createBigBakery();
        bakery2.addProduct(product1);
        bakery2.addProduct(product2);
        bakery2.addProduct(product3);
        assertEquals(bakery2.getProducts().size(), 3);
    }
    @Test
    protected void basicRemoveFromBakeryTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException, UnconfirmedOrdersException, SearchProductsNotFoundException, DoNotHaveEnoughMoneyToBuyException, OrderCanNotBeDoneException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.17)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(23.2)
                .setPrice(6.65)
                .createProduct();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(23112.23)
                .createCustomer();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        bakery1.addProduct(product2);
        bakery1.addProduct(product1);
        bakery1.addProduct(product3);
        bakery1.removeProduct(product1);
        assertEquals(bakery1.getProducts().size(), 2);
    }
    @Test
    protected void basicRemoveFromBakeryNotContainsInTheBakeryTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException, UnconfirmedOrdersException, SearchProductsNotFoundException, DoNotHaveEnoughMoneyToBuyException, OrderCanNotBeDoneException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.17)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(23.2)
                .setPrice(6.65)
                .createProduct();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(23112.23)
                .createCustomer();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        try {
            bakery1.removeProduct(product1);
        } catch (ProductDoesNotContainsInBakeryException ex) {
            Assertions.assertEquals("Product does not contains in the bakery!", ex.getMessage());
        }
    }

    @Test
    protected void addProductToBakerySecondTimeTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, ProductAlreadyContainsInTheBakeryException, SearchProductsNotFoundException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
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
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(1000.34)
                .createSmallBakery();
        BigBakery bakery2 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(13000.82)
                .createBigBakery();
        bakery1.addProduct(product1);
        bakery2.addProduct(product2);
        bakery2.addProduct(product3);
        try {
            bakery1.addProduct(product1);
        } catch (ProductAlreadyContainsInTheBakeryException ex) {
            Assertions.assertEquals("Product already contains in the bakery!", ex.getMessage());
        }
    }

    @Test
    protected void addProductToBakeryWhenHeAlreadyInAnotherBakeryTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, ProductAlreadyContainsInTheBakeryException, SearchProductsNotFoundException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
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
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(1000.34)
                .createSmallBakery();
        BigBakery bakery2 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(13000.82)
                .createBigBakery();
        bakery1.addProduct(product1);
        bakery2.addProduct(product2);
        bakery2.addProduct(product3);
        try {
            bakery1.addProduct(product2);
        } catch (ProductAlreadyContainsInAnotherBakeryException ex) {
            Assertions.assertEquals("Product already contains in another bakery!", ex.getMessage());
        }
    }

    @Test
    protected void removeProductFromBakeryBasicTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, ProductAlreadyContainsInTheBakeryException, SearchProductsNotFoundException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
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
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(1000.34)
                .createSmallBakery();
        BigBakery bakery2 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(13000.82)
                .createBigBakery();
        bakery2.addProduct(product1);
        bakery1.addProduct(product2);
        bakery2.removeProduct(product1);
        assertEquals(bakery2.getProducts().size(), 0);
    }

    @Test
    protected void createASmallBakeryWIthWrongBankAccountTest() {
        try {
            new SmallBakeryBuilder()
                    .setName("Small Bakery")
                    .setBankAccount((double) -1)
                    .createSmallBakery();
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Bank account must be a positive number.", ex.getMessage());
        }
    }

    @Test
    protected void createASmallBakeryWIthWrongNameTest() {
        try {
            new SmallBakeryBuilder()
                    .setName(null)
                    .setBankAccount((double) -1)
                    .createSmallBakery();
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Name cannot be empty", ex.getMessage());
        }
    }

    @Test
    protected void createAGigBakeryWIthWrongBankAccountTest() {
        try {
            new BigBakeryBuilder()
                    .setName("Big Bakery")
                    .setBankAccount((double) -2332)
                    .createBigBakery();
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Bank account must be a positive number.", ex.getMessage());
        }
    }

    @Test
    protected void createAGigBakeryWIthWrongNameTest() {
        try {
            new BigBakeryBuilder()
                    .setName(null)
                    .setBankAccount((double) -1)
                    .createBigBakery();
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Name cannot be empty", ex.getMessage());
        }
    }

    @Test
    protected void checkMoneyAfterBuyingByTypeBakeryTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, ProductAlreadyContainsInTheBakeryException, DoNotHaveEnoughMoneyToBuyException, SearchProductsNotFoundException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
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
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(23112.23)
                .createCustomer();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        double moneyBefore = bakery1.getBankAccount();
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.PIE);
        assertEquals(bakery1.getBankAccount(), moneyBefore + product1.getPrice());
    }

    @Test
    protected void checkMoneyAfterBuyingALotOfProductsByTypeBakeryTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException {
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
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(23112.23)
                .createCustomer();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        double moneyBefore1 = bakery1.getBankAccount();
        double moneyForAllProducts = product1.getPrice() + product2.getPrice() + product3.getPrice();
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.PIE);
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.BREAD);
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.CAKE);
        assertEquals(bakery1.getBankAccount(), moneyBefore1 + moneyForAllProducts);
    }

    @Test
    protected void checkMoneyAfterBuyingByTypeCustomerTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException {
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
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(100.00)
                .createCustomer();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        double moneyBefore1 = customer1.getBankAccount();
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.PIE);
        assertEquals(customer1.getBankAccount(), moneyBefore1 - product1.getPrice());
    }

    @Test
    protected void checkMoneyAfterBuyingALotOfProductsByTypeCustomerTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException {
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
                .setPrice(10.3)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(100.00)
                .createCustomer();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        double moneyBefore1 = customer1.getBankAccount();
        double moneyForAllProducts = product1.getPrice() + product2.getPrice() + product3.getPrice();
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.PIE);
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.BREAD);
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.CAKE);
        assertEquals(customer1.getBankAccount(), moneyBefore1 - moneyForAllProducts);
    }

    @Test
    protected void canNotBuyProductsDueToMoneyTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException {
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
                .setPrice(10.3)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(0.2)
                .createCustomer();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        try {
            bakery1.buyProductsByType(customer1, Product.bakeryTypes.PIE);
        } catch (DoNotHaveEnoughMoneyToBuyException ex) {
            Assertions.assertEquals("Don't have enough money to buy this product!", ex.getMessage());
        }
    }

    @Test
    protected void canNotBuyProductsDueToMoneyAfterBuyingSomeProductsTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException {
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
                .setPrice(10.3)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(13.5)
                .createCustomer();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.BREAD);
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.CAKE);
        try {
            bakery1.buyProductsByType(customer1, Product.bakeryTypes.PIE);
        } catch (DoNotHaveEnoughMoneyToBuyException ex) {
            Assertions.assertEquals("Don't have enough money to buy this product!", ex.getMessage());
        }
    }

    @Test
    protected void checkProductsThatCustomerBoughtTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException {
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
                .setPrice(10.3)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(323.2)
                .createCustomer();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.BREAD);
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.CAKE);
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.PIE);
        assertEquals(customer1.getProducts().size(), 3);
    }

    @Test
    protected void checkProductsInTheBakeryAfterBuyingTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException {
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
                .setPrice(10.3)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(323.2)
                .createCustomer();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.BREAD);
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.CAKE);
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.PIE);
        assertEquals(bakery1.getProducts().size(), 0);
    }

    @Test
    protected void onlyOneCustomerCanBuyOneProductTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException {
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
                .setPrice(10.3)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(323.2)
                .createCustomer();
        Customer customer2 = new CustomerBuilder().setName("Ago")
                .setAge(34)
                .setBankAccount(123.3)
                .createCustomer();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        bakery1.buyProductsByType(customer1, Product.bakeryTypes.BREAD);
        try {
            bakery1.buyProductsByType(customer2, Product.bakeryTypes.BREAD);
        } catch (ProductDoesNotContainsInBakeryException ex) {
            Assertions.assertEquals("Product does not contains in the bakery!", ex.getMessage());
        }
    }

    @Test
    protected void findProductByIdTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, SearchProductsNotFoundException {
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
                .setPrice(10.3)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        Optional<Product> optional = Optional.ofNullable(product2);
        assertSame(bakery1.findProductById(1), optional);
    }

    @Test
    protected void findProductByNameTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, SearchProductsNotFoundException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(643.01)
                .setPrice(10.3)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        assertEquals(bakery1.findProduct("Pie"), List.of(product2, product1));
    }

    @Test
    protected void findProductByPriceTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, SearchProductsNotFoundException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(643.01)
                .setPrice(10.3)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        assertEquals(bakery1.findProduct(10.3), List.of(product3));
    }

    @Test
    protected void findProductByPriceWithWrongPriceTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(643.01)
                .setPrice(10.3)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        try {
            bakery1.findProduct(34);
        } catch (SearchProductsNotFoundException ex) {
            Assertions.assertEquals("Search products not found! Try to input another product..", ex.getMessage());
        }
    }

    @Test
    protected void findProductByPriceWithWrongNameTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(643.01)
                .setPrice(10.3)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        try {
            bakery1.findProduct("Cake1");
        } catch (SearchProductsNotFoundException ex) {
            Assertions.assertEquals("Search products not found! Try to input another product..", ex.getMessage());
        }
    }

    @Test
    protected void findProductByPriceWithWrongIdTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(643.01)
                .setPrice(10.3)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        try {
            bakery1.findProductById(4);
        } catch (ProductDoesNotContainsInBakeryException ex) {
            Assertions.assertEquals("Product does not contains in the bakery!", ex.getMessage());
        }
    }

    @Test
    protected void smallBakeryCanSellOnlyOneTypeOfProductsTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createSmallBakery();
        bakery1.addProduct(product2);
        try {
            bakery1.addProduct(product1);
        } catch (SmallBakeryCanSellOnlyProductsWithOneTypeException ex) {
            Assertions.assertEquals("Small bakery can sell only products with one type!", ex.getMessage());
        }
    }

    @Test
    protected void smallBakeryAddMoreThanTenProductsTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createSmallBakery();
        for (int i = 0; i < 9; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.bakeryTypes.PIE)
                    .setName("Pie")
                    .setKilocalories(233.32)
                    .setPrice(3.12)
                    .createProduct());
        }
        try {
            bakery1.addProduct(product1);
        } catch (ProductLimitExceededException ex) {
            Assertions.assertEquals("Product limit exceeded!", ex.getMessage());
        }
    }

    @Test
    protected void bigBakeryFindProductsByTypeTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, SearchProductsNotFoundException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.CAKE)
                .setName("Cake")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.BUN)
                .setName("Bun")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product4 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        bakery1.addProduct(product1);
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product4);
        assertEquals(bakery1.findProductByType(Product.bakeryTypes.PIE), List.of(product2));
        assertEquals(bakery1.findProductByType(Product.bakeryTypes.COOKIE), List.of(product4));
        assertEquals(bakery1.findProductByType(Product.bakeryTypes.BUN), List.of(product3));
        assertEquals(bakery1.findProductByType(Product.bakeryTypes.CAKE), List.of(product1));
    }
    @Test
    protected void bigBakeryFindProductsByTypeMoreThanOneFoundTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, SearchProductsNotFoundException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.COOKIE)
                .setName("Cake")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.COOKIE)
                .setName("Bun")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product4 = new ProductBuilder()
                .setBakeryTypes(Product.bakeryTypes.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        bakery1.addProduct(product1);
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product4);
        assertEquals(bakery1.findProductByType(Product.bakeryTypes.COOKIE), List.of(product1, product3, product4));
    }

    @Test
    protected void bigBakeryAddALotOfProductsTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        for (int i = 0; i < 1000; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.bakeryTypes.PIE)
                    .setName("Pie")
                    .setKilocalories(233.32)
                    .setPrice(3.12)
                    .createProduct());
        }
        assertEquals(bakery1.getProducts().size(), 1000);
    }

}
