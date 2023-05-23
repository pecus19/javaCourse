package ee.taltech.iti0202.bakery.exceptions;

public class BakerAlreadyInTheBakeryException extends Exception{
    public static final String MESSAGE = "Baker already in the bakery!";

    /**
     * constructor
     */
    public BakerAlreadyInTheBakeryException() {
        super(MESSAGE);
    }
}
