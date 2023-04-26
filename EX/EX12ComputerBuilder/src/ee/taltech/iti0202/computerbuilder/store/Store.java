package ee.taltech.iti0202.computerbuilder.store;

import ee.taltech.iti0202.computerbuilder.computer.Computer;
import ee.taltech.iti0202.computerbuilder.computer.Laptop;
import ee.taltech.iti0202.computerbuilder.customer.Customer;
import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.order.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
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

    public List<Component> filterByType(Component.ComponentType type) {
        return getAvailableComponents().stream()
                .filter(component -> component.getType()
                        .equals(type)).toList();
    }

    public BigDecimal getInventoryValue() {
        BigDecimal sum = BigDecimal.valueOf(0);
        for (int i = 0; i < database.getComponents().values().size(); i++) {
            Component component = database.getComponents().get(i);
            sum = sum.add(component.getPrice().multiply(new BigDecimal(component.getAmount()))
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

    public Laptop assembleComputer(Order order) {
        Laptop laptop = new Laptop();
        if (order.getType().equals(Computer.ComputerType.Desktop)) {
//            return new DesktopComputer();
            laptop.assembleLaptop(this, order.getBudget(), order.getUseCase());
            return laptop.assembleLaptop(this, order.getBudget(), order.getUseCase());
        } else if (order.getType().equals(Computer.ComputerType.LAPTOP)) {
            return laptop.assembleLaptop(this, order.getBudget(), order.getUseCase());
        }
        return null;
    }
}
