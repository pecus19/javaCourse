package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.store.Store;

import java.util.logging.Logger;

public abstract class Computer {
    protected Component keyboard;
    protected Component touchpad;
    protected Component screen;
    protected Component battery;
    protected Component cpu;
    protected Component gpu;
    protected Component ram;
    protected Component motherboard;
    protected Component psu;
    protected Component aCase;
    protected Component hdd;
    protected Component ssd;
    protected int price = 0;
    protected double points = 0;
    protected Logger logger = Logger.getLogger(Laptop.class.getName());
    protected Store store;
    protected final static int ASSEMBLY_PRICE = 15;

    public abstract Computer getComputersWithTheRightPrice(Store store, double budget, Computer.UseCase useCase)
            throws OutOfStockException, ProductNotFoundException;

    public abstract Computer assembleLaptop(Store store, double budget, Computer.UseCase useCase)
            throws OutOfStockException, ProductNotFoundException;

    public abstract void findBestComputerAccordingUseCase(Computer.UseCase useCase, int gpu, int processor);

    public abstract void priceCalculation(Computer computer, Store store)
            throws OutOfStockException, ProductNotFoundException;

    public abstract Boolean isEnoughComponents(Store store);

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getTotalPoints() {
        return points;
    }

    public void setTotalPoints(double points) {
        this.points = points;
    }


    public Component getCpu() {
        return cpu;
    }

    public Component getGpu() {
        return gpu;
    }


    public Component getRam() {
        return ram;
    }


    public Component getMotherboard() {
        return motherboard;
    }


    public Component getPsu() {
        return psu;
    }


    public Component getaCase() {
        return aCase;
    }


    public Component getHdd() {
        return hdd;
    }

    public Component getSsd() {
        return ssd;
    }


    public enum ComputerType {
        Desktop, LAPTOP
    }

    public enum UseCase {
        WORKSTATION, GAMING
    }
}
