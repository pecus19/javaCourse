package ee.taltech.iti0202.bakery.exceptions;

public class BakerLimitException extends Exception{
    public static final String MESSAGE = "We can not add more than 5 bakers!";

    /**
     * constructor
     */
    public BakerLimitException() {
        super(MESSAGE);
    }
}
