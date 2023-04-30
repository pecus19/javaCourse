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

    /**
     * p-graphicsCard
     * q-ram
     * r-motherboard
     * m-hdd
     * h-psuList
     * s-case1
     * c-cpu
     */

    public DesktopComputerSSD getComputersWithTheRightPriceSSD(Store store, double budget, Computer.UseCase useCase)
            throws OutOfStockException, ProductNotFoundException {
        List<Component> processor = store.filterByType(Component.ComponentType.CPU);
        List<Component> graphicsCard = store.filterByType(Component.ComponentType.GPU);
        List<Component> ram = store.filterByType(Component.ComponentType.RAM);
        List<Component> motherboard = store.filterByType(Component.ComponentType.MOTHERBOARD);
        List<Component> ssd = store.filterByType(Component.ComponentType.SSD);
        List<Component> psuList = store.filterByType(Component.ComponentType.PSU);
        List<Component> case1 = store.filterByType(Component.ComponentType.CASE);
        List<DesktopComputerSSD> output = processor.stream().flatMap(p -> graphicsCard.stream().flatMap(g -> ram.stream()
                .flatMap(r -> motherboard.stream().flatMap(m -> ssd.stream().flatMap(h -> psuList.stream()
                        .flatMap(s -> case1.stream().filter(c -> p.getPrice().intValue() + g.getPrice().intValue()
                                        + r.getPrice().intValue() + m.getPrice().intValue() + h.getPrice().intValue()
                                        + s.getPrice().intValue() + c.getPrice().intValue() <= budget)
                                .filter(c -> p.getPrice().intValue() + g.getPrice().intValue()
                                        + r.getPrice().intValue() + m.getPrice().intValue() + h.getPrice().intValue()
                                        + s.getPrice().intValue() + c.getPrice().intValue() <= psu.getPowerConsumption())
                                .map(c -> {
                                    DesktopComputerSSD computerSSD = new DesktopComputerSSD(p, g, r, m, h, s, c);
                                    computerSSD.setPrice(p.getPrice().intValue() + g.getPrice().intValue() + r.getPrice().intValue()
                                            + m.getPrice().intValue() + h.getPrice().intValue() + s.getPrice().intValue()
                                            + c.getPrice().intValue());
                                    computerSSD.setTotalPoints(g.getPerformancePoints()
                                            + r.getPerformancePoints() + m.getPerformancePoints() + h.getPerformancePoints()
                                            + s.getPerformancePoints());
                                    findBestLaptopAccordingUseCase(useCase, c.getPerformancePoints(), p.getPerformancePoints());
                                    return computerSSD;
                                }))))))).collect(Collectors.toList());

        logger.info(String.format("We found %s laptops with SSD that are less than your price and are trying to fount the "
                + "best...", output.size()));
        return output.size() != 0 ? sortAssembleLaptops(output, useCase, store) : null;


    }

    public DesktopComputerSSD sortAssembleLaptops(List<DesktopComputerSSD> list, Computer.UseCase useCase, Store store) throws OutOfStockException, ProductNotFoundException {
        logger.info("------------------------Sorted assembled laptops with SSD------------------------");
        List<DesktopComputerSSD> output = list.stream().sorted(Comparator.comparing(DesktopComputerSSD::getTotalPoints)
                .thenComparing(DesktopComputerSSD::getPrice).reversed()).toList();
        for (int i = 0; i < output.size(); i++) {
            logger.info(output.get(i).toString());
        }
        priceCalculation(list.get(0), store);
        return output.size() != 0 ? output.get(0) : null;

    }

    public void priceCalculation(DesktopComputerSSD computer, Store store) throws OutOfStockException, ProductNotFoundException {
        int finalPrice = computer.getCpu().getPrice().intValue() + computer.getGpu().getPrice().intValue()
                + computer.getRam().getPrice().intValue() + computer.getMotherboard().getPrice().intValue()
                + computer.getHdd().getPrice().intValue() + computer.getPsu().getPrice().intValue()
                + computer.getaCase().getPrice().intValue()
                + ASSEMBLY_PRICE;
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

    public void findBestLaptopAccordingUseCase(Computer.UseCase useCase, int gpu,
                                               int processor) {
        if (useCase != null) {
            double sum = useCase.equals(UseCase.GAMING) ? gpu * 1.5 + processor : gpu + processor * 1.5;
            setTotalPoints(getTotalPoints() + sum);
        } else {
            setTotalPoints(gpu + processor);
        }
    }


    @Override
    public DesktopComputerSSD assembleLaptop(Store store, double budget, Computer.UseCase useCase) throws OutOfStockException, ProductNotFoundException {
        return getComputersWithTheRightPriceSSD(store, budget, useCase);
    }
}
