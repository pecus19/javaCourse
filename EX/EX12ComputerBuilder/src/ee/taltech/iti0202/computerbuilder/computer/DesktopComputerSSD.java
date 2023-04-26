package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;

public class DesktopComputerSSD extends Computer {
    private Component.ComponentType cpu;
    private Component.ComponentType gpu;
    private Component.ComponentType ram;
    private Component.ComponentType motherboard;
    private Component.ComponentType psu;
    private Component.ComponentType aCase;
    private Component.ComponentType ssd;

    public DesktopComputerSSD(Component.ComponentType ssd, Component.ComponentType cpu, Component.ComponentType gpu, Component.ComponentType ram,
                              Component.ComponentType motherboard, Component.ComponentType psu, Component.ComponentType aCase) {
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.motherboard = motherboard;
        this.ssd = ssd;
        this.psu = psu;
        this.aCase = aCase;
    }
}
