package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.store.Store;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Laptop extends Computer {
    Logger logger = Logger.getLogger(Laptop.class.getName());
    Store store;
    private int price = 0;
    private int points = 0;
    private Component keyboard;
    private Component touchpad;
    private Component screen;
    private Component battery;

    public Laptop(Component keyboard, Component touchpad,
                  Component screen, Component battery) {
        this.keyboard = keyboard;
        this.touchpad = touchpad;
        this.screen = screen;
        this.battery = battery;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPerformancePoints(int points) {
        this.points = points;
    }

    public Laptop() {
    }

//    public List<Laptop> getSortedByPrice(List<Laptop> list) {
//        logger.info("------------------------Sorted assembled laptops by price------------------------");
//        List<Laptop> output;
//        output = list.stream().sorted(Comparator.comparing(Laptop::getPrice)).toList();
//        logger.info(output.toString());
//        return output;
//    }

    public Laptop getComputersWithTheRightPrice(Store store, double budget, UseCase useCase) {
        List<Component> keyboard = store.filterByType(Component.ComponentType.KEYBOARD);
        List<Component> touchpad = store.filterByType(Component.ComponentType.TOUCHPAD);
        List<Component> screen = store.filterByType(Component.ComponentType.SCREEN);
        List<Component> battery = store.filterByType(Component.ComponentType.BATTERY);
        List<Laptop> output;

        output = keyboard.stream()
                .flatMap(k -> touchpad.stream()
                        .flatMap(t -> screen.stream()
                                .flatMap(s -> battery.stream()
                                        .filter(b -> k.getPrice().intValue() + t.getPrice().intValue() + s.getPrice().intValue() + b.getPrice().intValue() <= budget)
                                        .map(b -> {
                                            Laptop laptop = new Laptop(k, t, s, b);
                                            laptop.setPrice(k.getPrice().intValue() + t.getPrice().intValue() + s.getPrice().intValue() + b.getPrice().intValue());
                                            laptop.setPerformancePoints(k.getPerformancePoints() + t.getPerformancePoints() + s.getPerformancePoints() + b.getPerformancePoints());
                                            return laptop;
                                        })
                                )
                        )
                )
                .collect(Collectors.toList());
        logger.info(String.format("We found %s laptops that are less than your price and are trying to fount the "
                + "best...", output.size()));
        return output.size() != 0 ? getSortAssembleLaptops(output, useCase) : null;

    }

    public Laptop getSortAssembleLaptops(List<Laptop> list, UseCase useCase) {
        logger.info("------------------------Sorted assembled laptops------------------------");
        List<Laptop> output;
        output = list.stream().sorted(Comparator.comparing(Laptop::getPoints)
                .thenComparing(Laptop::getPrice).reversed()).toList();
        for (int i = 0; i < output.size(); i++) {
            logger.info(output.get(i).toString());
        }
        return output.size() != 0 ? findBestLaptop(output, useCase) : null;

    }

    public Laptop findBestLaptop(List<Laptop> list, UseCase useCase) {
        if (useCase.equals(UseCase.GAMING)) {
            return list.get(0);
        }
        if (useCase.equals(UseCase.WORKSTATION)) {
            return list.get(list.size() - 1);
        }
        return null;
    }


    public Laptop assembleLaptop(Store store, double budget, UseCase useCase) {
        return getComputersWithTheRightPrice(store, budget, useCase);
    }


    public int getPrice() {
        return price;
    }


    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "store=" + store +
                ", price=" + price +
                ", points=" + points +
                ", keyboard=" + keyboard +
                ", touchpad=" + touchpad +
                ", screen=" + screen +
                ", battery=" + battery +
                '}';
    }

    public Logger getLogger() {
        return logger;
    }

    public Component getKeyboard() {
        return keyboard;
    }

    public Component getTouchpad() {
        return touchpad;
    }

    public Component getScreen() {
        return screen;
    }

    public Component getBattery() {
        return battery;
    }
}
