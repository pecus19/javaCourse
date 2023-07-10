package ee.taltech.iti0202.springboot.coffee.coffeeMachine;

import ee.taltech.iti0202.springboot.coffee.CoffeeExceptions.CannotMakeACoffeeException;
import ee.taltech.iti0202.springboot.coffee.drinks.Drinks;
import ee.taltech.iti0202.springboot.coffee.waterTank.WaterTank;

public class CapsuleCoffeeMachine extends BasicCoffeeMachine {
    private boolean capsuleIn;
    private boolean isUsed;

    public CapsuleCoffeeMachine(int trash, WaterTank waterTank, Drinks drink, boolean capsuleIn) {
        super(trash, 0, waterTank, 0, drink);
        this.capsuleIn = capsuleIn;
    }

    @Override
    public Drinks start(Drinks.Types types) throws CannotMakeACoffeeException {
        if (isEmpty()) {
            if (chooseDrink(types) != null) {
                if (isUsed()) {
                    useWater(getDrink(), Drinks.Types.WATER);
                    coffeeGrounds();
                    setUsed(false);
                    logger.info(String.format("%s is ready!", types));
                    return new Drinks(Drinks.Types.WATER);
                } else {
                    useWater(getDrink(), types);
                    coffeeGrounds();
                    logger.info(String.format("%s is ready!", types));
                    setUsed(true);
                    return new Drinks(types);
                }
            }
        }
        throw new CannotMakeACoffeeException(this, CannotMakeACoffeeException.Reason.TRASH_IS_FULL);
    }

    public void throwAwayTheCapsules()  {
        if (checkCapsule()) {
            setCapsuleIn(false);
            setUsed(false);
            logger.info("The capsule is thrown out!");
        }
    }

    public void putCapsule() throws CannotMakeACoffeeException {
        if (checkCapsule()) {
            setCapsuleIn(true);
            setUsed(false);
            logger.info("The capsule is inserted!");
        } else {
            throw new CannotMakeACoffeeException(this,
                    CannotMakeACoffeeException.Reason.THERE_IS_ALREADY_A_CAPSULE_INSIDE);
        }
    }

    public boolean checkCapsule() {
        return !getCapsuleIn();
    }

    @Override
    public Drinks.Types chooseDrink(Drinks.Types types) throws CannotMakeACoffeeException {
        if (types != null && checkWater(getDrink(), types)) {
            logger.info("Сoffee is being prepared....");
            return types;
        }
        throw new CannotMakeACoffeeException(this, CannotMakeACoffeeException.Reason.NOT_WATER);
    }

    @Override
    public boolean isEmpty() {
        return super.getTrash() < 10;
    }

    public Boolean getCapsuleIn() {
        return capsuleIn;
    }

    public void setCapsuleIn(Boolean capsuleIn) {
        this.capsuleIn = capsuleIn;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
