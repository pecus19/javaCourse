package ee.taltech.iti0202.computerbuilder.exceptions;

public class OutOfStockException extends Exception {
    public static final String MESSAGE = "The Component is out of stock!";

    /**
     * constructor
     */
    public OutOfStockException() {
        super(MESSAGE);
    }
}
