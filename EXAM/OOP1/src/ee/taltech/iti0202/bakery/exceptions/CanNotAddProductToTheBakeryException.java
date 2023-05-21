package ee.taltech.iti0202.bakery.exceptions;

public class CanNotAddProductToTheBakeryException extends Exception {
    public static final String MESSAGE = "Cannot add product to the bakery!";

    /**
     * constructor
     */
    public CanNotAddProductToTheBakeryException() {
        super(MESSAGE);
    }
}
