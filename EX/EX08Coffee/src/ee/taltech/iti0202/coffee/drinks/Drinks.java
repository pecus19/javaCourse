package ee.taltech.iti0202.coffee.drinks;

import java.util.HashMap;
import java.util.Map;

public class Drinks {
    public Types types;
    public Map<Types, Integer> getWater() {
        return water;
    }

    public Map<Types, Integer> getMilk() {
        return milk;
    }

    public Map<Types, Integer> getCoffee() {
        return coffee;
    }

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
        capsule.put(Drinks.Types.Americano, 50);
        capsule.put(Drinks.Types.Cappuccino, 100);
        capsule.put(Drinks.Types.Latte, 100);
        capsule.put(Drinks.Types.Espresso, 20);
        capsule.put(Drinks.Types.Mocha, 100);
    }

    public void addWater() {
        water.put(Drinks.Types.Americano, 50);
        water.put(Drinks.Types.Cappuccino, 100);
        water.put(Drinks.Types.Latte, 100);
        water.put(Drinks.Types.Espresso, 20);
        water.put(Drinks.Types.Mocha, 100);
        water.put(Types.WATER, 300);
    }

    public void addMilk() {
        milk.put(Drinks.Types.Americano, 10);
        milk.put(Drinks.Types.Cappuccino, 150);
        milk.put(Drinks.Types.Latte, 150);
        milk.put(Drinks.Types.Espresso, 0);
        milk.put(Drinks.Types.Mocha, 100);
    }

    public void addCoffee() {
        coffee.put(Drinks.Types.Americano, 50);
        coffee.put(Drinks.Types.Cappuccino, 75);
        coffee.put(Drinks.Types.Latte, 75);
        coffee.put(Drinks.Types.Espresso, 100);
        coffee.put(Drinks.Types.Mocha, 60);
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

