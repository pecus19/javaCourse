package ee.taltech.iti0202.bakery.exceptions;

public class UnconfirmedOrdersException extends Exception{
    public static final String MESSAGE = "Cancel the order to make a new one!";

    /**
     * constructor
     */
    public UnconfirmedOrdersException() {
        super(MESSAGE);
    }
}
