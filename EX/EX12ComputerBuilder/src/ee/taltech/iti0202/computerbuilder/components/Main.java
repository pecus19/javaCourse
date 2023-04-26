package ee.taltech.iti0202.computerbuilder.components;

import ee.taltech.iti0202.computerbuilder.computer.Computer;
import ee.taltech.iti0202.computerbuilder.computer.Laptop;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.store.Store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws ProductAlreadyExistsException {
//        keyboard = keyboard;
//        this.touchpad = touchpad;
//        this.screen = screen;
//        this.battery = battery;
        Database database = Database.getInstance();
        Component component = new Component("component1", Component.ComponentType.KEYBOARD, new BigDecimal(43), "MSI",
                8, 200);
        Component component2 = new Component("component2", Component.ComponentType.TOUCHPAD, new BigDecimal(34), "MSI",
                2, 100);
        Component component3 = new Component("component3", Component.ComponentType.SCREEN, new BigDecimal(12), "MSI",
                4, 50);
        Component component4 = new Component("component4", Component.ComponentType.BATTERY, new BigDecimal(234), "MSI",
                8, 70);
        Component component5 = new Component("component5", Component.ComponentType.KEYBOARD, new BigDecimal(22), "MSI",
                8, 200);
        Component component6 = new Component("component6", Component.ComponentType.TOUCHPAD, new BigDecimal(2), "MSI",
                2, 100);
        Component component7 = new Component("component7", Component.ComponentType.SCREEN, new BigDecimal(53), "MSI",
                4, 50);
        Component component8 = new Component("component8", Component.ComponentType.BATTERY, new BigDecimal(6), "MSI",
                8, 70);
        Component component9 = new Component("component9", Component.ComponentType.BATTERY, new BigDecimal(3), "MSI",
                8, 70);
        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        database.saveComponent(component6);
        database.saveComponent(component7);
        database.saveComponent(component8);
        database.saveComponent(component9);
//        List<Component> keyboard = new ArrayList<>(List.of(component, component5));
//        List<Component> touchpad = new ArrayList<>(List.of(component2, component6));
//        List<Component> screen = new ArrayList<>(List.of(component3, component7));
//        List<Component> battery = new ArrayList<>(List.of(component4, component8, component9));
////        List<List> computers = new ArrayList<>();
//        List<List> computers = keyboard.stream()
//                .flatMap(k -> touchpad.stream()
//                        .flatMap(t -> screen.stream()
//                                .flatMap(s -> battery.stream()
//                                        .filter(b -> k.getPrice().intValue() + t.getPrice().intValue() + s.getPrice().intValue() + b.getPrice().intValue() < 100)
//                                        .map(b -> List.of(k.getName(), t.getName(), s.getName(), b.getName(), k.getPrice().intValue() + t.getPrice().intValue() + s.getPrice().intValue() + b.getPrice().intValue()))
//                                )
//                        )
//                )
//                .collect(Collectors.toList());
//        for (int i = 0; i < computers.size(); i++) {
//            System.out.println(computers.get(i));
//        }
//        System.out.println(computers.size());
        Laptop laptop = new Laptop();
//        System.out.println(laptop.assembleLaptop(new Store("Store", new BigDecimal(100), new BigDecimal(123)), 100));
//        slaptop.assembleLaptop(new Store("Store", new BigDecimal(100), new BigDecimal(123)));
    }

}
