package ee.taltech.iti0202.coffee.coffeeMachine;

import ee.taltech.iti0202.coffee.CoffeeExceptions.CannotMakeACoffeeException;
import ee.taltech.iti0202.coffee.builder.AutomaticCoffeeMachineBuilder;
import ee.taltech.iti0202.coffee.builder.CapsuleMachineBuilder;
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
                .createCapsuleMachine();
        CapsuleMachine coffeeMachine2 = new CapsuleMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
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
}