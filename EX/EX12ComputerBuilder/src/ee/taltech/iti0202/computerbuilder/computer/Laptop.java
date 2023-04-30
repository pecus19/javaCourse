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
//    protected List<Component> keyboard = store.filterByType(Component.ComponentType.KEYBOARD);
//    protected List<Component> touchpad = store.filterByType(Component.ComponentType.TOUCHPAD);
//    protected List<Component> screen = store.filterByType(Component.ComponentType.SCREEN);
//    protected List<Component> battery = store.filterByType(Component.ComponentType.BATTERY);

    public Laptop() {
    }

    public Laptop(Component keyboard, Component touchpad,
                  Component screen, Component battery) {
        super.keyboard = keyboard;
        super.touchpad = touchpad;
        super.screen = screen;
        super.battery = battery;
    }

    /**
     * k-touchpad
     * t-screen
     * s-battery
     * b-keyboard
     */

    public Laptop getComputersWithTheRightPrice(Store store, double budget, Computer.UseCase useCase)
            throws OutOfStockException, ProductNotFoundException {
        List<Component> keyboard = store.filterByType(Component.ComponentType.KEYBOARD);
        List<Component> touchpad = store.filterByType(Component.ComponentType.TOUCHPAD);
        List<Component> screen = store.filterByType(Component.ComponentType.SCREEN);
        List<Component> battery = store.filterByType(Component.ComponentType.BATTERY);

        List<Laptop> output = keyboard.stream().flatMap(k -> touchpad.stream().flatMap(t -> screen.stream()
                .flatMap(s -> battery.stream().filter(b -> k.getPrice().intValue() + t.getPrice().intValue()
                                + s.getPrice().intValue() + b.getPrice().intValue() <= budget)
                        .map(b -> {
                            Laptop laptop = new Laptop(k, t, s, b);
                            laptop.setPrice(k.getPrice().intValue() + t.getPrice().intValue()
                                    + s.getPrice().intValue() + b.getPrice().intValue());
                            laptop.setTotalPoints(k.getPerformancePoints() + b.getPerformancePoints());
//                            + t.getPerformancePoints() + s.getPerformancePoints()
                            findBestLaptopAccordingUseCase(useCase, t.getPerformancePoints(), s.getPerformancePoints());
                            return laptop;
                        })))).collect(Collectors.toList());
        logger.info(String.format("We found %s laptops that are less than your price and are trying to fount the "
                + "best...", output.size()));
        return output.size() != 0 ? sortAssembleLaptops(output, useCase, store) : null;

    }

    public Laptop sortAssembleLaptops(List<Laptop> list, Computer.UseCase useCase, Store store) throws OutOfStockException, ProductNotFoundException {
        logger.info("------------------------Sorted assembled laptops------------------------");
        List<Laptop> output = list.stream().sorted(Comparator.comparing(Laptop::getTotalPoints)
                .thenComparing(Laptop::getPrice).reversed()).toList();
        for (int i = 0; i < output.size(); i++) {
            logger.info(output.get(i).toString());
        }
        return output.size() != 0 ? findBestLaptop(output, useCase, store) : null;

    }

    public final void priceCalculation(Laptop laptop, Store store) throws OutOfStockException, ProductNotFoundException {
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

    public void findBestLaptopAccordingUseCase(Computer.UseCase useCase, int screen,
                                               int battery) {
        if (useCase != null) {
            double sum = useCase.equals(UseCase.GAMING) ? screen * 1.5 + battery : screen + battery * 1.5;
            setTotalPoints(getTotalPoints() + sum);
        } else {
            setTotalPoints(screen + battery);
        }
    }

    public Laptop findBestLaptop(List<Laptop> list, Computer.UseCase useCase, Store store) throws OutOfStockException, ProductNotFoundException {
        if (useCase == null || (useCase.equals(Computer.UseCase.GAMING) && list.get(0) != null)) {
            priceCalculation(list.get(0), store);
            return list.get(0);
        }
        if (useCase.equals(Computer.UseCase.WORKSTATION)) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    @Override
    public Laptop assembleLaptop(Store store, double budget, Computer.UseCase useCase) throws OutOfStockException, ProductNotFoundException {
        return getComputersWithTheRightPrice(store, budget, useCase);
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "store=" + store +
                ", price=" + getPrice() +
                ", points=" + getTotalPoints() +
                ", keyboard=" + keyboard +
                ", touchpad=" + touchpad +
                ", screen=" + screen +
                ", battery=" + battery +
                '}';
    }

}
