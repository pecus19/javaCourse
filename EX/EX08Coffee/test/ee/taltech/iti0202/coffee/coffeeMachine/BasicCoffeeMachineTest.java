package ee.taltech.iti0202.coffee.coffeeMachine;

import ee.taltech.iti0202.coffee.CoffeeExceptions.CannotMakeACoffeeException;
import ee.taltech.iti0202.coffee.builder.BasicCoffeeMachineBuilder;
import ee.taltech.iti0202.coffee.drinks.Drinks;
import ee.taltech.iti0202.coffee.kitchen.Kitchen;
import ee.taltech.iti0202.coffee.waterTank.WaterTank;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BasicCoffeeMachineTest {


    @org.junit.jupiter.api.Test
    void makeCoffeeUsingBasicCoffeeMachineTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        BasicCoffeeMachine coffeeMachine = new BasicCoffeeMachineBuilder()
                .setAmountOfGrains(1000)
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .setAmountOfMilk(5000)
                .createBasicCoffeeMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        assertEquals(new Drinks(Drinks.Types.Cappuccino).getTypes(),
                kitchen.acceptOrder(coffeeMachine, Drinks.Types.Cappuccino).getTypes());
    }

    @org.junit.jupiter.api.Test
    void cannotMakeCoffeeUsingBasicCoffeeMachineDueToTrashTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        BasicCoffeeMachine coffeeMachine = new BasicCoffeeMachineBuilder()
                .setAmountOfGrains(1000)
                .setDrink(drinks)
                .setTrash(4)
                .setWaterTank(waterTank)
                .setAmountOfMilk(5000)
                .createBasicCoffeeMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        kitchen.acceptOrder(coffeeMachine, Drinks.Types.Cappuccino);
        try {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Latte);
        } catch (CannotMakeACoffeeException ex) {
            assertEquals(CannotMakeACoffeeException.Reason.TRASH_IS_FULL, ex.getReason());
        }
    }

    @org.junit.jupiter.api.Test
    void cannotMakeCoffeeAfterTrowAwayWasteAndMakeCoffeeTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        BasicCoffeeMachine coffeeMachine = new BasicCoffeeMachineBuilder()
                .setAmountOfGrains(1000)
                .setDrink(drinks)
                .setTrash(4)
                .setWaterTank(waterTank)
                .setAmountOfMilk(5000)
                .createBasicCoffeeMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        kitchen.acceptOrder(coffeeMachine, Drinks.Types.Cappuccino);
        try {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Latte);
        } catch (CannotMakeACoffeeException ex) {
            assertEquals(CannotMakeACoffeeException.Reason.TRASH_IS_FULL, ex.getReason());
        }
        coffeeMachine.throwAwayWaste();
        assertEquals(new Drinks(Drinks.Types.Mocha).getTypes(),
                kitchen.acceptOrder(coffeeMachine, Drinks.Types.Mocha).getTypes());
    }

    @org.junit.jupiter.api.Test
    void cannotMakeCoffeeUsingBasicCoffeeMachineDueToMilkTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        BasicCoffeeMachine coffeeMachine = new BasicCoffeeMachineBuilder()
                .setAmountOfGrains(1000)
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .setAmountOfMilk(200)
                .createBasicCoffeeMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        kitchen.acceptOrder(coffeeMachine, Drinks.Types.Cappuccino);
        assertEquals(50, coffeeMachine.getAmountOfMilk());
        try {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Latte);
        } catch (CannotMakeACoffeeException ex) {
            assertEquals(CannotMakeACoffeeException.Reason.RAN_OUT_OF_MILK, ex.getReason());
        }
    }

    @org.junit.jupiter.api.Test
    void cannotMakeCoffeeUsingBasicCoffeeMachineDueToWaterTankTest() {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        BasicCoffeeMachine coffeeMachine = new BasicCoffeeMachineBuilder()
                .setAmountOfGrains(1000)
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .setAmountOfMilk(500)
                .createBasicCoffeeMachine();
        waterTank.setAmountOfWater(50);
        kitchen.addCoffeeMachines(coffeeMachine);
        try {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Cappuccino);
        } catch (CannotMakeACoffeeException ex) {
            assertEquals(CannotMakeACoffeeException.Reason.NOT_WATER, ex.getReason());
        }
    }

    @org.junit.jupiter.api.Test
    void cannotMakeCoffeeUsingBasicCoffeeMachineDueToWaterTankAndTopUpTheTankTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        BasicCoffeeMachine coffeeMachine = new BasicCoffeeMachineBuilder()
                .setAmountOfGrains(1000)
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .setAmountOfMilk(500)
                .createBasicCoffeeMachine();
        waterTank.setAmountOfWater(50);
        kitchen.addCoffeeMachines(coffeeMachine);
        try {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Cappuccino);
        } catch (CannotMakeACoffeeException ex) {
            assertEquals(CannotMakeACoffeeException.Reason.NOT_WATER, ex.getReason());
        }
        waterTank.topUpWaterTank();
        assertEquals(new Drinks(Drinks.Types.Cappuccino).getTypes(),
                kitchen.acceptOrder(coffeeMachine, Drinks.Types.Cappuccino).getTypes());
    }

    @org.junit.jupiter.api.Test
    void cannotMakeCoffeeUsingBasicCoffeeMachineDueToGrainsTest() {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        BasicCoffeeMachine coffeeMachine = new BasicCoffeeMachineBuilder()
                .setAmountOfGrains(50)
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .setAmountOfMilk(500)
                .createBasicCoffeeMachine();
        waterTank.setAmountOfWater(50);
        kitchen.addCoffeeMachines(coffeeMachine);
        try {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Espresso);
        } catch (CannotMakeACoffeeException ex) {
            assertEquals(CannotMakeACoffeeException.Reason.NO_GRAINS, ex.getReason());
        }
    }

    @org.junit.jupiter.api.Test
    void cannotMakeCoffeeUsingBasicCoffeeMachineDueToGrainsAndAddGrainsTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        BasicCoffeeMachine coffeeMachine = new BasicCoffeeMachineBuilder()
                .setAmountOfGrains(50)
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .setAmountOfMilk(500)
                .createBasicCoffeeMachine();
        waterTank.setAmountOfWater(50);
        kitchen.addCoffeeMachines(coffeeMachine);
        try {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Espresso);
        } catch (CannotMakeACoffeeException ex) {
            assertEquals(CannotMakeACoffeeException.Reason.NO_GRAINS, ex.getReason());
        }
        coffeeMachine.addGrains();
        assertEquals(new Drinks(Drinks.Types.Espresso).getTypes(),
                kitchen.acceptOrder(coffeeMachine, Drinks.Types.Espresso).getTypes());
    }
}
