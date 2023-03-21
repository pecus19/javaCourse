package ee.taltech.iti0202.coffee;

import ee.taltech.iti0202.coffee.CoffeeExceptions.CannotMakeACoffeeException;
import ee.taltech.iti0202.coffee.coffeeMachine.BasicCoffeeMachine;
import ee.taltech.iti0202.coffee.builder.BasicCoffeeMachineBuilder;
import ee.taltech.iti0202.coffee.drinks.Drinks;
import ee.taltech.iti0202.coffee.kitchen.Kitchen;
import ee.taltech.iti0202.coffee.waterTank.WaterTank;

public class Main {
    public static void main(String[] args) throws CannotMakeACoffeeException {
        Kitchen kitchen = new Kitchen();
        WaterTank waterTank = new WaterTank();
        Drinks drinks = new Drinks();
        BasicCoffeeMachine coffeeMachine = new BasicCoffeeMachineBuilder().setTrash(0).setAmountOfGrains(1000).setWaterTank(waterTank).setAmountOfMilk(5000).setDrink(drinks).createBasicCoffeeMachine();
        coffeeMachine.start(Drinks.Types.Americano);
        coffeeMachine.start(Drinks.Types.Cappuccino);
//        coffeeMachine.start(Drinks.Types.Mocha);
//        coffeeMachine.start(Drinks.Types.Americano);
//        coffeeMachine.start(Drinks.Types.Americano);
//        coffeeMachine.start(Drinks.Types.Americano);
//        System.out.println(coffeeMachine.getTrash());
        // Automatic Coffee Machine
//        AutomaticCoffeeMachine automaticCoffeeMachine = new AutomaticCoffeeMachine(0, waterTank, drinks);
//        kitchen.addCoffeeMachines(automaticCoffeeMachine);
//        kitchen.addCoffeeMachines(coffeeMachine);
//        System.out.println(kitchen.getCoffeeMachines().size());
    }
}
