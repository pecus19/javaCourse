package ee.taltech.iti0202.bakery;

import ee.taltech.iti0202.bakery.builder.BigBakeryBuilder;
import ee.taltech.iti0202.bakery.builder.CustomerBuilder;
import ee.taltech.iti0202.bakery.builder.ProductBuilder;
import ee.taltech.iti0202.bakery.builder.SmallBakeryBuilder;
import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.DoNotHaveEnoughMoneyToBuyException;
import ee.taltech.iti0202.bakery.exceptions.CanNotAddProductToTheBakeryException;
import ee.taltech.iti0202.bakery.exceptions.ProductAlreadyContainsInTheBakeryException;
import ee.taltech.iti0202.bakery.exceptions.ProductDoesNotContainsInBakeryException;
import ee.taltech.iti0202.bakery.exceptions.ProductLimitExceededException;
import ee.taltech.iti0202.bakery.exceptions.SearchProductsNotFoundException;
import ee.taltech.iti0202.bakery.exceptions.SmallBakeryCanSellOnlyProductsWithOneTypeException;
import ee.taltech.iti0202.bakery.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class BakeryTest {
    @Test
    protected void addProductToBakeryBasicTest() throws CanNotAddProductToTheBakeryException{
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
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
    protected void basicRemoveFromBakeryTest() throws CanNotAddProductToTheBakeryException
            , ProductDoesNotContainsInBakeryException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.17)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
                .setName("Cake")
                .setKilocalories(23.2)
                .setPrice(6.65)
                .createProduct();
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
    protected void basicRemoveFromBakeryNotContainsInTheBakeryTest()
            throws CanNotAddProductToTheBakeryException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.17)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
                .setName("Cake")
                .setKilocalories(23.2)
                .setPrice(6.65)
                .createProduct();
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
    protected void addProductToBakerySecondTimeTest() throws CanNotAddProductToTheBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
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
    protected void addProductToBakeryWhenHeAlreadyInAnotherBakeryTest()
            throws CanNotAddProductToTheBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
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
        } catch (CanNotAddProductToTheBakeryException ex) {
            Assertions.assertEquals("Cannot add product to the bakery!", ex.getMessage());
        }
    }

    @Test
    protected void testAddProductToBakerySecondTime()
            throws CanNotAddProductToTheBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(1000.34)
                .createSmallBakery();
        bakery1.addProduct(product1);
        try {
            bakery1.addProduct(product1);
        } catch (ProductAlreadyContainsInTheBakeryException ex) {
            Assertions.assertEquals("Product already contains in the bakery!", ex.getMessage());
        }
    }

    @Test
    protected void removeProductFromBakeryBasicTest() throws CanNotAddProductToTheBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.BREAD)
                .setName("Bread")
                .setKilocalories(233.32)
                .setPrice(3.12)
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
    protected void checkMoneyAfterBuyingByTypeBakeryTest() throws CanNotAddProductToTheBakeryException
            , ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
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
        bakery1.buyProductsByType(customer1, Product.Types.PIE);
        assertEquals(bakery1.getBankAccount(), moneyBefore + product1.getPrice());
    }

    @Test
    protected void checkMoneyAfterBuyingALotOfProductsByTypeBakeryTest()
            throws CanNotAddProductToTheBakeryException
            , ProductDoesNotContainsInBakeryException,
            DoNotHaveEnoughMoneyToBuyException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
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
        bakery1.buyProductsByType(customer1, Product.Types.PIE);
        bakery1.buyProductsByType(customer1, Product.Types.BREAD);
        bakery1.buyProductsByType(customer1, Product.Types.CAKE);
        assertEquals(bakery1.getBankAccount(), moneyBefore1 + moneyForAllProducts);
    }

    @Test
    protected void checkMoneyAfterBuyingByTypeCustomerTest() throws CanNotAddProductToTheBakeryException
            , ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
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
        bakery1.buyProductsByType(customer1, Product.Types.PIE);
        assertEquals(customer1.getBankAccount(), moneyBefore1 - product1.getPrice());
    }

    @Test
    protected void checkMoneyAfterBuyingALotOfProductsByTypeCustomerTest()
            throws CanNotAddProductToTheBakeryException
            , ProductDoesNotContainsInBakeryException,
            DoNotHaveEnoughMoneyToBuyException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
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
        bakery1.buyProductsByType(customer1, Product.Types.PIE);
        bakery1.buyProductsByType(customer1, Product.Types.BREAD);
        bakery1.buyProductsByType(customer1, Product.Types.CAKE);
        assertEquals(customer1.getBankAccount(), moneyBefore1 - moneyForAllProducts);
    }

    @Test
    protected void canNotBuyProductsDueToMoneyTest() throws CanNotAddProductToTheBakeryException
            , ProductDoesNotContainsInBakeryException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
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
            bakery1.buyProductsByType(customer1, Product.Types.PIE);
        } catch (DoNotHaveEnoughMoneyToBuyException ex) {
            Assertions.assertEquals("Don't have enough money to buy this product!", ex.getMessage());
        }
    }

    @Test
    protected void canNotBuyProductsDueToMoneyAfterBuyingSomeProductsTest()
            throws CanNotAddProductToTheBakeryException, ProductDoesNotContainsInBakeryException,
            DoNotHaveEnoughMoneyToBuyException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
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
        bakery1.buyProductsByType(customer1, Product.Types.BREAD);
        bakery1.buyProductsByType(customer1, Product.Types.CAKE);
        try {
            bakery1.buyProductsByType(customer1, Product.Types.PIE);
        } catch (DoNotHaveEnoughMoneyToBuyException ex) {
            Assertions.assertEquals("Don't have enough money to buy this product!", ex.getMessage());
        }
    }

    @Test
    protected void checkProductsThatCustomerBoughtTest() throws CanNotAddProductToTheBakeryException
            , ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
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
        bakery1.buyProductsByType(customer1, Product.Types.BREAD);
        bakery1.buyProductsByType(customer1, Product.Types.CAKE);
        bakery1.buyProductsByType(customer1, Product.Types.PIE);
        assertEquals(customer1.getProducts().size(), 3);
    }

    @Test
    protected void checkProductsInTheBakeryAfterBuyingTest() throws CanNotAddProductToTheBakeryException
            , ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
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
        bakery1.buyProductsByType(customer1, Product.Types.BREAD);
        bakery1.buyProductsByType(customer1, Product.Types.CAKE);
        bakery1.buyProductsByType(customer1, Product.Types.PIE);
        assertEquals(bakery1.getProducts().size(), 0);
    }

    @Test
    protected void onlyOneCustomerCanBuyOneProductTest() throws CanNotAddProductToTheBakeryException
            , ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
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
        bakery1.buyProductsByType(customer1, Product.Types.BREAD);
        try {
            bakery1.buyProductsByType(customer2, Product.Types.BREAD);
        } catch (ProductDoesNotContainsInBakeryException ex) {
            Assertions.assertEquals("Product does not contains in the bakery!", ex.getMessage());
        }
    }

    @Test
    protected void findProductByTypeNotFoundTest() throws CanNotAddProductToTheBakeryException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        bakery1.addProduct(product1);
        try {
            bakery1.findProductByType(Product.Types.COOKIE);
        } catch (SearchProductsNotFoundException ex) {
            Assertions.assertEquals("Search products not found!", ex.getMessage());
        }
    }

    @Test
    protected void findProductByIdTest() throws CanNotAddProductToTheBakeryException,
            ProductDoesNotContainsInBakeryException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
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
                .setPrice(10.3)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product1);
        Optional<Product> optional = Optional.of(product2);
        assertEquals(bakery1.findProductById(1), optional);
    }

    @Test
    protected void findProductByNameTest() throws CanNotAddProductToTheBakeryException
            , SearchProductsNotFoundException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
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
    protected void findProductByPriceTest() throws CanNotAddProductToTheBakeryException,
            SearchProductsNotFoundException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
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
    protected void findProductByPriceWithWrongPriceTest() throws CanNotAddProductToTheBakeryException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
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
            Assertions.assertEquals("Search products not found!", ex.getMessage());
        }
    }

    @Test
    protected void findProductByPriceWithWrongNameTest() throws CanNotAddProductToTheBakeryException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
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
            Assertions.assertEquals("Search products not found!", ex.getMessage());
        }
    }

    @Test
    protected void smallBakeryCanSellOnlyOneProductTest() throws CanNotAddProductToTheBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
                .setName("Cake")
                .setKilocalories(643.01)
                .setPrice(10.3)
                .createProduct();
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(1000.34)
                .createSmallBakery();
        bakery1.addProduct(product1);
        bakery1.addProduct(product2);
        try {
            bakery1.addProduct(product3);
        } catch (SmallBakeryCanSellOnlyProductsWithOneTypeException ex) {
            Assertions.assertEquals("Small bakery can sell only products with one type!", ex.getMessage());
        }
    }

    @Test
    protected void smallBakeryProductLimitExceededTest() throws CanNotAddProductToTheBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(1000.34)
                .createSmallBakery();
        for (int i = 0; i < 4; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.CAKE)
                    .setName("Cake")
                    .setKilocalories(643.01)
                    .setPrice(10.3)
                    .createProduct());
        }
        try {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.CAKE)
                    .setName("Cake5")
                    .setKilocalories(643.01)
                    .setPrice(10.3)
                    .createProduct());
        } catch (ProductLimitExceededException ex) {
            Assertions.assertEquals("Product limit exceeded!", ex.getMessage());
        }
    }

    @Test
    protected void findProductByPriceWithWrongIdTest() throws CanNotAddProductToTheBakeryException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(233.32)
                .setPrice(3.12)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
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
    protected void smallBakeryCanSellOnlyOneTypeOfProductsTest() throws CanNotAddProductToTheBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
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
    protected void bigBakeryAddProductSecondTime() throws CanNotAddProductToTheBakeryException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        bakery1.addProduct(product1);
        try {
            bakery1.addProduct(product1);
        } catch (CanNotAddProductToTheBakeryException ex) {
            Assertions.assertEquals("Cannot add product to the bakery!", ex.getMessage());
        }
    }

    @Test
    protected void smallBakeryAddMoreThanTenProductsTest() throws CanNotAddProductToTheBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
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
                    .setBakeryTypes(Product.Types.PIE)
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
    protected void bigBakeryFindProductsByTypeTest() throws CanNotAddProductToTheBakeryException,
            SearchProductsNotFoundException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.CAKE)
                .setName("Cake")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.BUN)
                .setName("Bun")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product4 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        bakery1.addProduct(product1);
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product4);
        assertEquals(bakery1.findProductByType(Product.Types.PIE), List.of(product2));
        assertEquals(bakery1.findProductByType(Product.Types.COOKIE), List.of(product4));
        assertEquals(bakery1.findProductByType(Product.Types.BUN), List.of(product3));
        assertEquals(bakery1.findProductByType(Product.Types.CAKE), List.of(product1));
    }

    @Test
    protected void bigBakeryFindProductsByTypeMoreThanOneFoundTest()
            throws CanNotAddProductToTheBakeryException, SearchProductsNotFoundException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Cake")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Bun")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product4 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        bakery1.addProduct(product1);
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product4);
        assertEquals(bakery1.findProductByType(Product.Types.COOKIE), List.of(product1, product3, product4));
    }

    @Test
    protected void bigBakeryAddALotOfProductsTest() throws CanNotAddProductToTheBakeryException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        for (int i = 0; i < 1000; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.PIE)
                    .setName("Pie")
                    .setKilocalories(233.32)
                    .setPrice(3.12)
                    .createProduct());
        }
        assertEquals(bakery1.getProducts().size(), 1000);
    }

    // Pagaritoodete pingerida(4 part)
    @Test
    protected void getRatingSimpleTest() throws CanNotAddProductToTheBakeryException
            , ProductDoesNotContainsInBakeryException,
            DoNotHaveEnoughMoneyToBuyException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(11232.2)
                .createCustomer();
        bakery1.addProduct(product1);
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.buyProductsByType(customer1, Product.Types.PIE);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        assertEquals(bakery1.getProductRating().get(0).getName(), "Cookie");
        assertEquals(bakery1.getProductRating().get(0).getKilocalories(), 400.0);
        assertEquals(bakery1.getProductRating().get(0).getPrice(), 1.2);
    }

    @Test
    protected void getRatingProductsAreEqualsSortByPriceAndCalories()
            throws CanNotAddProductToTheBakeryException, ProductDoesNotContainsInBakeryException,
            DoNotHaveEnoughMoneyToBuyException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(200.0)
                .setPrice(1.1)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product4 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(200.0)
                .setPrice(1.1)
                .createProduct();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(11232.2)
                .createCustomer();
        bakery1.addProduct(product1);
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product4);
        bakery1.buyProductsByType(customer1, Product.Types.PIE);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        bakery1.buyProductsByType(customer1, Product.Types.PIE);
        assertEquals(bakery1.getProductRating(), List.of(product2, product1));
    }

    @Test
    protected void getRatingProductsSortByPriceAndCalories() throws CanNotAddProductToTheBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(1000.34)
                .createSmallBakery();
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Cookie")
                .setKilocalories(100.0)
                .setPrice(10.0)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Pie")
                .setKilocalories(200.0)
                .setPrice(10.0)
                .createProduct();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(11232.2)
                .createCustomer();
        bakery1.addProduct(product1);
        bakery1.addProduct(product2);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        System.out.println(bakery1.getProductRating());
        assertEquals(bakery1.getProductRating().get(0).getName(), "Cookie"); // 100+10=110
        assertEquals(bakery1.getProductRating().get(1).getName(), "Pie"); // 200+10=210
    }

    @Test
    protected void getRatingProductsThirdSortingLevelTest() throws CanNotAddProductToTheBakeryException,
            ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Cookie")
                .setKilocalories(100.0)
                .setPrice(1.5)
                .createProduct();
        Product product5 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Cookie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Product product4 = new ProductBuilder()
                .setBakeryTypes(Product.Types.PIE)
                .setName("Pie")
                .setKilocalories(400.0)
                .setPrice(1.2)
                .createProduct();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(11232.2)
                .createCustomer();
        bakery1.addProduct(product1);
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product4);
        bakery1.addProduct(product5);
        bakery1.buyProductsByType(customer1, Product.Types.PIE);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        bakery1.buyProductsByType(customer1, Product.Types.PIE);
        assertEquals(bakery1.getProductRating().get(0).getName(), "Cookie");

    }

    @Test
    protected void getRatingProductsThirdSortingLevelThreeProductsTest()
            throws CanNotAddProductToTheBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createSmallBakery();
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Tartu peenleib")
                .setKilocalories(100.0)
                .setPrice(10.0)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Meremehe sai")
                .setKilocalories(100.0)
                .setPrice(10.0)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("Tartu peenleib")
                .setKilocalories(120.0)
                .setPrice(10.0)
                .createProduct();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(11232.2)
                .createCustomer();
        bakery1.addProduct(product1);
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        System.out.println(bakery1.getProductRating());
        assertEquals(bakery1.getProductRating().get(0).getName(), "Tartu peenleib");
        assertEquals(bakery1.getProductRating().get(1).getName(), "Meremehe sai");
    }

    @Test
    protected void getRatingProductsSortByAlphabetTest() throws CanNotAddProductToTheBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException {
        SmallBakery bakery1 = new SmallBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createSmallBakery();
        Product product1 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("A")
                .setKilocalories(100.0)
                .setPrice(10.0)
                .createProduct();
        Product product2 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("B")
                .setKilocalories(100.0)
                .setPrice(10.0)
                .createProduct();
        Product product3 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("A")
                .setKilocalories(100.0)
                .setPrice(10.0)
                .createProduct();
        Product product4 = new ProductBuilder()
                .setBakeryTypes(Product.Types.COOKIE)
                .setName("B")
                .setKilocalories(100.0)
                .setPrice(10.0)
                .createProduct();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(11232.2)
                .createCustomer();
        bakery1.addProduct(product1);
        bakery1.addProduct(product2);
        bakery1.addProduct(product3);
        bakery1.addProduct(product4);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        System.out.println(bakery1.getProductRating());
        assertEquals(bakery1.getProductRating().get(0).getName(), "A");
        assertEquals(bakery1.getProductRating().get(1).getName(), "B");
    }

    @Test
    protected void priceIncreasingBestThreeProductsChangePriceTest()
            throws CanNotAddProductToTheBakeryException, ProductDoesNotContainsInBakeryException,
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
        for (int i = 0; i < 101; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.COOKIE)
                    .setName("First")
                    .setKilocalories(100.0)
                    .setPrice(1.0)
                    .createProduct());
        }
        for (int i = 0; i < 151; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.PIE)
                    .setName("Second")
                    .setKilocalories(100.0)
                    .setPrice(1.0)
                    .createProduct());
        }
        for (int i = 0; i < 201; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.CAKE)
                    .setName("Third")
                    .setKilocalories(100.0)
                    .setPrice(1.0)
                    .createProduct());
        }
        for (int i = 0; i < 251; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.BREAD)
                    .setName("Fourth")
                    .setKilocalories(100.0)
                    .setPrice(1.0)
                    .createProduct());
        }
        for (int i = 0; i < 90; i++) {
            bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        }
        for (int i = 0; i < 120; i++) {
            bakery1.buyProductsByType(customer1, Product.Types.PIE);
        }
        for (int i = 0; i < 190; i++) {
            bakery1.buyProductsByType(customer1, Product.Types.CAKE);
        }
        for (int i = 0; i < 230; i++) {
            bakery1.buyProductsByType(customer1, Product.Types.BREAD);
        }
        double firstPriceBefore = bakery1.findProductByType(Product.Types.COOKIE).get(0).getPrice();
        double secondPriceBefore = bakery1.findProductByType(Product.Types.PIE).get(0).getPrice();
        double thirdPriceBefore = bakery1.findProductByType(Product.Types.CAKE).get(0).getPrice();
        double fourthPriceBefore = bakery1.findProductByType(Product.Types.BREAD).get(0).getPrice();
        bakery1.increasingPrices();
        assertEquals(bakery1.findProductByType(Product.Types.COOKIE).get(3).getPrice(), firstPriceBefore);
        assertNotEquals(bakery1.findProductByType(Product.Types.PIE).get(5).getPrice(), secondPriceBefore);
        assertNotEquals(bakery1.findProductByType(Product.Types.CAKE).get(10).getPrice(), thirdPriceBefore);
        assertNotEquals(bakery1.findProductByType(Product.Types.BREAD).get(15).getPrice(), fourthPriceBefore);
    }

    @Test
    protected void priceIncreasingPriceCanNotBeMoreThanMaxPriceTest()
            throws CanNotAddProductToTheBakeryException, ProductDoesNotContainsInBakeryException,
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
        for (int i = 0; i < 601; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.COOKIE)
                    .setName("First")
                    .setKilocalories(100.0)
                    .setPrice(1.0)
                    .createProduct());
        }

        for (int i = 0; i < 550; i++) {
            bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        }

        double firstPriceBefore = bakery1.findProductByType(Product.Types.COOKIE).get(0).getPrice();
        bakery1.increasingPrices();
        assertEquals(bakery1.findProductByType(Product.Types.COOKIE).get(3).getPrice(),
                firstPriceBefore * 1.5);
        assertEquals(bakery1.findProductByType(Product.Types.COOKIE).get(43).getPrice(),
                firstPriceBefore * 1.5);
        assertEquals(bakery1.findProductByType(Product.Types.COOKIE).get(13).getPrice(),
                firstPriceBefore * 1.5);
    }

    @Test
    protected void onlyTwoProductsShouldIncreaseThePrice() throws CanNotAddProductToTheBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException, DoNotHaveEnoughMoneyToBuyException,
            SearchProductsNotFoundException {
        BigBakery bakery1 = new BigBakeryBuilder()
                .setName("Big Bakery")
                .setBankAccount(1000.34)
                .createBigBakery();
        Customer customer1 = new CustomerBuilder().setName("Danila")
                .setAge(21)
                .setBankAccount(999999.2)
                .createCustomer();
        for (int i = 0; i < 201; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.COOKIE)
                    .setName("First")
                    .setKilocalories(100.0)
                    .setPrice(1.0)
                    .createProduct());
        }
        for (int i = 0; i < 301; i++) {
            bakery1.addProduct(new ProductBuilder()
                    .setBakeryTypes(Product.Types.BREAD)
                    .setName("Second")
                    .setKilocalories(100.0)
                    .setPrice(1.0)
                    .createProduct());
        }

        for (int i = 0; i < 150; i++) {
            bakery1.buyProductsByType(customer1, Product.Types.COOKIE);
        }
        for (int i = 0; i < 250; i++) {
            bakery1.buyProductsByType(customer1, Product.Types.BREAD);
        }

        double firstPriceBefore = bakery1.findProductByType(Product.Types.COOKIE).get(0).getPrice();
        double secondPriceBefore = bakery1.findProductByType(Product.Types.BREAD).get(0).getPrice();
        bakery1.increasingPrices();
        assertNotEquals(bakery1.findProductByType(Product.Types.COOKIE).get(0).getPrice(), firstPriceBefore);
        assertNotEquals(bakery1.findProductByType(Product.Types.BREAD).get(0).getPrice(), secondPriceBefore);
    }
}
