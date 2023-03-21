package ee.taltech.iti0202.coffee.drinks;

import java.util.HashMap;
import java.util.Map;

public class Drinks {
    public static final int FIFTY = 50;
    public static final int SEVENTY_FIVE = 75;
    public static final int HUNDRED = 100;
    public static final int BIG = 300;
    public static final int TWENTY = 20;
    public static final int ZERO = 0;
    public static final int TEN = 10;
    public static final int SIXTY = 60;
    public Types types;

    public Types getTypes() {
        return types;
    }

    public enum Types {
        Latte, Cappuccino, Americano, Espresso, Mocha, WATER
    }

    private Map<Types, Integer> water = new HashMap<>();
    private Map<Types, Integer> milk = new HashMap<>();
    private Map<Types, Integer> coffee = new HashMap<>();
    private Map<Types, Integer> capsule = new HashMap<>();

    public Drinks(Types types) {
        this.types = types;
    }

    public Drinks() {
        addCoffee();
        addWater();
        addMilk();
        addCapsule();
    }

    public void addCapsule() {
        capsule.put(Drinks.Types.Americano, FIFTY);
        capsule.put(Drinks.Types.Cappuccino, HUNDRED);
        capsule.put(Drinks.Types.Latte, HUNDRED);
        capsule.put(Drinks.Types.Espresso, TWENTY);
        capsule.put(Drinks.Types.Mocha, HUNDRED);
    }

    public void addWater() {
        water.put(Drinks.Types.Americano, FIFTY);
        water.put(Drinks.Types.Cappuccino, HUNDRED);
        water.put(Drinks.Types.Latte, HUNDRED);
        water.put(Drinks.Types.Espresso, TWENTY);
        water.put(Drinks.Types.Mocha, HUNDRED);
        water.put(Types.WATER, BIG);
    }

    public void addMilk() {
        milk.put(Drinks.Types.Americano, TEN);
        milk.put(Drinks.Types.Cappuccino, FIFTY + HUNDRED);
        milk.put(Drinks.Types.Latte, FIFTY + HUNDRED);
        milk.put(Drinks.Types.Espresso, ZERO);
        milk.put(Drinks.Types.Mocha, HUNDRED);
    }

    public void addCoffee() {
        coffee.put(Drinks.Types.Americano, FIFTY);
        coffee.put(Drinks.Types.Cappuccino, SEVENTY_FIVE);
        coffee.put(Drinks.Types.Latte, SEVENTY_FIVE);
        coffee.put(Drinks.Types.Espresso, HUNDRED);
        coffee.put(Drinks.Types.Mocha, SIXTY);
    }

    public int getAmountOfWater(Types drinks) {
        return water.get(drinks);
    }

    public int getAmountOfMilk(Types drinks) {
        return milk.get(drinks);
    }

    public int getAmountOfCoffee(Types drinks) {
        return coffee.get(drinks);
    }
}
