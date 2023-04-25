package ee.taltech.iti0202.computerstore.store;

import ee.taltech.iti0202.computerstore.Customer;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.database.Database;
import ee.taltech.iti0202.computerstore.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.math.BigInteger;
import java.math.RoundingMode;
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
        BigDecimal one = new BigDecimal("1");
        if (profitMargin.compareTo(one) < 0) {
            throw new IllegalArgumentException();
        }
        this.profitMargin = profitMargin;
    }

    public Store(String name, BigDecimal balance, BigDecimal profitMargin) {
        BigDecimal one = new BigDecimal("1");
        if (profitMargin.compareTo(one) < 0) {
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
        } else {
            balance = balance.add(price);
            customer.setBalance(customer.getBalance().subtract(price));
            customer.addComponents(component);
            database.decreaseComponentStock(component.getId(), 1);
            return component;
        }
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
        BigDecimal sum = BigDecimal.valueOf(0);
        for (int i = 0; i < database.getComponents().values().size(); i++) {
            Component component = database.getComponents().get(i);
            sum = sum.multiply(component.getPrice().multiply(new BigDecimal(component.getAmount()))
                    .multiply(profitMargin));

        }
        return sum.setScale(2, RoundingMode.HALF_UP);
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
