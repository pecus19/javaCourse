package ee.taltech.iti0202.bakery.exceptions;

public class ProductLimitExceededException extends Exception {
    public static final String MESSAGE = "Product limit exceeded!";

    /**
     * constructor
     */
    public ProductLimitExceededException() {
        super(MESSAGE);
    }
}
