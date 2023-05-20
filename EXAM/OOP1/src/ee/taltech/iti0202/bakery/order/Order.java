package ee.taltech.iti0202.bakery.order;

import ee.taltech.iti0202.bakery.SmallBakery;
import ee.taltech.iti0202.bakery.customer.Customer;
import ee.taltech.iti0202.bakery.exceptions.*;
import ee.taltech.iti0202.bakery.product.Product;

import java.util.List;
import java.util.logging.Logger;

public class Order {
    private Customer customer;
    private List<Product> products;
    public SmallBakery bakery;
    private boolean approved = false;
    protected Logger logger = Logger.getLogger(Order.class.getName());


    public Order(Customer customer, List<Product> products, SmallBakery bakery) {
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

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void makeOrder() throws SearchProductsNotFoundException, UnconfirmedOrdersException {
        if (!getCustomer().isConfirmedOrders()) {
            getBakery().takeOrder(this);
            logger.info(getCustomer() + " made an order");
            getCustomer().setConfirmedOrders(true);
        }
        throw new UnconfirmedOrdersException();
    }

    public void cancelOrder() {
        getCustomer().setConfirmedOrders(false);
        setApproved(false);

    }

    public void confirmOrder() throws DoNotHaveEnoughMoneyToBuyException, ProductDoesNotContainsInBakeryException,
            OrderCanNotBeDoneException {
        if (isApproved()) {
            for (int i = 0; i < getProducts().size(); i++) {
                getBakery().buyProducts(getCustomer(), getProducts().get(i));
                logger.info(getCustomer() + " confirmed an order");
                getCustomer().setConfirmedOrders(false);
            }
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
