package ee.taltech.iti0202.bakery.order;

import ee.taltech.iti0202.bakery.BigBakery;
import ee.taltech.iti0202.bakery.SmallBakery;
import ee.taltech.iti0202.bakery.builder.BigBakeryBuilder;
import ee.taltech.iti0202.bakery.builder.CustomerBuilder;
import ee.taltech.iti0202.bakery.builder.ProductBuilder;
import ee.taltech.iti0202.bakery.builder.SmallBakeryBuilder;
import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.*;
import ee.taltech.iti0202.bakery.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class OrderTest {
    @Test
    protected void makeBasicOrderTest() throws ProductAlreadyContainsInAnotherBakeryException,
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
        Order order = new Order(customer1, bakery1, List.of(product1, product2, product3));
        order.makeOrder();
        assertEquals(order.confirmOrder(), List.of(product1, product2, product3));
    }

    @Test
    protected void checkCustomerBoughtProductsTest() throws ProductAlreadyContainsInAnotherBakeryException,
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
        Order order = new Order(customer1, bakery1, List.of(product1, product2, product3));
        order.makeOrder();
        order.confirmOrder();
        assertEquals(customer1.getOrders(), List.of(order));
    }

    @Test
    protected void checkBakeryMoneyAfterReceivingTheOrderTest() throws ProductAlreadyContainsInAnotherBakeryException,
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
        double bankAccountBefore = bakery1.getBankAccount();
        double productPrice = product1.getPrice() + product2.getPrice() + product3.getPrice();
        Order order = new Order(customer1, bakery1, List.of(product1, product2, product3));
        order.makeOrder();
        order.confirmOrder();
        assertEquals(bakery1.getBankAccount(), bankAccountBefore + productPrice);
    }

    @Test
    protected void checkCustomerMoneyAfterReceivingTheOrderTest() throws ProductAlreadyContainsInAnotherBakeryException,
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
        double customer1BankAccount = customer1.getBankAccount();
        double productPrice = product1.getPrice() + product2.getPrice() + product3.getPrice();
        Order order = new Order(customer1, bakery1, List.of(product1, product2, product3));
        order.makeOrder();
        order.confirmOrder();
        assertEquals(customer1.getBankAccount(), customer1BankAccount - productPrice);
    }

    @Test
    protected void canNotMakeAnOrderTest() throws ProductAlreadyContainsInAnotherBakeryException,
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
        SmallBakery bakery2 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(131.34)
                .createSmallBakery();
        bakery1.addProduct(product2);
        bakery1.addProduct(product1);
        bakery2.addProduct(product3);
        Order order = new Order(customer1, bakery1, List.of(product1, product2, product3));
        order.makeOrder();
        try {
            order.confirmOrder();
        } catch (OrderCanNotBeDoneException ex) {
            Assertions.assertEquals("Order can not be done!", ex.getMessage());
        }
    }

    @Test
    protected void canNotMakeAnOrderCheckMoneyTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException,
            UnconfirmedOrdersException, DoNotHaveEnoughMoneyToBuyException {
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
        SmallBakery bakery2 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(131.34)
                .createSmallBakery();
        bakery1.addProduct(product2);
        bakery1.addProduct(product1);
        bakery2.addProduct(product3);
        double customerBankAccountBefore = customer1.getBankAccount();
        Order order = new Order(customer1, bakery1, List.of(product1, product2, product3));
        order.makeOrder();
        try {
            order.confirmOrder();
        } catch (OrderCanNotBeDoneException ignored) {
        } finally {
            assertEquals(customer1.getBankAccount(), customerBankAccountBefore);
        }
    }

    @Test
    protected void mustCancelTheOldOrderToOrderANewOneTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException,
            UnconfirmedOrdersException, DoNotHaveEnoughMoneyToBuyException {
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
        SmallBakery bakery2 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(131.34)
                .createSmallBakery();
        bakery1.addProduct(product2);
        bakery1.addProduct(product1);
        bakery2.addProduct(product3);
        Order order = new Order(customer1, bakery1, List.of(product1, product2, product3));
        order.makeOrder();
        Order order2 = new Order(customer1, bakery1, List.of(product1, product2));
        try {
            order2.makeOrder();
        } catch (UnconfirmedOrdersException ex) {
            Assertions.assertEquals("Cancel the order to make a new one!", ex.getMessage());
        }
    }

    @Test
    protected void сanceledTheOldOrderAndOrderedANewOneTest() throws ProductAlreadyContainsInAnotherBakeryException,
            ProductLimitExceededException, ProductDoesNotContainsInBakeryException,
            ProductAlreadyContainsInTheBakeryException, SmallBakeryCanSellOnlyProductsWithOneTypeException,
            UnconfirmedOrdersException, DoNotHaveEnoughMoneyToBuyException, OrderCanNotBeDoneException {
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
        SmallBakery bakery2 = new SmallBakeryBuilder()
                .setName("Small Bakery")
                .setBankAccount(131.34)
                .createSmallBakery();
        bakery1.addProduct(product2);
        bakery1.addProduct(product1);
        bakery2.addProduct(product3);
        Order order = new Order(customer1, bakery1, List.of(product1, product2, product3));
        order.makeOrder();
        Order order2 = new Order(customer1, bakery1, List.of(product1, product2));
        try {
            order.confirmOrder();
        } catch (OrderCanNotBeDoneException ex) {
            order.cancelOrder();
        }
        try {
            order2.makeOrder();
        } catch (ProductDoesNotContainsInBakeryException ex) {

        } finally {
            assertEquals(order2.confirmOrder().size(), 2);
        }
    }


}