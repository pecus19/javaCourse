package ee.taltech.iti0202.springboot.coffee.builder;

import ee.taltech.iti0202.springboot.coffee.coffeeMachine.AutomaticCoffeeMachine;
import ee.taltech.iti0202.springboot.coffee.drinks.Drinks;
import ee.taltech.iti0202.springboot.coffee.waterTank.WaterTank;

public class AutomaticCoffeeMachineBuilder {
    private int trash;
    private WaterTank waterTank;
    private Drinks drink;

    public AutomaticCoffeeMachineBuilder setTrash(int trash) {
        this.trash = trash;
        return this;
    }

    public AutomaticCoffeeMachineBuilder setWaterTank(WaterTank waterTank) {
        this.waterTank = waterTank;
        return this;
    }

    public AutomaticCoffeeMachineBuilder setDrink(Drinks drink) {
        this.drink = drink;
        return this;
    }

    public AutomaticCoffeeMachine createAutomaticCoffeeMachine() {
        return new AutomaticCoffeeMachine(trash, waterTank, drink);
    }
}
