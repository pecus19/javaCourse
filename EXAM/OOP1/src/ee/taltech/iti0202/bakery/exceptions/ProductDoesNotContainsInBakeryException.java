package ee.taltech.iti0202.bakery.exceptions;

public class ProductDoesNotContainsInBakeryException extends Exception {
    public static final String MESSAGE = "Product does not contains in the bakery!";

    /**
     * constructor
     */
    public ProductDoesNotContainsInBakeryException() {
        super(MESSAGE);
    }
}
