package ee.taltech.iti0202.bakery.exceptions;

public class PriceCanNotBeLessThanZeroException extends Exception{
    public static final String MESSAGE = "Price can not be less than zero!";

    /**
     * constructor
     */
    public PriceCanNotBeLessThanZeroException() {
        super(MESSAGE);
    }
}
