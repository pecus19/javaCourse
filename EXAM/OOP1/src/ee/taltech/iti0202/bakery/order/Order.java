package ee.taltech.iti0202.bakery.order;

import ee.taltech.iti0202.bakery.SmallBakery;
import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.DoNotHaveEnoughMoneyToBuyException;
import ee.taltech.iti0202.bakery.exceptions.OrderCanNotBeDoneException;
import ee.taltech.iti0202.bakery.exceptions.ProductDoesNotContainsInBakeryException;
import ee.taltech.iti0202.bakery.exceptions.UnconfirmedOrdersException;
import ee.taltech.iti0202.bakery.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Order {
    private Customer customer;
    private List<Product> products;
    public SmallBakery bakery;
    private boolean approved = false;
    protected Logger logger = Logger.getLogger(Order.class.getName());


    public Order(Customer customer, SmallBakery bakery, List<Product> products) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be empty");
        }

        if (bakery == null) {
            throw new IllegalArgumentException("Bakery cannot be empty");
        }

        if (products.size() == 0) {
            throw new IllegalArgumentException("You need to add some products to the order");
        }
        this.customer = customer;
        this.products = products;
        this.bakery = bakery;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void makeOrder() throws UnconfirmedOrdersException, ProductDoesNotContainsInBakeryException {
        if (getCustomer().isConfirmedOrders()) {
            throw new UnconfirmedOrdersException();
        }
        getBakery().takeOrder(this);
        logger.info(getCustomer().getName() + " made an order");
        getCustomer().setConfirmedOrders(true);
    }

    public void cancelOrder() {
        getCustomer().setConfirmedOrders(false);
        setApproved(false);

    }

    public List<Product> confirmOrder() throws DoNotHaveEnoughMoneyToBuyException,
            ProductDoesNotContainsInBakeryException,
            OrderCanNotBeDoneException {
        List<Product> output = new ArrayList<>();
        if (isApproved()) {
            for (int i = 0; i < getProducts().size(); i++) {
                getBakery().buyProducts(getCustomer(), getProducts().get(i));
                logger.info(getCustomer().getName() + " confirmed an order");
                getCustomer().setConfirmedOrders(false);
                getCustomer().addOrder(this);
                output.add(getProducts().get(i));
            }
            return output;
        }
        throw new OrderCanNotBeDoneException();

    }

    public SmallBakery getBakery() {
        return bakery;
    }

    public void setBakery(SmallBakery bakery) {
        this.bakery = bakery;
    }
}
