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
    Logger logger = Logger.getLogger(Laptop.class.getName());
    Store store;
    protected final static int ASSEMBLY_PRICE = 15;


    public Computer assembleLaptop(Store store, double budget, Computer.UseCase useCase) throws OutOfStockException, ProductNotFoundException {
        return null;
    }

    public Component getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Component keyboard) {
        this.keyboard = keyboard;
    }

    public Component getTouchpad() {
        return touchpad;
    }

    public void setTouchpad(Component touchpad) {
        this.touchpad = touchpad;
    }

    public Component getScreen() {
        return screen;
    }

    public void setScreen(Component screen) {
        this.screen = screen;
    }

    public Component getBattery() {
        return battery;
    }

    public void setBattery(Component battery) {
        this.battery = battery;
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

    public void setCpu(Component cpu) {
        this.cpu = cpu;
    }

    public Component getGpu() {
        return gpu;
    }

    public void setGpu(Component gpu) {
        this.gpu = gpu;
    }

    public Component getRam() {
        return ram;
    }

    public void setRam(Component ram) {
        this.ram = ram;
    }

    public Component getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Component motherboard) {
        this.motherboard = motherboard;
    }

    public Component getPsu() {
        return psu;
    }

    public void setPsu(Component psu) {
        this.psu = psu;
    }

    public Component getaCase() {
        return aCase;
    }

    public void setaCase(Component aCase) {
        this.aCase = aCase;
    }

    public Component getHdd() {
        return hdd;
    }

    public void setHdd(Component hdd) {
        this.hdd = hdd;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Component getSsd() {
        return ssd;
    }

    public void setSsd(Component ssd) {
        this.ssd = ssd;
    }

    public enum ComputerType {
        Desktop, LAPTOP
    }

    public enum UseCase {
        WORKSTATION, GAMING
    }
}
