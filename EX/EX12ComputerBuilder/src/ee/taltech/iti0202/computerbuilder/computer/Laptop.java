package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.store.Store;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Laptop extends Computer {
    private List<Component> keyboard;
    private List<Component> touchpad;
    private List<Component> screen;
    private List<Component> battery;

    public Laptop() {
    }

    @Override
    public Boolean isEnoughComponents(Store store) {
        if (store.filterByType(Component.ComponentType.KEYBOARD).size() != 0
                & store.filterByType(Component.ComponentType.TOUCHPAD).size() != 0
                & store.filterByType(Component.ComponentType.SCREEN).size() != 0
                & store.filterByType(Component.ComponentType.BATTERY).size() != 0) {
            this.keyboard = store.filterByType(Component.ComponentType.KEYBOARD);
            this.touchpad = store.filterByType(Component.ComponentType.TOUCHPAD);
            this.screen = store.filterByType(Component.ComponentType.SCREEN);
            this.battery = store.filterByType(Component.ComponentType.BATTERY);
            return true;
        } else {
            return false;
        }
    }

    public Laptop(Component keyboard, Component touchpad,
                  Component screen, Component battery) {
        super.keyboard = keyboard;
        super.touchpad = touchpad;
        super.screen = screen;
        super.battery = battery;
    }

    @Override
    public Laptop getComputersWithTheRightPrice(Store store, double budget, Computer.UseCase useCase) {
        List<Laptop> output;
        if (isEnoughComponents(store)) {
            output = findComputersWithRightPrice(budget, useCase);
            logger.info(String.format("We found %s Laptop that are less than your price and are trying to fount the "
                    + "best...", output.size()));
            return output.size() != 0 ? sortAssembleLaptops(output, useCase) : null;
        }
        return null;
    }

    public List<Laptop> findComputersWithRightPrice(double budget, Computer.UseCase useCase) {
        return keyboard.stream().flatMap(k -> touchpad.stream().flatMap(t -> screen.stream()
                .flatMap(s -> battery.stream().filter(b -> k.getPrice().intValue() + t.getPrice().intValue()
                                + s.getPrice().intValue() + b.getPrice().intValue() <= budget)
                        .map(b -> {
                            Laptop laptop = new Laptop(k, t, s, b);
                            laptop.setPrice(k.getPrice().intValue() + t.getPrice().intValue()
                                    + s.getPrice().intValue() + b.getPrice().intValue());
                            laptop.setTotalPoints(k.getPerformancePoints() + b.getPerformancePoints());
                            findBestComputerAccordingUseCase(useCase, t.getPerformancePoints(),
                                    s.getPerformancePoints());
                            return laptop;
                        })))).collect(Collectors.toList());
    }

    public Laptop sortAssembleLaptops(List<Laptop> list, Computer.UseCase useCase) {
        logger.info("------------------------Sorted assembled laptops------------------------");
        List<Laptop> output = list.stream().sorted(Comparator.comparing(Laptop::getTotalPoints)
                .thenComparing(Laptop::getPrice).reversed()).toList();
        for (int i = 0; i < output.size(); i++) {
            logger.info(output.get(i).toString());
        }
        return output.size() != 0 ? findBestLaptop(output, useCase) : null;

    }

    @Override
    public final void priceCalculation(Computer laptop, Store store) throws OutOfStockException,
            ProductNotFoundException {
        int finalPrice = laptop.getKeyboard().getPrice().intValue() + laptop.getTouchpad().getPrice().intValue()
                + laptop.getScreen().getPrice().intValue() + laptop.getBattery().getPrice().intValue()
                + ASSEMBLY_PRICE;
        setPrice(finalPrice);
        store.setBalance(store.getBalance().add(BigDecimal.valueOf(finalPrice)));
        store.getDatabase().decreaseComponentStock(laptop.getKeyboard().getId(), 1);
        store.getDatabase().decreaseComponentStock(laptop.getTouchpad().getId(), 1);
        store.getDatabase().decreaseComponentStock(laptop.getScreen().getId(), 1);
        store.getDatabase().decreaseComponentStock(laptop.getBattery().getId(), 1);

    }

    @Override
    public void findBestComputerAccordingUseCase(Computer.UseCase useCase, int screen,
                                                 int battery) {
        if (useCase != null) {
            double sum = useCase.equals(UseCase.GAMING) ? screen * MULTIPLIER + battery : screen + battery * MULTIPLIER;
            setTotalPoints(getTotalPoints() + sum);
        } else {
            setTotalPoints(screen + battery);
        }
    }

    public Laptop findBestLaptop(List<Laptop> list, Computer.UseCase useCase) {
        if (useCase == null || (useCase.equals(Computer.UseCase.GAMING) && list.get(0) != null)) {
            return list.get(0);
        }
        if (useCase.equals(Computer.UseCase.WORKSTATION)) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    @Override
    public Laptop assembleLaptop(Store store, double budget, Computer.UseCase useCase) {
        return getComputersWithTheRightPrice(store, budget, useCase);
    }
}
