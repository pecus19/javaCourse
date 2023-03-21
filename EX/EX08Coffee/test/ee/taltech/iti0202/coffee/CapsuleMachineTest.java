package ee.taltech.iti0202.coffee;

import ee.taltech.iti0202.coffee.CoffeeExceptions.CannotMakeACoffeeException;
import ee.taltech.iti0202.coffee.builder.AutomaticCoffeeMachineBuilder;
import ee.taltech.iti0202.coffee.builder.BasicCoffeeMachineBuilder;
import ee.taltech.iti0202.coffee.builder.CapsuleMachineBuilder;
import ee.taltech.iti0202.coffee.coffeeMachine.AutomaticCoffeeMachine;
import ee.taltech.iti0202.coffee.coffeeMachine.BasicCoffeeMachine;
import ee.taltech.iti0202.coffee.coffeeMachine.CapsuleMachine;
import ee.taltech.iti0202.coffee.drinks.Drinks;
import ee.taltech.iti0202.coffee.kitchen.Kitchen;
import ee.taltech.iti0202.coffee.waterTank.WaterTank;


import static org.junit.jupiter.api.Assertions.assertEquals;

class CapsuleMachineTest {

    @org.junit.jupiter.api.Test
    void thereIsNoSuchCoffeeMachineTest() {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        CapsuleMachine coffeeMachine = new CapsuleMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .setCapsuleIn(false)
                .createCapsuleMachine();
        CapsuleMachine coffeeMachine2 = new CapsuleMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .setCapsuleIn(false)
                .createCapsuleMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        try {
            kitchen.acceptOrder(coffeeMachine2, Drinks.Types.Latte);
        } catch (CannotMakeACoffeeException ex) {
            assertEquals(CannotMakeACoffeeException.Reason.WE_DONT_HAVE_THIS_MACHINE, ex.getReason());
        }
    }

    @org.junit.jupiter.api.Test
    void makeCoffeeWithCapsuleCoffeeMachineBasicTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        CapsuleMachine coffeeMachine = new CapsuleMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setCapsuleIn(false)
                .setWaterTank(waterTank)
                .createCapsuleMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        assertEquals(new Drinks(Drinks.Types.Espresso).getTypes(),
                kitchen.acceptOrder(coffeeMachine, Drinks.Types.Espresso).getTypes());
    }

    @org.junit.jupiter.api.Test
    void makeCoffeeWithCapsuleCoffeeMachineWithOneCapsuleTwiceTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        CapsuleMachine coffeeMachine = new CapsuleMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setCapsuleIn(false)
                .setWaterTank(waterTank)
                .createCapsuleMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        kitchen.acceptOrder(coffeeMachine, Drinks.Types.Espresso);
        assertEquals(new Drinks(Drinks.Types.WATER).getTypes(),
                kitchen.acceptOrder(coffeeMachine, Drinks.Types.WATER).getTypes());
    }

    @org.junit.jupiter.api.Test
    void makeCoffeeWithCapsuleCoffeeMachineChangeTheCapsuleTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        CapsuleMachine coffeeMachine = new CapsuleMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setCapsuleIn(false)
                .setWaterTank(waterTank)
                .createCapsuleMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        kitchen.acceptOrder(coffeeMachine, Drinks.Types.Espresso);
        coffeeMachine.throwAwayTheCapsules();
        coffeeMachine.putCapsule();
        assertEquals(new Drinks(Drinks.Types.Mocha).getTypes(),
                kitchen.acceptOrder(coffeeMachine, Drinks.Types.Mocha).getTypes());
    }

    @org.junit.jupiter.api.Test
    void putCapsuleInsideWhenMachineAlreadyHasAnotherTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        CapsuleMachine coffeeMachine = new CapsuleMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setCapsuleIn(false)
                .setWaterTank(waterTank)
                .createCapsuleMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        kitchen.acceptOrder(coffeeMachine, Drinks.Types.Espresso);
        coffeeMachine.putCapsule();
        try {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Latte);
        } catch (CannotMakeACoffeeException ex) {
            assertEquals(CannotMakeACoffeeException.Reason.THERE_IS_ALREADY_A_CAPSULE_INSIDE, ex.getReason());
        }
    }

    @org.junit.jupiter.api.Test
    void cannotMakeCoffeeUsingCapsuleCoffeeMachineDueToTrashTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        CapsuleMachine coffeeMachine = new CapsuleMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setCapsuleIn(false)
                .setWaterTank(waterTank)
                .createCapsuleMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        for (int i = 0; i < 10; i++) {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Cappuccino);
        }
        try {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Latte);
        } catch (CannotMakeACoffeeException ex) {
            assertEquals(CannotMakeACoffeeException.Reason.TRASH_IS_FULL, ex.getReason());
        }
    }

    @org.junit.jupiter.api.Test
    void putANewCapsuleWhenThereIsAnotherTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        CapsuleMachine coffeeMachine = new CapsuleMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setCapsuleIn(false)
                .setWaterTank(waterTank)
                .createCapsuleMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        kitchen.acceptOrder(coffeeMachine, Drinks.Types.Cappuccino);
        try {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Latte);
        } catch (CannotMakeACoffeeException ex) {
            assertEquals(CannotMakeACoffeeException.Reason.THERE_IS_ALREADY_A_CAPSULE_INSIDE, ex.getReason());
        }
    }

    @org.junit.jupiter.api.Test
    void threeCoffeeMachineHaveTheSameWaterTankTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        BasicCoffeeMachine basicCoffeeMachine = new BasicCoffeeMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .setAmountOfMilk(1000)
                .setAmountOfGrains(500)
                .createBasicCoffeeMachine();
        CapsuleMachine coffeeMachine = new CapsuleMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setCapsuleIn(false)
                .setWaterTank(waterTank)
                .createCapsuleMachine();
        AutomaticCoffeeMachine automaticCoffeeMachine = new AutomaticCoffeeMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .createAutomaticCoffeeMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        kitchen.addCoffeeMachines(basicCoffeeMachine);
        kitchen.addCoffeeMachines(automaticCoffeeMachine);
        kitchen.acceptOrder(coffeeMachine, Drinks.Types.Cappuccino);
        kitchen.acceptOrder(automaticCoffeeMachine, Drinks.Types.Latte);
        kitchen.acceptOrder(basicCoffeeMachine, Drinks.Types.Espresso);
        assertEquals(9780, waterTank.getAmountOfWater());
    }

    @org.junit.jupiter.api.Test
    void ranOutOfWaterAndImmediatelyFillUpTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        AutomaticCoffeeMachine automaticCoffeeMachine = new AutomaticCoffeeMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .createAutomaticCoffeeMachine();
        kitchen.addCoffeeMachines(automaticCoffeeMachine);
        kitchen.acceptOrder(automaticCoffeeMachine, Drinks.Types.Latte);
        for (int i = 0; i < 99; i++) {
            if (automaticCoffeeMachine.isEmpty()) {
                automaticCoffeeMachine.throwAwayWaste();
                kitchen.acceptOrder(automaticCoffeeMachine, Drinks.Types.Cappuccino);
            }
        }
        try {
            kitchen.acceptOrder(automaticCoffeeMachine, Drinks.Types.Latte);
        } catch (CannotMakeACoffeeException ex) {
            assertEquals(CannotMakeACoffeeException.Reason.NOT_WATER, ex.getReason());
        }
        waterTank.topUpWaterTank();
        assertEquals(new Drinks(Drinks.Types.Mocha).getTypes(),
                kitchen.acceptOrder(automaticCoffeeMachine, Drinks.Types.Mocha).getTypes());
    }
}
