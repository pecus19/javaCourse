package ee.taltech.iti0202.springboot.coffee.builder;

import ee.taltech.iti0202.springboot.coffee.coffeeMachine.BasicCoffeeMachine;
import ee.taltech.iti0202.springboot.coffee.drinks.Drinks;
import ee.taltech.iti0202.springboot.coffee.waterTank.WaterTank;

public class BasicCoffeeMachineBuilder {
    private int trash;
    private int amountOfGrains;
    private WaterTank waterTank;
    private int amountOfMilk;
    private Drinks drink;

    public BasicCoffeeMachineBuilder setTrash(int trash) {
        this.trash = trash;
        return this;
    }

    public BasicCoffeeMachineBuilder setAmountOfGrains(int amountOfGrains) {
        this.amountOfGrains = amountOfGrains;
        return this;
    }

    public BasicCoffeeMachineBuilder setWaterTank(WaterTank waterTank) {
        this.waterTank = waterTank;
        return this;
    }

    public BasicCoffeeMachineBuilder setAmountOfMilk(int amountOfMilk) {
        this.amountOfMilk = amountOfMilk;
        return this;
    }

    public BasicCoffeeMachineBuilder setDrink(Drinks drink) {
        this.drink = drink;
        return this;
    }

    public BasicCoffeeMachine createBasicCoffeeMachine() {
        return new BasicCoffeeMachine(trash, amountOfGrains, waterTank, amountOfMilk, drink);
    }
}
