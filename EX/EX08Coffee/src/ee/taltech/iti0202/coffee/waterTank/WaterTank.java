package ee.taltech.iti0202.coffee.waterTank;


public class WaterTank {
    public static final int ZERO = 0;
    public static final int BIG = 10000;
    private int amountOfWater = BIG;

    public void takeWater(int amount) {
        if (canWeTake(amount)) {
            setAmountOfWater(getAmountOfWater() - amount);
        }
    }

    public int getAmountOfWater() {
        return amountOfWater;
    }

    public void setAmountOfWater(int amountOfWater) {
        this.amountOfWater = amountOfWater;
    }

    public boolean canWeTake(int amount) {
        return getAmountOfWater() - amount >= ZERO;
    }

    public void topUpWaterTank() {
        if (getAmountOfWater() < BIG) {
            setAmountOfWater(BIG);
        }
    }
}
