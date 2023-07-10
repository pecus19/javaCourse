package ee.taltech.iti0202.springboot.kt.registration.exceptions;

public class ThisIsVIPService extends Exception {
    public static final String MESSAGE = "VIP service can not be canceled!";

    /**
     * constructor
     */
    public ThisIsVIPService() {
        super(MESSAGE);
    }
}
