package ee.taltech.iti0202.bakery.exceptions;

public class BakeryDoesNotHaveThisProductException extends Exception {
    public static final String MESSAGE = "Bakery does not have this product!";

    /**
     * constructor
     */
    public BakeryDoesNotHaveThisProductException() {
        super(MESSAGE);
    }
}
