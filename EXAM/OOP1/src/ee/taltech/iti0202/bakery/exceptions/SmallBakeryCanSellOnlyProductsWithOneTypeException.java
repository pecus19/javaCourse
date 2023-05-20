package ee.taltech.iti0202.bakery.exceptions;

public class SmallBakeryCanSellOnlyProductsWithOneTypeException extends Exception {
    public static final String MESSAGE = "Small bakery can sell only products with one type!";

    /**
     * constructor
     */
    public SmallBakeryCanSellOnlyProductsWithOneTypeException() {
        super(MESSAGE);
    }
}
