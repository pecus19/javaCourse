package ee.taltech.iti0202.springboot.coffee.kitchen;

import ee.taltech.iti0202.springboot.coffee.CoffeeExceptions.CannotMakeACoffeeException;
import ee.taltech.iti0202.springboot.coffee.coffeeMachine.BasicCoffeeMachine;
import ee.taltech.iti0202.springboot.coffee.drinks.Drinks;

import java.util.ArrayList;
import java.util.List;

public class Kitchen {
    private List<BasicCoffeeMachine> coffeeMachines = new ArrayList<>();

    public void addCoffeeMachines(BasicCoffeeMachine coffeeMachine) {
        if (!coffeeMachines.contains(coffeeMachine)) {
            coffeeMachines.add(coffeeMachine);
        }
    }

    public Drinks acceptOrder(BasicCoffeeMachine coffeeMachine, Drinks.Types types) throws CannotMakeACoffeeException {
        if (getCoffeeMachines().contains(coffeeMachine)) {
            return coffeeMachine.start(types);
        }
        throw new CannotMakeACoffeeException(coffeeMachine,
                CannotMakeACoffeeException.Reason.WE_DONT_HAVE_THIS_MACHINE);
    }

    public List<BasicCoffeeMachine> getCoffeeMachines() {
        return coffeeMachines;
    }
}
