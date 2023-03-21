package ee.taltech.iti0202.coffee.builder;

import ee.taltech.iti0202.coffee.coffeeMachine.CapsuleCoffeeMachine;
import ee.taltech.iti0202.coffee.drinks.Drinks;
import ee.taltech.iti0202.coffee.waterTank.WaterTank;

public class CapsuleMachineBuilder {
    private int trash;
    private WaterTank waterTank;
    private Drinks drink;
    private boolean capsuleIn;

    public CapsuleMachineBuilder setTrash(int trash) {
        this.trash = trash;
        return this;
    }

    public CapsuleMachineBuilder setWaterTank(WaterTank waterTank) {
        this.waterTank = waterTank;
        return this;
    }

    public CapsuleMachineBuilder setDrink(Drinks drink) {
        this.drink = drink;
        return this;
    }

    public CapsuleMachineBuilder setCapsuleIn(boolean capsuleIn) {
        this.capsuleIn = capsuleIn;
        return this;
    }

    public CapsuleCoffeeMachine createCapsuleMachine() {
        return new CapsuleCoffeeMachine(trash, waterTank, drink, capsuleIn);
    }
}
