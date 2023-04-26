package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;

public abstract class Computer {
    private Component.ComponentType cpu;
    private Component.ComponentType gpu;
    private Component.ComponentType ram;
    private Component.ComponentType motherboard;
    private Component.ComponentType psu;
    private Component.ComponentType aCase;
    private Component.ComponentType hdd;
    private Component.ComponentType ssd;
    private Component.ComponentType keyboard;
    private Component.ComponentType touchpad;
    private Component.ComponentType screen;
    private Component.ComponentType battery;

    public enum ComputerType {
        Desktop, LAPTOP
    }

    public enum UseCase {
        WORKSTATION, GAMING
    }




}
