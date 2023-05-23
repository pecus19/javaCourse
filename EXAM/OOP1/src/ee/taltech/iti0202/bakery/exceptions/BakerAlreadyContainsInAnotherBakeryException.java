package ee.taltech.iti0202.bakery.exceptions;

public class BakerAlreadyContainsInAnotherBakeryException extends Exception{
    public static final String MESSAGE = "Baker already in another bakery!";

    /**
     * constructor
     */
    public BakerAlreadyContainsInAnotherBakeryException() {
        super(MESSAGE);
    }
}
