package ee.taltech.iti0202.bakery.exceptions;

public class OrderCanNotBeDoneException extends Exception {
    public static final String MESSAGE = "Order can not be done!";

    /**
     * constructor
     */
    public OrderCanNotBeDoneException() {
        super(MESSAGE);
    }
}
