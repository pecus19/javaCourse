package ee.taltech.iti0202.computerbuilder;

import ee.taltech.iti0202.computerbuilder.components.Component;
import ee.taltech.iti0202.computerbuilder.computer.Computer;
import ee.taltech.iti0202.computerbuilder.customer.Customer;
import ee.taltech.iti0202.computerbuilder.database.Database;
import ee.taltech.iti0202.computerbuilder.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerbuilder.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductAlreadyExistsException;
import ee.taltech.iti0202.computerbuilder.exceptions.ProductNotFoundException;
import ee.taltech.iti0202.computerbuilder.order.Order;
import ee.taltech.iti0202.computerbuilder.store.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ComputerStoreTest {
    @Test
    protected void buyBasicLaptopTest() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException {
        Database database = Database.getInstance();
        Store store = new Store("Store", new BigDecimal(100), new BigDecimal(123));
        Customer customer = new Customer("Danila", new BigDecimal(1000));
        Order order = new Order(100, Computer.UseCase.WORKSTATION, Computer.ComputerType.LAPTOP);
        Component component = new Component("component1", Component.ComponentType.KEYBOARD, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("component2", Component.ComponentType.TOUCHPAD, new BigDecimal(34), "MSI",
                3, 100);
        Component component3 = new Component("component3", Component.ComponentType.SCREEN, new BigDecimal(12), "MSI",
                5, 50);
        Component component4 = new Component("component4", Component.ComponentType.BATTERY, new BigDecimal(24), "MSI",
                8, 70);
        Component component5 = new Component("component5", Component.ComponentType.KEYBOARD, new BigDecimal(22), "MSI",
                66, 200);
        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        Computer laptop1 = store.assembleComputer(order, customer);
        Assertions.assertSame(component5, laptop1.getKeyboard());
        Assertions.assertSame(component2, laptop1.getTouchpad());
        Assertions.assertSame(component3, laptop1.getScreen());
        Assertions.assertSame(component5, laptop1.getKeyboard());
    }

    @Test
    protected void theDifferenceBetweenLaptopsIsMinimalTest() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException {
        Database database = Database.getInstance();
        Store store = new Store("Store", new BigDecimal(100), new BigDecimal(123));
        Customer customer = new Customer("Danila", new BigDecimal(1000));
        Order order = new Order(1040, Computer.UseCase.GAMING, Computer.ComputerType.LAPTOP);
        Component component = new Component("component1", Component.ComponentType.KEYBOARD, new BigDecimal(42), "MSI",
                65, 200);
        Component component2 = new Component("component2", Component.ComponentType.TOUCHPAD, new BigDecimal(0), "MSI",
                0, 100);
        Component component3 = new Component("component3", Component.ComponentType.SCREEN, new BigDecimal(0), "MSI",
                0, 50);
        Component component4 = new Component("component4", Component.ComponentType.BATTERY, new BigDecimal(0), "MSI",
                0, 70);
        Component component5 = new Component("component5", Component.ComponentType.KEYBOARD, new BigDecimal(42), "MSI",
                66, 200);
        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        Computer laptop1 = store.assembleComputer(order, customer);
        Assertions.assertSame(component5, laptop1.getKeyboard());
        Assertions.assertSame(component2, laptop1.getTouchpad());
        Assertions.assertSame(component3, laptop1.getScreen());
        Assertions.assertSame(component5, laptop1.getKeyboard());
    }

    @Test
    protected void checkMoneyAfterBuyingTest() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException {
        Database database = Database.getInstance();
        Store store = new Store("Store", new BigDecimal(100), new BigDecimal(123));
        Customer customer = new Customer("Danila", new BigDecimal(1000));
        Order order = new Order(100, Computer.UseCase.GAMING, Computer.ComputerType.LAPTOP);
        Component component = new Component("component1", Component.ComponentType.KEYBOARD, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("component2", Component.ComponentType.TOUCHPAD, new BigDecimal(34), "MSI",
                3, 100);
        Component component3 = new Component("component3", Component.ComponentType.SCREEN, new BigDecimal(12), "MSI",
                5, 50);
        Component component4 = new Component("component4", Component.ComponentType.BATTERY, new BigDecimal(24), "MSI",
                8, 70);
        Component component5 = new Component("component5", Component.ComponentType.KEYBOARD, new BigDecimal(22), "MSI",
                66, 200);
        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        int priceForAssembleLaptop = 15;
        int moneyBeforeBuying = store.getBalance().intValue();
        Computer laptop = store.assembleComputer(order, customer);
        Assertions.assertEquals(laptop.getPrice(), store.getBalance().intValueExact() - moneyBeforeBuying
                - priceForAssembleLaptop);

    }

    //Desktop Computer Tests
//    * p-graphicsCard
//     * q-ram
//     * r-motherboard
//     * m-hdd
//     * h-psuList
//     * s-case1
//     * c-cpu
    @Test
    protected void basicDesktopComputerHDDTest() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException {
        Database database = Database.getInstance();
        Store store = new Store("Store", new BigDecimal(100), new BigDecimal(123));
        Customer customer = new Customer("Danila", new BigDecimal(1000));
        Order order = new Order(101330, Computer.UseCase.GAMING, Computer.ComputerType.Desktop);
        Component component = new Component("graphicsCard", Component.ComponentType.GPU, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);
        Component component5 = new Component("case", Component.ComponentType.CASE, new BigDecimal(22), "MSI",
                66, 204);
        Component component6 = new Component("cpu", Component.ComponentType.CPU, new BigDecimal(22), "MSI",
                66, 205);
        Component component7 = new Component("motherboard", Component.ComponentType.MOTHERBOARD,
                new BigDecimal(22), "MSI",
                66, 206);
        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        database.saveComponent(component6);
        database.saveComponent(component7);
        Computer computer = store.assembleComputer(order, customer);
//        assertSame(component, computer.getGpu());
//        assertSame(component2, computer.getRam());
//        assertSame(component3, computer.getHdd());
//        assertSame(component4, computer.getPsu());
//        assertSame(component5, computer.getaCase());
//        assertSame(component6, computer.getCpu());
        Assertions.assertSame(component7, computer.getMotherboard());
    }

    @Test
    protected void choosesAMorePowerfulCpuTest() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException {
        Database database = Database.getInstance();
        Store store = new Store("Store", new BigDecimal(100), new BigDecimal(123));
        Customer customer = new Customer("Danila", new BigDecimal(1000));
        Order order5 = new Order(10133, Computer.UseCase.GAMING, Computer.ComputerType.Desktop);
        Component component = new Component("graphicsCard", Component.ComponentType.GPU, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);
        Component component5 = new Component("case", Component.ComponentType.CASE, new BigDecimal(22), "MSI",
                66, 204);
        Component component6 = new Component("cpu", Component.ComponentType.CPU, new BigDecimal(22), "MSI",
                66, 205);
        Component component8 = new Component("cpu", Component.ComponentType.CPU, new BigDecimal(22), "MSI",
                67, 205);
        Component component7 = new Component("motherboard", Component.ComponentType.MOTHERBOARD,
                new BigDecimal(22), "MSI",
                66, 206);
        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        database.saveComponent(component6);
        database.saveComponent(component7);
        database.saveComponent(component8);
        Computer computer = store.assembleComputer(order5, customer);
//        assertSame(component, computer.getGpu());
//        assertSame(component2, computer.getRam());
//        assertSame(component3, computer.getHdd());
//        assertSame(component4, computer.getPsu());
//        assertSame(component5, computer.getaCase());
//        assertSame(component8, computer.getCpu());
        Assertions.assertSame(component7, computer.getMotherboard());
    }

    @Test
    protected void basicDesktopComputerSSDTest() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException {
        Database database = Database.getInstance();
        Store store = new Store("Store", new BigDecimal(100), new BigDecimal(123));
        Customer customer = new Customer("Danila", new BigDecimal(1000));
        Order order = new Order(101330, Computer.UseCase.GAMING, Computer.ComputerType.Desktop);
        Component component = new Component("graphicsCard", Component.ComponentType.GPU, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("ssd", Component.ComponentType.SSD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 12293);
        Component component5 = new Component("case", Component.ComponentType.CASE, new BigDecimal(22), "MSI",
                66, 204);
        Component component6 = new Component("cpu", Component.ComponentType.CPU, new BigDecimal(22), "MSI",
                66, 205);
        Component component7 = new Component("motherboard", Component.ComponentType.MOTHERBOARD,
                new BigDecimal(22), "MSI",
                66, 206);
        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        database.saveComponent(component6);
        database.saveComponent(component7);
        Computer computer = store.assembleComputer(order, customer);
//        assertSame(component, computer.getGpu());
//        assertSame(component2, computer.getRam());
//        assertSame(component3, computer.getSsd());
//        assertSame(component4, computer.getPsu());
//        assertSame(component5, computer.getaCase());
//        assertSame(component6, computer.getCpu());
        Assertions.assertSame(component7, computer.getMotherboard());
    }

    @Test
    protected void basicChooseBetweenSsdAndHddTest() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException {
        Database database = Database.getInstance();
        Store store = new Store("Store", new BigDecimal(100), new BigDecimal(123));
        Customer customer = new Customer("Danila", new BigDecimal(1000));
        Order order = new Order(101330, Computer.UseCase.GAMING, Computer.ComputerType.Desktop);
        Component component = new Component("graphicsCard", Component.ComponentType.GPU, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("ssd", Component.ComponentType.SSD, new BigDecimal(12), "MSI",
                6, 202);
        Component component8 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);
        Component component5 = new Component("case", Component.ComponentType.CASE, new BigDecimal(22), "MSI",
                66, 204);
        Component component6 = new Component("cpu", Component.ComponentType.CPU, new BigDecimal(22), "MSI",
                66, 205);
        Component component7 = new Component("motherboard", Component.ComponentType.MOTHERBOARD,
                new BigDecimal(22), "MSI",
                66, 206);
        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        database.saveComponent(component6);
        database.saveComponent(component7);
        database.saveComponent(component8);
        Computer computer = store.assembleComputer(order, customer);
//        assertSame(component, computer.getGpu());
//        assertSame(component2, computer.getRam());
//        assertSame(component3, computer.getSsd());
//        assertSame(component4, computer.getPsu());
//        assertSame(component5, computer.getaCase());
//        assertSame(component6, computer.getCpu());
        Assertions.assertSame(component7, computer.getMotherboard());
    }

    @Test
    protected void missingOneComponentTest() throws ProductAlreadyExistsException, OutOfStockException {
        Database database = Database.getInstance();
        Store store = new Store("Store", new BigDecimal(100), new BigDecimal(123));
        Customer customer = new Customer("Danila", new BigDecimal(1000));
        Order order = new Order(101330, Computer.UseCase.GAMING, Computer.ComputerType.Desktop);
        Component component = new Component("graphicsCard", Component.ComponentType.GPU, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);
        Component component5 = new Component("case", Component.ComponentType.CASE, new BigDecimal(22), "MSI",
                66, 204);
        Component component6 = new Component("cpu", Component.ComponentType.CPU, new BigDecimal(22), "MSI",
                66, 205);
        Component component7 = new Component("motherboard", Component.ComponentType.MOTHERBOARD,
                new BigDecimal(22), "MSI",
                66, 206);
        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component4);
        database.saveComponent(component5);
        database.saveComponent(component6);
        database.saveComponent(component7);
        try {
            store.assembleComputer(order, customer);
        } catch (ProductNotFoundException ex) {
            Assertions.assertEquals("We don't have all components in the store or this price is too "
                    + "small to create a computer!", ex.getMessage());
        }
    }

    @Test
    protected void noBudgetAmountTest() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException {
        Database database = Database.getInstance();
        Store store = new Store("Store", new BigDecimal(100), new BigDecimal(123));
        Customer customer = new Customer("Danila", new BigDecimal(1000));
        Order order = new Order(null, Computer.UseCase.GAMING, Computer.ComputerType.Desktop);
        Component component = new Component("graphicsCard", Component.ComponentType.GPU, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("ssd", Component.ComponentType.SSD, new BigDecimal(12), "MSI",
                6, 202);
        Component component8 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);
        Component component5 = new Component("case", Component.ComponentType.CASE, new BigDecimal(22), "MSI",
                66, 204);
        Component component6 = new Component("cpu", Component.ComponentType.CPU, new BigDecimal(22), "MSI",
                66, 205);
        Component component7 = new Component("motherboard", Component.ComponentType.MOTHERBOARD,
                new BigDecimal(22), "MSI",
                66, 206);
        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        database.saveComponent(component6);
        database.saveComponent(component7);
        database.saveComponent(component8);
        Computer computer = store.assembleComputer(order, customer);
        Assertions.assertSame(component, computer.getGpu());
        Assertions.assertSame(component2, computer.getRam());
        Assertions.assertSame(component3, computer.getSsd());
        Assertions.assertSame(component4, computer.getPsu());
        Assertions.assertSame(component5, computer.getaCase());
        Assertions.assertSame(component6, computer.getCpu());
        Assertions.assertSame(component7, computer.getMotherboard());
    }

    @Test
    protected void noUseCaseSpecified() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException {
        Database database = Database.getInstance();
        Store store = new Store("Store", new BigDecimal(100), new BigDecimal(123));
        Customer customer = new Customer("Danila", new BigDecimal(1000));
        Order order = new Order(null, null, Computer.ComputerType.Desktop);
        Component component = new Component("graphicsCard", Component.ComponentType.GPU, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("ssd", Component.ComponentType.SSD, new BigDecimal(12), "MSI",
                6, 202);
        Component component8 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);
        Component component5 = new Component("case", Component.ComponentType.CASE, new BigDecimal(22), "MSI",
                66, 204);
        Component component6 = new Component("cpu", Component.ComponentType.CPU, new BigDecimal(22), "MSI",
                66, 205);
        Component component7 = new Component("motherboard", Component.ComponentType.MOTHERBOARD,
                new BigDecimal(22), "MSI",
                66, 206);
        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        database.saveComponent(component6);
        database.saveComponent(component7);
        database.saveComponent(component8);
        Computer computer = store.assembleComputer(order, customer);
        Assertions.assertSame(component, computer.getGpu());
        Assertions.assertSame(component2, computer.getRam());
        Assertions.assertSame(component3, computer.getSsd());
        Assertions.assertSame(component4, computer.getPsu());
        Assertions.assertSame(component5, computer.getaCase());
        Assertions.assertSame(component6, computer.getCpu());
        Assertions.assertSame(component7, computer.getMotherboard());
    }

    @Test
    protected void checkAfterBuyingTest() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException {
        Database database = Database.getInstance();
        Store store = new Store("Store", new BigDecimal(100), new BigDecimal(123));
        Customer customer = new Customer("Danila", new BigDecimal(1000));
        Order order = new Order(null, Computer.UseCase.WORKSTATION, Computer.ComputerType.Desktop);
        Component component = new Component("graphicsCard", Component.ComponentType.GPU, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("ssd", Component.ComponentType.SSD, new BigDecimal(12), "MSI",
                6, 202);
        Component component8 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);
        Component component5 = new Component("case", Component.ComponentType.CASE, new BigDecimal(22), "MSI",
                66, 204);
        Component component6 = new Component("cpu", Component.ComponentType.CPU, new BigDecimal(22), "MSI",
                66, 205);
        Component component7 = new Component("motherboard", Component.ComponentType.MOTHERBOARD,
                new BigDecimal(22), "MSI",
                66, 206);
        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        database.saveComponent(component6);
        database.saveComponent(component7);
        database.saveComponent(component8);
        int checkValance = store.getBalance().intValue() + 194;
        store.assembleComputer(order, customer);
        Assertions.assertEquals(checkValance, store.getBalance().intValue());
    }

    @Test
    protected void customerBoughtTwoComputersTest() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException {
        Database database = Database.getInstance();
        Store store = new Store("Store", new BigDecimal(100), new BigDecimal(123));
        Customer customer = new Customer("Danila", new BigDecimal(1000));
        Order order = new Order(null, Computer.UseCase.WORKSTATION, Computer.ComputerType.Desktop);
        Order order2 = new Order(null, Computer.UseCase.GAMING, Computer.ComputerType.LAPTOP);
        Component component = new Component("graphicsCard", Component.ComponentType.GPU, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("ssd", Component.ComponentType.SSD, new BigDecimal(12), "MSI",
                6, 202);
        Component component8 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);
        Component component5 = new Component("case", Component.ComponentType.CASE, new BigDecimal(22), "MSI",
                66, 204);
        Component component6 = new Component("cpu", Component.ComponentType.CPU, new BigDecimal(22), "MSI",
                66, 205);
        Component component7 = new Component("motherboard", Component.ComponentType.MOTHERBOARD,
                new BigDecimal(22), "MSI",
                66, 206);
        Component component13 = new Component("component1", Component.ComponentType.KEYBOARD,
                new BigDecimal(43), "MSI",
                1, 200);
        Component component9 = new Component("component2", Component.ComponentType.TOUCHPAD,
                new BigDecimal(34), "MSI",
                3, 100);
        Component component10 = new Component("component3", Component.ComponentType.SCREEN,
                new BigDecimal(12), "MSI",
                5, 50);
        Component component11 = new Component("component4", Component.ComponentType.BATTERY,
                new BigDecimal(24), "MSI",
                8, 70);
        Component component12 = new Component("component5", Component.ComponentType.KEYBOARD,
                new BigDecimal(22), "MSI",
                66, 200);
        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        database.saveComponent(component6);
        database.saveComponent(component7);
        database.saveComponent(component8);
        database.saveComponent(component9);
        database.saveComponent(component10);
        database.saveComponent(component11);
        database.saveComponent(component12);
        database.saveComponent(component13);
        store.assembleComputer(order, customer);
        store.assembleComputer(order2, customer);
        Assertions.assertEquals(2, customer.getComputers().size());
    }

    @Test
    protected void canNotUseComponentsForSecondOrderTest() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException {
        Database database = Database.getInstance();
        Store store = new Store("Store", new BigDecimal(100), new BigDecimal(123));
        Customer customer = new Customer("Danila", new BigDecimal(1000));
        Order order = new Order(null, Computer.UseCase.WORKSTATION, Computer.ComputerType.Desktop);
        Order order2 = new Order(null, Computer.UseCase.GAMING, Computer.ComputerType.Desktop);
        Component component = new Component("graphicsCard", Component.ComponentType.GPU, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("ssd", Component.ComponentType.SSD, new BigDecimal(12), "MSI",
                6, 202);
        Component component8 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);
        Component component5 = new Component("case", Component.ComponentType.CASE, new BigDecimal(22), "MSI",
                66, 204);
        Component component6 = new Component("cpu", Component.ComponentType.CPU, new BigDecimal(22), "MSI",
                66, 205);
        Component component7 = new Component("motherboard", Component.ComponentType.MOTHERBOARD,
                new BigDecimal(22), "MSI",
                66, 206);
        Component component9 = new Component("ssd", Component.ComponentType.SSD, new BigDecimal(12), "MSI",
                6, 202);
        Component component10 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component11 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);
        Component component12 = new Component("case", Component.ComponentType.CASE, new BigDecimal(22), "MSI",
                66, 204);
        Component component13 = new Component("cpu", Component.ComponentType.CPU, new BigDecimal(22), "MSI",
                66, 205);
        Component component14 = new Component("motherboard", Component.ComponentType.MOTHERBOARD,
                new BigDecimal(22), "MSI",
                66, 206);

        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component5);
        database.saveComponent(component6);
        database.saveComponent(component7);
        database.saveComponent(component8);
        database.saveComponent(component9);
        database.saveComponent(component10);
        database.saveComponent(component11);
        database.saveComponent(component12);
        database.saveComponent(component13);
        database.saveComponent(component14);
        store.assembleComputer(order, customer);
        try {
            store.assembleComputer(order2, customer);
        } catch (ProductNotFoundException ex) {
            Assertions.assertEquals("We don't have all components in the store or this price is too "
                    + "small to create a computer!", ex.getMessage());
        }
    }

    @Test
    protected void buyComponentsSeparatelyTest() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException, NotEnoughMoneyException {
        Database database = Database.getInstance();
        Store store4 = new Store("Store", new BigDecimal(1), new BigDecimal(1));
        Customer customer = new Customer("Danila", new BigDecimal(100));
        Component component = new Component("graphicsCard", Component.ComponentType.GPU, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("ssd", Component.ComponentType.SSD, new BigDecimal(12), "MSI",
                6, 202);
        Component component8 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);

        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component8);
        database.increaseComponentStock(component.getId(), 2);
        int balanceBefore = store4.getBalance().intValue();
        store4.purchaseComponent(component.getId(), customer);
        Assertions.assertEquals(store4.getBalance().intValue(), balanceBefore + component.getPrice().intValue());
    }

    @Test
    protected void buyComponentsSeparatelySecondTimeAndProductOutOfStockTest() throws ProductAlreadyExistsException,
            OutOfStockException,
            ProductNotFoundException, NotEnoughMoneyException {
        Database database = Database.getInstance();
        Store store4 = new Store("Store", new BigDecimal(1), new BigDecimal(1));
        Customer customer = new Customer("Danila", new BigDecimal(100));
        Component component = new Component("graphicsCard", Component.ComponentType.GPU,
                new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("ssd", Component.ComponentType.SSD, new BigDecimal(12), "MSI",
                6, 202);
        Component component8 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);

        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component8);
        store4.purchaseComponent(component.getId(), customer);
        try {
            store4.purchaseComponent(component.getId(), customer);
        } catch (OutOfStockException ex) {
            Assertions.assertEquals("The Component is out of stock!", ex.getMessage());
        }
    }

    @Test
    protected void customerCheckAllComponentTest() throws ProductAlreadyExistsException, OutOfStockException,
            ProductNotFoundException, NotEnoughMoneyException {
        Database database = Database.getInstance();
        Store store4 = new Store("Store", new BigDecimal(1), new BigDecimal(1));
        Customer customer = new Customer("Danila", new BigDecimal(1100));
        Component component = new Component("graphicsCard", Component.ComponentType.GPU, new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("ssd", Component.ComponentType.SSD, new BigDecimal(12), "MSI",
                6, 202);
        Component component8 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);

        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component8);
        store4.purchaseComponent(component.getId(), customer);
        store4.purchaseComponent(component2.getId(), customer);
        store4.purchaseComponent(component3.getId(), customer);
        Assertions.assertEquals(3, customer.getComponents().size());
    }

    @Test
    protected void resetDatabaseTest() throws ProductAlreadyExistsException {
        Database database = Database.getInstance();
        Store store4 = new Store("Store", new BigDecimal(1), new BigDecimal(1));
        Customer customer = new Customer("Danila", new BigDecimal(1100));
        Component component = new Component("graphicsCard", Component.ComponentType.GPU,
                new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("ssd", Component.ComponentType.SSD, new BigDecimal(12), "MSI",
                6, 202);
        Component component8 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);

        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component8);
        database.resetEntireDatabase();
        Assertions.assertEquals(0, database.getComponents().size());
    }

    @Test
    protected void sortByDifferentTypesTest() throws ProductAlreadyExistsException, ProductNotFoundException,
            OutOfStockException {
        Database database = Database.getInstance();
        Store store4 = new Store("Store", new BigDecimal(1), new BigDecimal(1));
        Customer customer = new Customer("Danila", new BigDecimal(1100));
        Component component = new Component("graphicsCard", Component.ComponentType.GPU,
                new BigDecimal(43), "MSI",
                1, 200);
        Component component2 = new Component("ram", Component.ComponentType.RAM, new BigDecimal(34), "MSI",
                3, 201);
        Component component3 = new Component("Assd", Component.ComponentType.SSD, new BigDecimal(11), "MSI",
                6, 202);
        Component component8 = new Component("hdd", Component.ComponentType.HDD, new BigDecimal(12), "MSI",
                5, 202);
        Component component4 = new Component("psu", Component.ComponentType.PSU, new BigDecimal(24), "MSI",
                3, 1223);

        database.saveComponent(component);
        database.saveComponent(component2);
        database.saveComponent(component3);
        database.saveComponent(component4);
        database.saveComponent(component8);
        database.increaseComponentStock(component.getId(), 4);
        database.increaseComponentStock(component2.getId(), 1);
        database.decreaseComponentStock(component.getId(), 1);
        Assertions.assertEquals(component, store4.getComponentsSortedByAmount()
                .get(store4.getComponentsSortedByAmount().size() - 1));
        Assertions.assertEquals("Assd", store4.getComponentsSortedByName()
                .get(0).getName());
        Assertions.assertSame(11, store4.getComponentsSortedByPrice()
                .get(0).getPrice().intValue());
        try {
            store4.setProfitMargin(BigDecimal.valueOf(0));
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("assertThrowsException", ex.getMessage());
        }
        store4.setProfitMargin(BigDecimal.valueOf(1));
        Assertions.assertEquals(1, store4.getProfitMargin().intValue());
        try {
            database.saveComponent(component);
        } catch (ProductAlreadyExistsException ex) {
            Assertions.assertEquals("Product already exists exception!", ex.getMessage());
        }
        try {
            store4.purchaseComponent(322, customer);
        } catch (ProductNotFoundException | NotEnoughMoneyException ex) {
            Assertions.assertEquals("We don't have all components in the store or this price is too small to "
                    + "create a computer!", ex.getMessage());
        }
        Assertions.assertEquals(287, store4.getInventoryValue().intValue());
        database.deleteComponent(0);
        try {
            database.deleteComponent(0);
        } catch (ProductNotFoundException ex) {
            Assertions.assertEquals("We don't have all components in the store or this price is too small to "
                    + "create a computer!", ex.getMessage());
        }
        try {
            database.decreaseComponentStock(0, 1);
        } catch (ProductNotFoundException ex) {
            Assertions.assertEquals("We don't have all components in the store or this price is too small to "
                    + "create a computer!", ex.getMessage());
        }
    }
}
