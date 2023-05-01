package ee.taltech.iti0202.computerbuilder.exceptions;

public class ProductNotFoundException extends Exception {
    public static final String MESSAGE = "We don't have all components in the store or this price is too small to create a computer!";

    /**
     * constructor
     */
    public ProductNotFoundException() {
        super(MESSAGE);
    }
}
