package ee.taltech.iti0202.computerbuilder.computer;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.store.Store;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DesktopComputerSSD extends Computer {
    private List<Component> processor;
    private List<Component> graphicsCard;
    private List<Component> ram;
    private List<Component> motherboard;
    private List<Component> ssd;
    private List<Component> psuList;
    private List<Component> case1;


    public DesktopComputerSSD(Component ssd, Component cpu, Component gpu, Component ram,
                              Component motherboard, Component psu, Component aCase) {
        super.cpu = cpu;
        super.gpu = gpu;
        super.ram = ram;
        super.motherboard = motherboard;
        super.ssd = ssd;
        super.psu = psu;
        super.aCase = aCase;
    }

    public DesktopComputerSSD() {
    }

    @Override
    public DesktopComputerSSD getComputersWithTheRightPrice(Store store, double budget, Computer.UseCase useCase) {
        List<DesktopComputerSSD> output;
        if (isEnoughComponents(store)) {
            output = findComputersWithRightPrice(budget, useCase);
            logger.info(String.format("We found %s computer with SSD that are less than your price and "
                    + "are trying to fount the "
                    + "best...", output.size()));
            return output.size() != 0 ? sortAssembleLaptops(output) : null;
        }
        return null;
    }

    private List<DesktopComputerSSD> findComputersWithRightPrice(double budget, Computer.UseCase useCase) {
        return ssd.stream().flatMap(p -> processor.stream().flatMap(g -> graphicsCard.stream()
                .flatMap(r -> ram.stream().flatMap(m -> motherboard.stream().flatMap(h -> psuList.stream()
                        .flatMap(s -> case1.stream().filter(c -> p.getPrice().intValue() + g.getPrice().intValue()
                                        + r.getPrice().intValue() + m.getPrice().intValue() + h.getPrice().intValue()
                                        + s.getPrice().intValue() + c.getPrice().intValue() <= budget)
                                .filter(c -> p.getPowerConsumption() + g.getPowerConsumption()
                                        + r.getPowerConsumption() + m.getPowerConsumption() + h.getPowerConsumption()
                                        + c.getPowerConsumption() <= s.getPowerConsumption()).map(c -> {
                                    DesktopComputerSSD computerSSD = new DesktopComputerSSD(p, g, r, m, h, s, c);
                                    computerSSD.setPrice(p.getPrice().intValue() + g.getPrice().intValue()
                                            + r.getPrice().intValue()
                                            + m.getPrice().intValue() + h.getPrice().intValue()
                                            + s.getPrice().intValue() + c.getPrice().intValue());
                                    computerSSD.setTotalPoints(g.getPerformancePoints()
                                            + r.getPerformancePoints() + m.getPerformancePoints()
                                            + h.getPerformancePoints() + s.getPerformancePoints());
                                    findBestComputerAccordingUseCase(useCase, c.getPerformancePoints(),
                                            p.getPerformancePoints());
                                    return computerSSD;
                                }))))))).collect(Collectors.toList());
    }

    public DesktopComputerSSD sortAssembleLaptops(List<DesktopComputerSSD> list) {
        logger.info("------------------------Sorted assembled laptops with SSD------------------------");
        List<DesktopComputerSSD> output = list.stream().sorted(Comparator.comparing(DesktopComputerSSD::getTotalPoints)
                .thenComparing(DesktopComputerSSD::getPrice).reversed()).toList();
        for (int i = 0; i < output.size(); i++) {
            logger.info(output.get(i).toString());
        }
        return output.size() != 0 ? output.get(0) : null;

    }

    @Override
    public Boolean isEnoughComponents(Store store) {
        if (store.filterByType(Component.ComponentType.CPU).size() != 0
                & store.filterByType(Component.ComponentType.GPU).size() != 0
                & store.filterByType(Component.ComponentType.RAM).size() != 0
                & store.filterByType(Component.ComponentType.MOTHERBOARD).size() != 0
                & store.filterByType(Component.ComponentType.SSD).size() != 0
                & store.filterByType(Component.ComponentType.PSU).size() != 0
                & store.filterByType(Component.ComponentType.CASE).size() != 0) {
            this.processor = store.filterByType(Component.ComponentType.CPU);
            this.graphicsCard = store.filterByType(Component.ComponentType.GPU);
            this.ram = store.filterByType(Component.ComponentType.RAM);
            this.motherboard = store.filterByType(Component.ComponentType.MOTHERBOARD);
            this.ssd = store.filterByType(Component.ComponentType.SSD);
            this.psuList = store.filterByType(Component.ComponentType.PSU);
            this.case1 = store.filterByType(Component.ComponentType.CASE);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void priceCalculation(Computer computer, Store store) throws OutOfStockException, ProductNotFoundException {
        int finalPrice = computer.getCpu().getPrice().intValue() + computer.getGpu().getPrice().intValue()
                + computer.getRam().getPrice().intValue() + computer.getMotherboard().getPrice().intValue()
                + computer.getSsd().getPrice().intValue() + computer.getPsu().getPrice().intValue()
                + computer.getaCase().getPrice().intValue()
                + ASSEMBLY_PRICE;
        setPrice(finalPrice);
        store.setBalance(store.getBalance().add(BigDecimal.valueOf(finalPrice)));
        store.getDatabase().decreaseComponentStock(computer.getCpu().getId(), 1);
        store.getDatabase().decreaseComponentStock(computer.getGpu().getId(), 1);
        store.getDatabase().decreaseComponentStock(computer.getRam().getId(), 1);
        store.getDatabase().decreaseComponentStock(computer.getMotherboard().getId(), 1);
        store.getDatabase().decreaseComponentStock(computer.getSsd().getId(), 1);
        store.getDatabase().decreaseComponentStock(computer.getPsu().getId(), 1);
        store.getDatabase().decreaseComponentStock(computer.getaCase().getId(), 1);

    }

    @Override
    public DesktopComputerSSD assembleLaptop(Store store, double budget, Computer.UseCase useCase) {
        return getComputersWithTheRightPrice(store, budget, useCase);
    }
}
