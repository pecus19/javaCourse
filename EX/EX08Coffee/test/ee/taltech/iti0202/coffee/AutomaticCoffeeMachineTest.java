package ee.taltech.iti0202.coffee;

import ee.taltech.iti0202.coffee.CoffeeExceptions.CannotMakeACoffeeException;
import ee.taltech.iti0202.coffee.builder.AutomaticCoffeeMachineBuilder;
import ee.taltech.iti0202.coffee.coffeeMachine.AutomaticCoffeeMachine;
import ee.taltech.iti0202.coffee.drinks.Drinks;
import ee.taltech.iti0202.coffee.kitchen.Kitchen;
import ee.taltech.iti0202.coffee.waterTank.WaterTank;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutomaticCoffeeMachineTest {
    public static final int FIFTY = 50;
    @org.junit.jupiter.api.Test
    void automaticCoffeeMachineCheckTrashTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        AutomaticCoffeeMachine coffeeMachine = new AutomaticCoffeeMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .createAutomaticCoffeeMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        for (int i = 0; i < 4; i++) {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Cappuccino);
        }
        assertEquals(4, coffeeMachine.getTrash());
        assertEquals(new Drinks(Drinks.Types.Espresso).getTypes(),
                kitchen.acceptOrder(coffeeMachine, Drinks.Types.Espresso).getTypes());
    }
    @org.junit.jupiter.api.Test
    void cannotMakeCoffeeUsingAutomaticCoffeeMachineDueToTrashTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        AutomaticCoffeeMachine coffeeMachine = new AutomaticCoffeeMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .createAutomaticCoffeeMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        for (int i = 0; i < 5; i++) {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Cappuccino);
        }
        try {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Latte);
        } catch (CannotMakeACoffeeException ex) {
            assertEquals(CannotMakeACoffeeException.Reason.TRASH_IS_FULL, ex.getReason());
        }
    }

    @org.junit.jupiter.api.Test
    void makeCoffeeUsingAutomaticCoffeeMachineALotOfOrdersTest() throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        Drinks drinks = new Drinks();
        WaterTank waterTank = new WaterTank();
        AutomaticCoffeeMachine coffeeMachine = new AutomaticCoffeeMachineBuilder()
                .setDrink(drinks)
                .setTrash(0)
                .setWaterTank(waterTank)
                .createAutomaticCoffeeMachine();
        kitchen.addCoffeeMachines(coffeeMachine);
        int counter = 0;
        for (int i = 0; i < FIFTY; i++) {
            kitchen.acceptOrder(coffeeMachine, Drinks.Types.Cappuccino);
            if (coffeeMachine.isEmpty()) {
                coffeeMachine.throwAwayWaste();
            }
            if (kitchen.acceptOrder(coffeeMachine, Drinks.Types.Espresso).getTypes() == Drinks.Types.Espresso) {
                counter++;
            }
        }
        assertEquals(FIFTY, counter);
    }
}
