package ee.taltech.iti0202.bakery.exceptions;

public class ProductAlreadyContainsInTheBakeryException extends Exception {
    public static final String MESSAGE = "Product already contains in the bakery!";

    /**
     * constructor
     */
    public ProductAlreadyContainsInTheBakeryException() {
        super(MESSAGE);
    }
}
