package ee.taltech.iti0202.computerstore.store;

import ee.taltech.iti0202.computerstore.Customer;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.database.Database;
import ee.taltech.iti0202.computerstore.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class Store {
    private String name;
    private BigDecimal balance;
    private final int six = 6;


    private BigDecimal profitMargin;
    private final Database database;

    public Store(String name, BigDecimal balance, BigDecimal profitMargin) {
        BigDecimal bg1 = new BigDecimal("1");
        int res = profitMargin.compareTo(bg1);
        if (res < 0) {
            throw new IllegalArgumentException();
        } else {
            this.name = name;
            this.balance = balance;
            this.profitMargin = profitMargin;
            this.database = Database.getInstance();
        }
    }


    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException {
        if (database.getComponents().containsKey(id)) {
            Component component = database.getComponents().get(id);
            BigDecimal newPrice = profitMargin.multiply(component.getPrice());
            if (newPrice.compareTo(customer.getBalance()) > 0) {
                throw new NotEnoughMoneyException();
            } else {
                database.decreaseComponentStock(component.getId(), 1);
                balance = balance.add(newPrice);
                customer.setBalance(customer.getBalance().subtract(newPrice));
                customer.addComponents(component);
                return component;

            }
        }
        return null;
    }

    public List<Component> getAvailableComponents() {
        return database.getComponents()
                .values()
                .stream()
                .filter(c -> c.getAmount() > 0)
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByAmount() {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .sorted(Comparator.comparingInt(Component::getAmount))
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByName() {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .sorted(Comparator.comparing(Component::getName))
                .collect(Collectors.toList());
    }

    public List<Component> getComponentsSortedByPrice() {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .sorted(Comparator.comparing(Component::getPrice))
                .collect(Collectors.toList());
    }

    public List<Component> filterByType(Component.Type type) {
        return new ArrayList<>(database.getComponents().values())
                .stream()
                .filter(o -> o.getType().equals(type))
                .collect(Collectors.toList());
    }

    public BigDecimal getInventoryValue() {
        BigDecimal output = BigDecimal.ZERO;
        for (Component component : database.getComponents().values()) {
            BigDecimal num1 = new BigDecimal(component.getAmount());
            output = output.add(component.getPrice().multiply(num1).multiply(profitMargin));
        }
        MathContext mc = new MathContext(six);
        return output.round(mc);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(BigDecimal profitMargin) {
        BigDecimal bg1 = new BigDecimal("1");
        int res = profitMargin.compareTo(bg1);
        if (res < 0) {
            throw new IllegalArgumentException();
        }
        this.profitMargin = profitMargin;
    }
}
