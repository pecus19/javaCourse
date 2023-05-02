package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.store.Store;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DesktopComputerHDD extends Computer {
    private List<Component> processor;
    private List<Component> graphicsCard;
    private List<Component> ram;
    private List<Component> motherboard;
    private List<Component> hdd;
    private List<Component> psuList;
    private List<Component> case1;

    public DesktopComputerHDD(Component hdd, Component cpu, Component gpu, Component ram,
                              Component motherboard, Component psu, Component aCase) {
        super.cpu = cpu;
        super.gpu = gpu;
        super.ram = ram;
        super.motherboard = motherboard;
        super.hdd = hdd;
        super.psu = psu;
        super.aCase = aCase;
    }

    public DesktopComputerHDD() {
    }

    @Override
    public Boolean isEnoughComponents(Store store) {
        if (store.filterByType(Component.ComponentType.CPU).size() != 0
                & store.filterByType(Component.ComponentType.GPU).size() != 0
                & store.filterByType(Component.ComponentType.RAM).size() != 0
                & store.filterByType(Component.ComponentType.MOTHERBOARD).size() != 0
                & store.filterByType(Component.ComponentType.HDD).size() != 0
                & store.filterByType(Component.ComponentType.PSU).size() != 0
                & store.filterByType(Component.ComponentType.CASE).size() != 0) {
            this.processor = store.filterByType(Component.ComponentType.CPU);
            this.graphicsCard = store.filterByType(Component.ComponentType.GPU);
            this.ram = store.filterByType(Component.ComponentType.RAM);
            this.motherboard = store.filterByType(Component.ComponentType.MOTHERBOARD);
            this.hdd = store.filterByType(Component.ComponentType.HDD);
            this.psuList = store.filterByType(Component.ComponentType.PSU);
            this.case1 = store.filterByType(Component.ComponentType.CASE);
            return true;
        } else {
            return false;
        }
    }

    public List<DesktopComputerHDD> findComputersWithRightPrice(double budget, Computer.UseCase useCase) {
        return hdd.stream().flatMap(p -> processor.stream().flatMap(g -> graphicsCard.stream()
                .flatMap(r -> ram.stream().flatMap(m -> motherboard.stream().flatMap(h -> psuList.stream()
                        .flatMap(s -> case1.stream().filter(c -> p.getPrice().intValue() + g.getPrice().intValue()
                                        + r.getPrice().intValue() + m.getPrice().intValue() + h.getPrice().intValue()
                                        + s.getPrice().intValue() + c.getPrice().intValue() <= budget)
                                .filter(c -> p.getPowerConsumption() + g.getPowerConsumption()
                                        + r.getPowerConsumption() + m.getPowerConsumption() + h.getPowerConsumption()
                                        + c.getPowerConsumption() <= s.getPowerConsumption()).map(c -> {
                                    DesktopComputerHDD computerHDD = new DesktopComputerHDD(p, g, r, m, h, s, c);
                                    computerHDD.setPrice(p.getPrice().intValue() + g.getPrice().intValue()
                                            + r.getPrice().intValue()
                                            + m.getPrice().intValue() + h.getPrice().intValue()
                                            + s.getPrice().intValue() + c.getPrice().intValue());
                                    computerHDD.setTotalPoints(g.getPerformancePoints()
                                            + r.getPerformancePoints() + m.getPerformancePoints()
                                            + h.getPerformancePoints() + s.getPerformancePoints());
                                    findBestComputerAccordingUseCase(useCase, c.getPerformancePoints(),
                                            p.getPerformancePoints());
                                    return computerHDD;
                                }))))))).collect(Collectors.toList());
    }

    @Override
    public DesktopComputerHDD getComputersWithTheRightPrice(Store store, double budget, Computer.UseCase useCase) {
        List<DesktopComputerHDD> output;
        if (isEnoughComponents(store)) {
            output = findComputersWithRightPrice(budget, useCase);
            logger.info(String.format("We found %s computer with HDD that are less than your price and are "
                    + "trying to fount the "
                    + "best...", output.size()));
            return output.size() != 0 ? sortAssembleLaptops(output) : null;
        }
        return null;
    }

    public DesktopComputerHDD sortAssembleLaptops(List<DesktopComputerHDD> list) {
        logger.info("------------------------Sorted assembled laptops with HDD------------------------");
        List<DesktopComputerHDD> output = list.stream().sorted(Comparator.comparing(DesktopComputerHDD::getTotalPoints)
                .thenComparing(DesktopComputerHDD::getPrice).reversed()).toList();
        for (int i = 0; i < output.size(); i++) {
            logger.info(output.get(i).toString());
        }
        return output.size() != 0 ? output.get(0) : null;

    }

    @Override
    public void priceCalculation(Computer computer, Store store) throws
            OutOfStockException, ProductNotFoundException {
        int finalPrice = computer.getCpu().getPrice().intValue() + computer.getGpu().getPrice().intValue()
                + computer.getRam().getPrice().intValue() + computer.getMotherboard().getPrice().intValue()
                + computer.getHdd().getPrice().intValue() + computer.getPsu().getPrice().intValue()
                + computer.getaCase().getPrice().intValue() + ASSEMBLY_PRICE;
        setPrice(finalPrice);
        store.setBalance(store.getBalance().add(BigDecimal.valueOf(finalPrice)));
        store.getDatabase().decreaseComponentStock(computer.getCpu().getId(), 1);
        store.getDatabase().decreaseComponentStock(computer.getGpu().getId(), 1);
        store.getDatabase().decreaseComponentStock(computer.getRam().getId(), 1);
        store.getDatabase().decreaseComponentStock(computer.getMotherboard().getId(), 1);
        store.getDatabase().decreaseComponentStock(computer.getHdd().getId(), 1);
        store.getDatabase().decreaseComponentStock(computer.getPsu().getId(), 1);
        store.getDatabase().decreaseComponentStock(computer.getaCase().getId(), 1);
    }

    @Override
    public DesktopComputerHDD assembleLaptop(Store store, double budget, Computer.UseCase useCase) {
        return getComputersWithTheRightPrice(store, budget, useCase);
    }
}
