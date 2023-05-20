package ee.taltech.iti0202.bakery.exceptions;

public class DoNotHaveEnoughMoneyToBuyException extends Exception{
    public static final String MESSAGE = "Don't have enough money to buy this product!";

    /**
     * constructor
     */
    public DoNotHaveEnoughMoneyToBuyException() {
        super(MESSAGE);
    }
}
