package ee.taltech.iti0202.bakery.exceptions;

public class CanNotAddProductToTheBakeryException extends Exception {
    public static final String MESSAGE = "Product already contains in another bakery!";

    /**
     * constructor
     */
    public CanNotAddProductToTheBakeryException() {
        super(MESSAGE);
    }
}
