package ee.taltech.iti0202.computerstore.store;

import ee.taltech.iti0202.computerstore.Customer;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.database.Database;
import ee.taltech.iti0202.computerstore.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class Store {
    private String name;
    private BigDecimal balance;
    private BigDecimal profitMargin;
    private final Database database;

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setProfitMargin(BigDecimal profitMargin) {
        this.profitMargin = profitMargin;
    }

    public Store(String name, BigDecimal balance, BigDecimal profitMargin) {
        BigDecimal one = new BigDecimal("1");
        if (profitMargin.compareTo(one) < 1) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.balance = balance;
        this.profitMargin = profitMargin;
        this.database = Database.getInstance();
    }

    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException {
        Component component;
        if (!database.getComponents().containsKey(id)) {
            throw new ProductNotFoundException();
        }
        component = database.getComponents().get(id);
        BigDecimal price = profitMargin.multiply(component.getPrice());
        if (price.compareTo(customer.getBalance()) > 0) {
            throw new NotEnoughMoneyException();
        }
        customer.addComponents(component);
        database.deleteComponent(id);
        balance = balance.add(price);
        customer.setBalance(customer.getBalance().subtract(price));
        return component;
    }

    public List<Component> getAvailableComponents() {
        return database.getComponents().values().stream()
                .filter(component -> component.getAmount() > 0)
                .collect(Collectors.toList());

    }

    public List<Component> getComponentsSortedByAmount() {
        return database.getComponents().values().stream()
                .sorted(Comparator.comparing(Component::getAmount))
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByName() {
        return database.getComponents().values().stream()
                .sorted(Comparator.comparing(Component::getName))
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByPrice() {
        return database.getComponents().values().stream()
                .sorted(Comparator.comparing(Component::getPrice))
                .collect(Collectors.toList());
    }

    public List<Component> filterByType(Component.Type type) {
        return database.getComponents().values().stream()
                .filter(component -> component.getType()
                        .equals(type)).toList();
    }

    public BigDecimal getInventoryValue() {
        return BigDecimal.ZERO;
    }

    public String getName() {
        return name;
    }


    public BigDecimal getBalance() {
        return balance;
    }


    public BigDecimal getProfitMargin() {
        return profitMargin;
    }
}
