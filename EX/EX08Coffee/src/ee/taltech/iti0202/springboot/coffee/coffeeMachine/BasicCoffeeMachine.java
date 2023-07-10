package ee.taltech.iti0202.springboot.coffee.coffeeMachine;


import ee.taltech.iti0202.springboot.coffee.CoffeeExceptions.CannotMakeACoffeeException;
import ee.taltech.iti0202.springboot.coffee.drinks.Drinks;
import ee.taltech.iti0202.springboot.coffee.waterTank.WaterTank;

import java.util.logging.Logger;

public class BasicCoffeeMachine {
    public static final int FIVE_THOUSAND = 5000;
    Logger logger = Logger.getLogger(BasicCoffeeMachine.class.getName());
    private int trash;
    private int amountOfGrains;
    private WaterTank waterTank;
    private int amountOfMilk;
    private Drinks drink;


    public int getTrash() {
        return trash;
    }

    public void setTrash(int trash) {
        this.trash = trash;
    }

    public int getAmountOfGrains() {
        return amountOfGrains;
    }

    public void setAmountOfGrains(int amountOfGrains) {
        this.amountOfGrains = amountOfGrains;
    }

    public WaterTank getWaterTank() {
        return waterTank;
    }

    public BasicCoffeeMachine(int trash, int amountOfGrains, WaterTank waterTank, int amountOfMilk, Drinks drink) {
        this.trash = trash;
        this.amountOfGrains = amountOfGrains;
        this.waterTank = waterTank;
        this.amountOfMilk = amountOfMilk;
        this.drink = drink;
    }

    public Drinks start(Drinks.Types types) throws CannotMakeACoffeeException {
        if (isEmpty()) {
            if (chooseDrink(types) != null) {
                useGrains(getDrink(), types);
                useMilk(getDrink(), types);
                useWater(getDrink(), types);
                coffeeGrounds();
                logger.info(String.format("%s is ready!", types));
                return new Drinks(types);
            }
        }
        throw new CannotMakeACoffeeException(this, CannotMakeACoffeeException.Reason.TRASH_IS_FULL);
    }

    public void coffeeGrounds() {
        int trash = getTrash();
        setTrash(trash + 1);
    }


    public void throwAwayWaste() {
        setTrash(0);
    }

    public boolean isEmpty() {
        return getTrash() < 5;
    }

    public void addGrains() {
        if (getAmountOfGrains() < FIVE_THOUSAND) {
            setAmountOfGrains(FIVE_THOUSAND);
        }
    }

    public boolean checkGrains(Drinks drink, Drinks.Types types) throws CannotMakeACoffeeException {
        if (drink != null && types != null && getAmountOfGrains() >= drink.getAmountOfCoffee(types)) {
            logger.info("The number of grains is fine!");
            return true;
        }
        throw new CannotMakeACoffeeException(this, CannotMakeACoffeeException.Reason.NO_GRAINS);
    }

    public boolean checkWater(Drinks drink, Drinks.Types types) throws CannotMakeACoffeeException {
        if (drink != null && getWaterTank().canWeTake(drink.getAmountOfWater(types))) {
            logger.info("The amount of water is fine!");
            return true;
        }
        throw new CannotMakeACoffeeException(this, CannotMakeACoffeeException.Reason.NOT_WATER);
    }

    public boolean checkMilk(Drinks drink, Drinks.Types types) throws CannotMakeACoffeeException {
        if (drink != null && getAmountOfMilk() >= drink.getAmountOfMilk(types)) {
            logger.info("The amount of milk is fine!");
            return true;
        }
        throw new CannotMakeACoffeeException(this, CannotMakeACoffeeException.Reason.RAN_OUT_OF_MILK);
    }

    public Drinks.Types chooseDrink(Drinks.Types types) throws CannotMakeACoffeeException {
        if (types != null && checkGrains(getDrink(), types) && checkWater(getDrink(), types)
                && checkMilk(getDrink(), types)) {
            logger.info("Сoffee is being prepared....");
            return types;
        }
        return null;
    }

    public void useGrains(Drinks drink, Drinks.Types types) {
        setAmountOfGrains(getAmountOfGrains() - drink.getAmountOfCoffee(types));
    }

    public void useWater(Drinks drink, Drinks.Types types) {
        getWaterTank().takeWater(drink.getAmountOfWater(types));
    }

    public void useMilk(Drinks drink, Drinks.Types types) {
        setAmountOfMilk(getAmountOfMilk() - drink.getAmountOfMilk(types));
    }

    public int getAmountOfMilk() {
        return amountOfMilk;
    }

    public void setAmountOfMilk(int amountOfMilk) {
        this.amountOfMilk = amountOfMilk;
    }

    public Drinks getDrink() {
        return drink;
    }
}
