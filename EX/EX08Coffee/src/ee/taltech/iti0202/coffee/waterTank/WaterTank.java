package ee.taltech.iti0202.coffee.waterTank;


public class WaterTank {
    private int amountOfWater = 10000;

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
        return getAmountOfWater() - amount >= 0;
    }

    public void topUpWaterTank() {
        if (getAmountOfWater() < 10000) {
            setAmountOfWater(10000);
        }
    }
}
