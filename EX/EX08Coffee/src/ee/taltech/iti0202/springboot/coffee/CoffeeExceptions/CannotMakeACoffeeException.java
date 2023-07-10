package ee.taltech.iti0202.springboot.coffee.CoffeeExceptions;

import ee.taltech.iti0202.springboot.coffee.coffeeMachine.BasicCoffeeMachine;

public class CannotMakeACoffeeException extends Throwable {
    private final Reason reason;
    private BasicCoffeeMachine basicCoffeeMachine;

    public enum Reason {
        NOT_WATER,
        TRASH_IS_FULL,
        NO_GRAINS,
        RAN_OUT_OF_MILK,
        THERE_IS_ALREADY_A_CAPSULE_INSIDE,
        WE_DONT_HAVE_THIS_MACHINE

    }

    public CannotMakeACoffeeException(BasicCoffeeMachine basicCoffeeMachine, Reason reason) {
        this.basicCoffeeMachine = basicCoffeeMachine;
        this.reason = reason;
    }


    public Reason getReason() {
        return reason;
    }
}
