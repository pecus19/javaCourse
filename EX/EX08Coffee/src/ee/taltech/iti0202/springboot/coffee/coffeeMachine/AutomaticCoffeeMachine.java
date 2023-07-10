package ee.taltech.iti0202.springboot.coffee.coffeeMachine;

import ee.taltech.iti0202.springboot.coffee.CoffeeExceptions.CannotMakeACoffeeException;
import ee.taltech.iti0202.springboot.coffee.drinks.Drinks;
import ee.taltech.iti0202.springboot.coffee.waterTank.WaterTank;

import java.util.logging.Logger;

public class AutomaticCoffeeMachine extends BasicCoffeeMachine {
    Logger logger = Logger.getLogger(AutomaticCoffeeMachine.class.getName());

    public AutomaticCoffeeMachine(int trash, WaterTank waterTank, Drinks drink) {
        super(trash, 0, waterTank, 0, drink);
    }

    @Override
    public Drinks start(Drinks.Types types) throws CannotMakeACoffeeException {
        if (isEmpty()) {
            if (chooseDrink(types) != null) {
                useWater(getDrink(), types);
                coffeeGrounds();
                logger.info(String.format("%s is ready!", types));
                return new Drinks(types);
            }
        }
        throw new CannotMakeACoffeeException(this, CannotMakeACoffeeException.Reason.TRASH_IS_FULL);
    }


    @Override
    public boolean checkGrains(Drinks drink, Drinks.Types types) {
        logger.info("The number of grains is fine!");
        return true;
    }

    @Override
    public boolean checkMilk(Drinks drink, Drinks.Types types) {
        logger.info("The amount of milk is fine!");
        return true;
    }
}
