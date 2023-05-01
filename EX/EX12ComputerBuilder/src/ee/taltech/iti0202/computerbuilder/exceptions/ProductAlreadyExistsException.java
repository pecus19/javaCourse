package ee.taltech.iti0202.computerbuilder.exceptions;

public class ProductAlreadyExistsException extends Exception {
    public static final String MESSAGE = "Product already exists exception!";

    /**
     * constructor
     */
    public ProductAlreadyExistsException() {
        super(MESSAGE);
    }
}
